package hubspotFin;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Partners {
	private String lastName;

	private String email;

	private List<String> availableDates;

	private String firstName;

	private String country;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getAvailableDates() {
		return availableDates;
	}

	public void setAvailableDates(List<String> availableDates) {
		this.availableDates = availableDates;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> validDates() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		List<String> validDates = new ArrayList<String>();

		for (int i = 0; i <= availableDates.size() - 2; i++) {
			try {
				Date currDate = simpleDateFormat.parse(availableDates.get(i));
				Date nextDate = simpleDateFormat.parse(availableDates.get(i + 1));

				int diff = (int) ((currDate.getTime() - nextDate.getTime()) / (1000 * 60 * 60 * 24));

				if (diff == -1) {
					validDates.add(simpleDateFormat.format(currDate));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return validDates;
	}
}
