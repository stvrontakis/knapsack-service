package parameters;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-18
 * Time: 17:11
 * Project: knapsack-service
 */
public class MaximizedCampaign {
    private String customer;
    private int numberOfCampaigns;
    private int maximizedImpressions;
    private int maximizedRevenue;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getNumberOfCampaigns() {
        return numberOfCampaigns;
    }

    public void setNumberOfCampaigns(int numberOfCampaigns) {
        this.numberOfCampaigns = numberOfCampaigns;
    }

    public int getMaximizedImpressions() {
        return maximizedImpressions;
    }

    public void setMaximizedImpressions(int maximizedImpressions) {
        this.maximizedImpressions = maximizedImpressions;
    }

    public int getMaximizedRevenue() {
        return maximizedRevenue;
    }

    public void setMaximizedRevenue(int maximizedRevenue) {
        this.maximizedRevenue = maximizedRevenue;
    }

    public MaximizedCampaign(String customer, int numberOfCampaigns, int maximizedImpressions, int maximizedRevenue) {
        this.customer = customer;
        this.numberOfCampaigns = numberOfCampaigns;
        this.maximizedImpressions = maximizedImpressions;
        this.maximizedRevenue = maximizedRevenue;
    }

    public MaximizedCampaign() {}
}
