import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class HSMain {

	private static final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

	private static final String GET_HTTP_URL = "https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=4bc15a5148f0473013d78084d024";
	private static final String POST_HTTP_URL = "";

	public static void main(String args[]) {
		try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(GET_HTTP_URL).build();
			Response getResponse = client.newCall(request).execute();
			Gson gson = new Gson();
			String response = getResponse.body().string();

			ArrayList<Partners> listPartners = getPartnersList(response, gson);
			String json = getHttpRespJSON(listPartners);

			//System.out.println(json.length() + ", " + json);

			String finalResponse = httpResp(client, json);
			System.out.println(finalResponse);

		} catch (Exception ex) {
			System.out.println("Error!!");
		}
	}

	private static String httpResp(OkHttpClient client, String json) throws Exception {

		RequestBody body = RequestBody.create(mediaType, json);
		Request postRequest = new Request.Builder().url(POST_HTTP_URL).post(body)
				.addHeader("content-type", "application/json; charset=utf-8").build();
		Response postResponse = client.newCall(postRequest).execute();
		String response = postResponse.body().string();
		return response;

	}

	private static ArrayList<Partners> getPartnersList(String response, Gson gson) {
		
		Partners partners[] = gson.fromJson(response.substring(12, response.lastIndexOf("}")), Partners[].class);

		return new ArrayList<Partners>(Arrays.asList(partners));
	}

	private static String getHttpRespJSON(ArrayList<Partners> listPartners) {
		
		Gson gson = new Gson();

		Map<String, List<Partners>> mapCountries = listPartners.stream()
				.collect(Collectors
						.groupingBy(Partners::getCountry));
		
		Map<String, HashMap<String, List<String>>> mapInvitation = mapCountries.entrySet().stream()
				.collect(Collectors
						.toMap(e -> e.getKey(), e -> getDate(e.getValue())));
		Map<String, ArrayList<Invitation>> mapCOuntries = new HashMap<String, ArrayList<Invitation>>();
		
		ArrayList<Invitation> arrMapCOuntries = new ArrayList<Invitation>();
		
		mapInvitation
			.forEach((String k, HashMap<String, List<String>> v) -> {
			v.forEach((key, val) -> {
				arrMapCOuntries.add(new Invitation(k, key, val));
			});
		});
		
		mapCOuntries.put("countries", arrMapCOuntries);
		
		return gson.toJson(mapCOuntries);
		
	}

	public static HashMap<String, List<String>> getDate(List<Partners> listPartners) {
		
		Map<String, ArrayList<String>> emailDates = new HashMap<String, ArrayList<String>>();
		
		Map.Entry<String, ArrayList<String>> getValMax = null;
		
		Map<String, List<String>> output = new HashMap<String, List<String>>();
		
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd");
		
		HashMap<String, List<String>> finalDateOutput = (HashMap<String, List<String>>)output;
		
		listPartners.forEach(k -> {
		List<String> validDates = k.validDates();
		validDates.forEach(vDate -> {
			ArrayList<String> temp = null;
			
			if (emailDates.containsKey(vDate)) 
			{
				temp = emailDates.get(vDate);
			} 
			else 
			{
				temp = new ArrayList<String>();
			}
			temp.add(k.getEmail());
			
			emailDates.put(vDate, temp);
		});
		});

		try {
			
		for (Map.Entry<String, ArrayList<String>> e : emailDates.entrySet()) 
		{
			if ((getValMax == null || e.getValue().size() > getValMax.getValue().size())
					|| ((e.getValue().size() == getValMax.getValue().size()) && (sDF.parse(e.getKey())
							.compareTo(sDF.parse(getValMax.getKey())) < 0)))
				getValMax = e;
		}
		} catch (Exception e) {}
		
		output.put(getValMax.getKey(), getValMax.getValue());
				
		return finalDateOutput;
	}
}
