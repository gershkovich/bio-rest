package yale.pathology.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PubMedAPITest
{

    @Before
    public void setUp() throws Exception
    {

    }


    //a set of tests demonstrate various usages
    @Test
    public void simpleQuery() throws Exception
    {
        PubMedAPI pubMedAPI = new PubMedAPI();

        System.out.println(pubMedAPI.getResource("http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=p53"));

    }

    @Test
    public void authorQuery() throws Exception
    {
        PubMedAPI pubMedAPI = new PubMedAPI();

        //demonstrates concatenated list of search elements - looks far from standards!
        String authAndJournal =  "http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=auth:%22Gershkovich%20P%22%20JOURNAL:%22Proc%20AMIA%20Symp%22&format=json";

        String auth =  "http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=auth:Gershkovich&format=json";

        System.out.println(pubMedAPI.getResource(auth)); //returns too many results

    }

    @Test
    public void citationQuery() throws Exception
    {
        PubMedAPI pubMedAPI = new PubMedAPI();

        //demonstrates concatenated list of search elements - looks far from standards!
        String pubmedId =  "http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=ext_id:24926086&format=json";

        System.out.println(pubMedAPI.getResource(pubmedId)); //returns too many results

    }

}