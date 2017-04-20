package connector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class SpringerLinkConnector
{

    public SpringerLinkSearchResults performSearch(SpringerLinkSearch searchToPerform)
    {
        String baseUrl = System.getProperty("test.site.url");
        if (baseUrl == null) baseUrl = "https://link.springer.com";

        String searchUrl = baseUrl + "/advanced-search";

        Document searchResultsDocument;

        try
        {
            searchResultsDocument = Jsoup
                    .connect(searchUrl)
                    .data(searchToPerform)
                    .post();
        }
        catch (IOException e)
        {
            throw new SpringerLinkConnectorException(e, "Failed to POST data to Springer Link search url");
        }

        return new SpringerLinkSearchResults(searchResultsDocument);
    }

}
