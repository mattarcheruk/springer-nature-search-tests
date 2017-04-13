package tests;

import connector.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class advancedSuccessfulSearchTest {

    private static SpringerLinkSearchResults searchResults;

    @BeforeClass()
    public static void performSearch()
    {
        SpringerLinkSearch searchThatShouldReturnLotsOfResults =
            new SpringerLinkSearch()
                .withFilter("all-words", "the");

        searchResults = new SpringerLinkConnector().performSearch(searchThatShouldReturnLotsOfResults);
    }

    @Test()
    public void testThatASuccessfulSearchHasAPositiveNumberOfResults()
    {
        int numberOfSearchResults = searchResults.getNumberOfSearchResults();

        assertTrue("The number of results associated with a successful search should be greater than 0",
                numberOfSearchResults > 0);
    }

    @Test()
    public void testThatASuccessfulSearchHasAtLeastOneContentTypeFacet() {

        List<SpringerLinkFacet> contentTypeFacets = searchResults.getContentTypeFacets();

        assertTrue("The number of 'Content Type' facets associated with a successful search should be greater than 0",
                contentTypeFacets.size() > 0);

    }

    @Test()
    public void testThatTheNumberOfPagesCorrespondsToTheNumberOfResults()
    {
        int assumedResultsPerPage = 20;

        int numberOfSearchResults = searchResults.getNumberOfSearchResults();

        int numberOfActualPages = searchResults.getNumberOfPagesOfSearchResults();
        int numberOfExpectedPages = (int) Math.ceil(numberOfSearchResults / (double) assumedResultsPerPage);

        assertEquals("The number of pages should be the number of search results divided by 20 (rounded up)",
                numberOfExpectedPages,
                numberOfActualPages);
    }

}
