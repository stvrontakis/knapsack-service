package parameters;

import java.util.List;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-18
 * Time: 17:03
 * Project: knapsack-service
 */
public class Solution {
    int maximizedImpressions;
    int maximizedRevenue;
    List<MaximizedCampaign> maximizedCampaigns;

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

    public List<MaximizedCampaign> getMaximizedCampaigns() {
        return maximizedCampaigns;
    }

    public void setMaximizedCampaigns(List<MaximizedCampaign> maximizedCampaigns) {
        this.maximizedCampaigns = maximizedCampaigns;
    }

    public Solution(int maximizedImpressions, int maximizedRevenue, List<MaximizedCampaign> maximizedCampaigns) {
        this.maximizedImpressions = maximizedImpressions;
        this.maximizedRevenue = maximizedRevenue;
        this.maximizedCampaigns = maximizedCampaigns;
    }

    public Solution() {}
}
