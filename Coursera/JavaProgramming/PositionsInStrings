class FindGeneSimpleTest
{
	public  String findGeneSimpleTest(String dna)
    {
      String result = "";
      int startIndex = dna.indexOf("ATG");
      int stopIndex = dna.indexOf("TAA",startIndex+3);


      if(startIndex==-1 ||stopIndex == -1)
      {
      
      	return "";
      
      }
      result = dna.substring(startIndex,stopIndex+3);

		return result;
	}

  public  void testFindGeneSimple()
  {

    String dna = "AATGCGTAATATGGT";
    System.out.println("DNA strand is: "+dna);
    String gene = findGeneSimpleTest(dna);    
    System.out.println("gene is: "+gene);

  }
  
}

public class TestString
{
public static void main(String args[])
    {
    	FindGeneSimpleTest fgst = new FindGeneSimpleTest();
	    fgst.testFindGeneSimple();
    }
}
