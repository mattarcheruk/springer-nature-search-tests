package tests.toInvestigate;

import connector.SpringerLinkConnector;
import connector.SpringerLinkSearch;
import connector.SpringerLinkSearchResults;
import org.junit.Test;

import static org.junit.Assert.*;

public class advancedSearchesToInvestigateTest
{

    /*
    *   I have commented out the test below so the build remains green.
    *   It can be temporarily uncommented and run to help facilitate discussions.
    *   It can be permanently uncommented or deleted once the desired behaviour is confirmed.
    */

    //@Test()
    public void investigateContentTypeFacetAmounts()
    {
        /*
        *
        *   Can we investigate this test as a team?
        *
        *   I had assumed the following scenario would hold true, but it does not always appear to be the case.
        *
        *   - Given a search for all resources published between 2015 and 2015,
        *   - And a search for all resources published between 2016 and 2016,
        *   - When the total number of resources returned by those searches are added together,
        *   - Then the combined total should equal the number of resources
        *     returned by a search for all resources published between 2015 and 2016.
        *
        */

        SpringerLinkSearch searchForAllResourcesPublishedIn2015 =
                new SpringerLinkSearch()
                        .withFilter("facet-start-year", "2015")
                        .withFilter("facet-end-year", "2015");

        SpringerLinkSearch searchForAllResourcesPublishedIn2016 =
                new SpringerLinkSearch()
                        .withFilter("facet-start-year", "2016")
                        .withFilter("facet-end-year", "2016");

        SpringerLinkSearch searchForAllResourcesPublishedIn2015And2016 =
                new SpringerLinkSearch()
                        .withFilter("facet-start-year", "2015")
                        .withFilter("facet-end-year", "2016");

        SpringerLinkSearchResults results2015 =
                new SpringerLinkConnector().performSearch(searchForAllResourcesPublishedIn2015);

        SpringerLinkSearchResults results2016 =
                new SpringerLinkConnector().performSearch(searchForAllResourcesPublishedIn2016);

        SpringerLinkSearchResults results2015And2016 =
                new SpringerLinkConnector().performSearch(searchForAllResourcesPublishedIn2015And2016);

        assertEquals(
                results2015And2016.getNumberOfSearchResults(),
                results2015.getNumberOfSearchResults() + results2016.getNumberOfSearchResults()
                );

    }

}
