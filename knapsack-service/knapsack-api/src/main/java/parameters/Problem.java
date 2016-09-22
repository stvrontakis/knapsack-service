package parameters;

import java.util.List;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-17
 * Time: 16:39
 * Project: knapsack-service
 */
public class Problem {
    private int inventory;
    private List<Campaign> campaigns;

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public Problem(int inventory, List<Campaign> campaigns) {
        this.inventory = inventory;
        this.campaigns = campaigns;
    }

    public Problem() {}
}
