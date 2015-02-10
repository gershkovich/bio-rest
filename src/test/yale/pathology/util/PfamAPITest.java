package yale.pathology.util;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import yale.pathology.model.Pfam;
import yale.pathology.model.Publication;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.client.ResponseProcessingException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class PfamAPITest
{

    @Before
    public void setUp() throws Exception
    {

    }

    @Test
    public void testGetResource() throws Exception
    {
        PfamAPI pfamAPI = new PfamAPI();

        //demonstrates concatenated list of search elements - looks far from standards!
        String url = "http://pfam.xfam.org/families?output=xml";

        String results = "";

        Collection<Pfam> pfams = new ArrayList<>();

        try
        {
            results = pfamAPI.getResource(url); //we do this as a separate call to check results as text if needed


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





    }

    @Test
    public void testPfamParsing() throws Exception
    {
//        File input = new File("samples/families.xml");
//        Document doc = Jsoup.parse(input, "UTF-8", "");

        //alternative get doc from url
        Document doc = Jsoup.connect("http://pfam.xfam.org/families?output=xml").get();

        Elements links = doc.getElementsByTag("entry");
        for (Element link : links) {

            String linkHref = link.text();

            System.out.println(link.attr("id") + "\t" + link.attr("accession") + "\t" + linkHref);
        }

    }
}