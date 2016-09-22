package parameters;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-16
 * Time: 20:30
 * Project: knapsack-service
 */
public class Campaign {
    private String customer;
    private int impressions; /** Weight */
    private int revenue; /** Value */

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public Campaign(String customer, int impressions, int revenue) {
        this.customer = customer;
        this.impressions = impressions;
        this.revenue = revenue;
    }

    public Campaign() {}
}
