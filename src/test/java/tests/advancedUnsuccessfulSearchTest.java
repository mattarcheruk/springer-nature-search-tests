package tests;

import connector.SpringerLinkConnector;
import connector.SpringerLinkFacet;
import connector.SpringerLinkSearch;
import connector.SpringerLinkSearchResults;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class advancedUnsuccessfulSearchTest {

    private static SpringerLinkSearchResults searchResults;

    @BeforeClass()
    public static void performSearch()
    {
        SpringerLinkSearch searchThatShouldNotReturnAnyResults =
            new SpringerLinkSearch()
                .withFilter("exact-phrase", "matt@equalexperts.com");

        searchResults = new SpringerLinkConnector().performSearch(searchThatShouldNotReturnAnyResults);
    }

    @Test()
    public void testThatASuccessfulSearchHasAPositiveNumberOfResults()
    {
        int numberOfSearchResults = searchResults.getNumberOfSearchResults();

        assertEquals("The number of results associated with an unsuccessful search should be 0",
                0, numberOfSearchResults);
    }

    @Test()
    public void testThatASuccessfulSearchHasAtLeastOneContentTypeFacetFilter() {

        List<SpringerLinkFacet> contentTypeFacets = searchResults.getContentTypeFacets();

        assertEquals("The number of 'Content Type' facets associated with an unsuccessful search should be 0",
                0, contentTypeFacets.size());

    }

}
