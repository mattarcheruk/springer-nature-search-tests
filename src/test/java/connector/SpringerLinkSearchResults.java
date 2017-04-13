package connector;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpringerLinkSearchResults
{
    private Document searchResultsHtmlDocument;

    SpringerLinkSearchResults(Document searchResultsHtmlDocument)
    {
        this.searchResultsHtmlDocument = searchResultsHtmlDocument;
    }

    public int getNumberOfSearchResults()
    {
        final String numberOfSearchResultsLocator = ".number-of-search-results-and-search-terms strong:first-of-type";

        Elements numberOfSearchResultsElement = searchResultsHtmlDocument.select(numberOfSearchResultsLocator);
        return convertCommaSeparatedNumberToInt(numberOfSearchResultsElement.text());
    }

    public int getNumberOfPagesOfSearchResults()
    {
        final String numberOfPagesLocator = ".number-of-pages";

        Elements numberOfPagesElement = searchResultsHtmlDocument.select(numberOfPagesLocator);
        return convertCommaSeparatedNumberToInt(numberOfPagesElement.text());
    }

    public List<SpringerLinkFacet> getContentTypeFacets()
    {
        final String contentTypeFacetTitleLocator = "#content-type-facet .facet-title";
        final String contentTypeFacetAmountLocator = "#content-type-facet .facet-amount";
        final String contentTypeFacetPercentageLocator = "#content-type-facet .facet-percentage";

        Elements contentTypeFacetTitleElements = searchResultsHtmlDocument.select(contentTypeFacetTitleLocator);
        Elements contentTypeFacetAmountElements = searchResultsHtmlDocument.select(contentTypeFacetAmountLocator);
        Elements contentTypeFacetPercentageElements = searchResultsHtmlDocument.select(contentTypeFacetPercentageLocator);

        List<SpringerLinkFacet> contentTypeFacetsList = new ArrayList<SpringerLinkFacet>();

        for (int i = 0; i < contentTypeFacetTitleElements.size(); i++)
        {
            String facetTitle = contentTypeFacetTitleElements.get(i).text();
            int facetAmount = convertCommaSeparatedNumberToInt(contentTypeFacetAmountElements.get(i).text());
            int facetPercentage = extractFacetPercentageFromStyleAttribute(
                    contentTypeFacetPercentageElements.get(i).attr("style"));

            SpringerLinkFacet facet = new SpringerLinkFacet(facetTitle, facetAmount, facetPercentage);

            contentTypeFacetsList.add(facet);
        }

        return contentTypeFacetsList;
    }

    private int convertCommaSeparatedNumberToInt(String commaSeparatedNumber)
    {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.UK);

        try
        {
            return numberFormat.parse(commaSeparatedNumber).intValue();
        }
        catch (ParseException e)
        {
            throw new SpringerLinkConnectorException(e, "Unable to convert comma separated value to integer");
        }
    }

    private int extractFacetPercentageFromStyleAttribute(String styleAttributeValue)
    {
        Pattern pattern = Pattern.compile("width: (\\d+)%;");
        Matcher matcher = pattern.matcher(styleAttributeValue);

        if (matcher.find())
        {
            return Integer.parseInt(matcher.group(1));
        }
        else
        {
            throw new SpringerLinkConnectorException("Unable to extract facet percentage [" + styleAttributeValue +"]");
        }
    }


}
