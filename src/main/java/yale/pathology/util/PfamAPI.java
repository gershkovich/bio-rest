package yale.pathology.util;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;

/**
 * Created by petergershkovich on 2/10/15.
 */
public class PfamAPI
{
    //http://pfam.xfam.org/families?output=xml

    public String getResource(String url)  throws ResponseProcessingException, NotAcceptableException
    {

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(url);

        return target.request("application/xml").get(String.class);

    }

}
