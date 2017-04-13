package connector;

import java.util.HashMap;

public class SpringerLinkSearch extends HashMap<String, String>
{

    public SpringerLinkSearch()
    {
        this.put("all-words", "");
        this.put("exact-phrase", "");
        this.put("least-words", "");
        this.put("without-words", "");
        this.put("title-is", "");
        this.put("author-is", "");
        this.put("date-facet-mode", "between");
        this.put("facet-start-year", "");
        this.put("facet-end-year", "");
        this.put("show-locked-results", "true");
    }

    public SpringerLinkSearch withFilter(String filterName, String filterValue)
    {
        this.put(filterName, filterValue);

        return this;
    }

}
