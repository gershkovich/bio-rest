package yale.pathology.util;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;


/**
 * Created by petergershkovich on 1/18/15.
 */
public class PubMedAPI
{
    public String getResource(String url)
    {
        //get pubmed data from Europe PubMed

        //http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=p53

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(url);


        String response = "";

        try
        {
            response = target.request("application/json").get(String.class);

        }   catch (ResponseProcessingException rex)
        {
            System.out.println(rex.getResponse().getStatus());
            rex.printStackTrace();

        }   catch (NotAcceptableException nex)
        {
            System.out.println(nex.getResponse().getStatus());
            nex.printStackTrace();
        }


        return  response;

    }
}
