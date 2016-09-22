package algorithm;

import parameters.Campaign;
import parameters.Problem;

import java.util.List;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-19
 * Time: 18:29
 * Project: knapsack-service
 */
public class ExhaustiveKnapsack {
    public int[] calculateKnapsack(Problem problem) {
        int W = problem.getInventory();
        List<Campaign> campaigns = problem.getCampaigns();

        int n = campaigns.size();
        int[] maxValueEachStep = new int[W+1];
        int[] lastAddedInSack = new int[W+1];
        setAllArrayValue(maxValueEachStep, 0);
        setAllArrayValue(lastAddedInSack, -1);

        for(int i = 1; i < maxValueEachStep.length; i++) {
            for(int j = 0; j < n; j++) {
                Campaign campaign = campaigns.get(j);
                int wj = campaign.getImpressions();
                int vj = campaign.getRevenue();
                if( wj <= i ) {
                    int addedValue = vj + maxValueEachStep[i-wj];
                    if(addedValue > maxValueEachStep[i]) {
                        maxValueEachStep[i] = vj + maxValueEachStep[i-wj];
                        lastAddedInSack[i] = j;
                    }
                }
            }
        }
        return trackCombination(lastAddedInSack, campaigns);
    }

    protected void setAllArrayValue(int[] array, int value) {
        for(int i = 0; i< array.length; i++) {
            array[i] = value;
        }
    }

    private int[] trackCombination(int[] lastAddedInSack, List<Campaign> campaigns) {
        int[] combination = new int[campaigns.size()];

        int postTracker = lastAddedInSack.length-1;
        int itemTracker = lastAddedInSack[postTracker];

        while (itemTracker != -1 && postTracker > 0)
        {
            Campaign campaign = campaigns.get(itemTracker);
            int wj = campaign.getImpressions();
            combination[itemTracker]++;
            postTracker = postTracker - wj;
            itemTracker = lastAddedInSack[postTracker];
        }
        return combination;
    }
}
