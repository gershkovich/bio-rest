package yale.pathology.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import yale.pathology.model.Publication;

import java.util.ArrayList;
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
     * @throws Exception
     */
    @Test
    public void parseAuthorQuery() throws Exception
    {
        PubMedAPI pubMedAPI = new PubMedAPI();

        //demonstrates concatenated list of search elements - looks far from standards!
        String pubmedId = "http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=ext_id:24926086&format=json";

        String auth = "http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=auth:%22Gershkovich%20P%22%20&format=json";

        String results = pubMedAPI.getResource(auth);

        //   System.out.println(results); //returns too many results

        ArrayList<Publication> publications = new ArrayList<Publication>();

        ObjectMapper mapper = new ObjectMapper();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        JsonNode root = mapper.readTree(results);

        JsonNode numFound = root.findValue("hitCount");

        if ( numFound != null && Integer.valueOf(numFound.toString()) > 0 )
        {
            JsonNode docs = root.findValue("result");

            Iterator<JsonNode> jsonNodeIterator = docs.elements();

            while ( jsonNodeIterator.hasNext() )
            {
                JsonNode doc = jsonNodeIterator.next();

                if ( doc != null )
                {
                    System.out.println(doc.toString());

                    Publication gene = mapper.readValue(doc.toString(), Publication.class);

                    publications.add(gene);
                }

            }

        }


        for (Publication pub:publications)
        {
            System.out.println(pub.toString());
            assertTrue(pub.getAuthorString().contains("Gershkovich"));
        }

    }


}