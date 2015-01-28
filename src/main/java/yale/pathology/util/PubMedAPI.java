package yale.pathology.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import yale.pathology.model.Publication;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * Created by petergershkovich on 1/18/15.
 */
public class PubMedAPI
{
    public String getResource(String url)  throws  ResponseProcessingException, NotAcceptableException
    {
        //get pubmed central data from Europe PubMed

        //http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=p53

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(url);

        return target.request("application/xml").get(String.class);

    }

    public Collection<Publication> mapResults(String results) throws IOException
    {
        ArrayList<Publication> publications = new ArrayList<Publication>();

        ObjectMapper mapper = new ObjectMapper();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        JsonNode root = mapper.readTree(results);

        JsonNode numFound = root.findValue("hitCount");

        if (numFound != null && Integer.valueOf(numFound.toString()) > 0)
        {
            JsonNode docs = root.findValue("result");

            Iterator<JsonNode> jsonNodeIterator = docs.elements();

            while (jsonNodeIterator.hasNext())
            {
                JsonNode doc = jsonNodeIterator.next();

                if (doc != null)
                {
                    System.out.println(doc.toString());

                    Publication gene = mapper.readValue(doc.toString(), Publication.class);

                    publications.add(gene);
                }

            }

        }

        return publications;
    }
}
