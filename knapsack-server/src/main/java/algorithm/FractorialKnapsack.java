package algorithm;

import parameters.Campaign;
import parameters.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-19
 * Time: 18:32
 * Project: knapsack-service
 */
public class FractorialKnapsack {

    public int[] calculateKnapsack(Problem problem) {
        int W = problem.getInventory();
        List<Campaign> tempCampaigns = copyCampaigns(problem);

        int[] optimal = new int[tempCampaigns.size()];

        int n = tempCampaigns.size(); //number of items
        int max_qty = W;
        int currentImpressionsLeft = max_qty;
        int j = 0;
        float max;
        float sum = 0;
        while(currentImpressionsLeft > 0) {
            max = 0;
            for(int i = 0; i < n; i++) {
                Campaign campaign = tempCampaigns.get(i);
                int revenue = campaign.getRevenue();
                int impressions = campaign.getImpressions();
                float logos = (float)revenue / (float)impressions;
                if(logos > max) {
                    max = logos;
                    j = i;
                }
            }
            if(max == 0) {
                currentImpressionsLeft = -1;
            }
            Campaign campaign = tempCampaigns.get(j);
            int impressions = campaign.getImpressions();
            if(impressions > currentImpressionsLeft) {
                campaign.setRevenue(0);
            } else {
                optimal[j]++;
                currentImpressionsLeft -= campaign.getImpressions();
                sum += campaign.getRevenue();
            }
        }
        return optimal;
    }

    public List<Campaign> copyCampaigns(Problem problem) {
        List<Campaign> campaigns = problem.getCampaigns();
        List<Campaign> tempCampaigns = new ArrayList<>(problem.getCampaigns());

        for(int i = 0; i < tempCampaigns.size(); i++) {
            Campaign campaign = campaigns.get(i);
            tempCampaigns.set(i, new Campaign(campaign.getCustomer(), campaign.getImpressions(), campaign.getRevenue()));
        }
        return tempCampaigns;
    }
}
