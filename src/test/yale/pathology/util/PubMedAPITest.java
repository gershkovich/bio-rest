package yale.pathology.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.fxml.builder.URLBuilder;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import yale.pathology.model.Publication;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.client.ResponseProcessingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
        String authAndJournal = "http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=auth:%22Gershkovich%20P%22%20JOURNAL:%22Proc%20AMIA%20Symp%22&format=json";

        String auth = "http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=auth:%22Gershkovich%20P%22%20&format=json";

        System.out.println(pubMedAPI.getResource(auth)); //returns too many results

    }

    /**
     * Tokenize request
     *
     * @throws Exception
     */
    @Test
    public void citationQuery() throws Exception
    {
        PubMedAPI pubMedAPI = new PubMedAPI();

        //demonstrates concatenated list of search elements - looks far from standards!
        String pubmedId = "http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=ext_id:24926086&format=json";

        String results = pubMedAPI.getResource(pubmedId);

        //   System.out.println(results); //returns too many results

        JsonFactory factory = new JsonFactory();

        JsonParser parser = factory.createParser(results);

        while (!parser.isClosed())
        {
            JsonToken token = parser.nextToken();

            if (token == null)
            {
                break;
            }

            if (JsonToken.FIELD_NAME.equals(token) && "resultList".equalsIgnoreCase(parser.getCurrentName()))
            {
                while (true)
                {
                    token = parser.nextToken();
                    if (token == null)
                    {
                        break;
                    }

                    token = parser.nextToken();
                    System.out.println(parser.getText());


                }
            }


        }


        assertTrue(results.contains("Gershkovich"));

    }


    /**
     * Parse to object
     *
     * @throws Exception
     */
    @Test
    public void parseAuthorQuery() throws Exception
    {
        PubMedAPI pubMedAPI = new PubMedAPI();

        //demonstrates concatenated list of search elements - looks far from standards!
        //  String url = "http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=ext_id:12463939&format=json";

        String url = "http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=auth:%22Gershkovich%20P%22%20AFF:yale&format=json";

        String results = "";

        Collection<Publication> publications = new ArrayList<>();

        try
        {
            results = pubMedAPI.getResource(url); //we do this as a separate call to check results as text if needed

            if (StringUtils.isNotEmpty(results))
            {
                publications = pubMedAPI.mapResults(results);
            }

        } catch (ResponseProcessingException rex)
        {
            System.out.println(rex.getResponse().getStatus());
            rex.printStackTrace();

        } catch (NotAcceptableException nex)
        {
            System.out.println(nex.getResponse().getStatus());
            nex.printStackTrace();
        }
        //   System.out.println(results); //returns too many results


        for (Publication pub : publications)
        {

            assertTrue(pub.getAuthorString().contains("Gershkovich"));

            if (pub.getCitedByCount() > 0)
            {
                System.out.println(pub.toString());

                //get newer publications by
            }
        }

    }


    /**
     * Parse to object
     *
     * @throws Exception
     */
    @Test
    public void parseSubjectQuery() throws Exception
    {
        PubMedAPI pubMedAPI = new PubMedAPI();

        //demonstrates concatenated list of search elements - looks far from standards!
        //  String url = "http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=ext_id:12463939&format=json";

        String query = URLEncoder.encode("PUB_YEAR:[2013 TO 2014]".replaceAll("\\s","%20"), "UTF-8");


        String url = "http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=COSTAR%20PUB_YEAR:%5B1970%20TO%201990%5D&format=json&resulttype=core";

        /*
        P_PDATE:[2000-12-18 TO 2014-12-30],  PUB_YEAR:%5B1990%20TO%202000%5D
        COSTAR, clinical information systems.,
        Collen MF. A History of Medical Informatics in the United States, 1950 to 1990. Indianapolis, IN: American Medical Informatics Association; 1995.
        */

        String results = "";

        Collection<Publication> publications = new ArrayList<>();

        try
        {
            results = pubMedAPI.getResource(url); //we do this as a separate call to check results as text if needed

            if (StringUtils.isNotEmpty(results))
            {
                publications = pubMedAPI.mapResults(results);
            }

        } catch (ResponseProcessingException rex)
        {
            System.out.println(rex.getResponse().getStatus());
            rex.printStackTrace();

        } catch (NotAcceptableException nex)
        {
            System.out.println(nex.getResponse().getStatus());
            nex.printStackTrace();
        }
        //   System.out.println(results); //returns too many results


        for (Publication pub : publications)
        {



            if (pub.getCitedByCount() > 0)
            {
                System.out.println(pub.toString());

                //get newer publications by
            }
        }

        // assertTrue(pub.getAuthorString().contains("Gershkovich"));

    }



}