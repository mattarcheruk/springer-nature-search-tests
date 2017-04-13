package connector;

public class SpringerLinkFacet
{

    private String title;
    private int amount;
    private int percentage;

    SpringerLinkFacet(String title, int amount, int percentage)
    {
        this.title = title;
        this.amount = amount;
        this.percentage = percentage;
    }

    public String getTitle()
    {
        return title;
    }

    public int getAmount()
    {
        return amount;
    }

    public int getPercentage()
    {
        return percentage;
    }

}
