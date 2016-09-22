package algorithm;

import parameters.Campaign;
import parameters.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-19
 * Time: 18:29
 * Project: knapsack-service
 */
public class ExhaustiveKnapsack {
    private int optimizeDp;

    public ExhaustiveKnapsack(int optimizeDp) {
        this.optimizeDp = optimizeDp;
    }

    public int[] calculateKnapsack(Problem problem) {
        Problem optimizedProblem = tryToOptimizeProblem(problem);

        int W = optimizedProblem.getInventory();
        List<Campaign> campaigns = optimizedProblem.getCampaigns();

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

    public Problem tryToOptimizeProblem(Problem problem) {
        if (isOptimizationApplicable(problem)) {
            int newInventory = problem.getInventory() / optimizeDp;
            List<Campaign> tempCampaigns = optimizeProblemCampaigns(problem);
            return new Problem(newInventory, tempCampaigns);
        } else {
            return problem;
        }
    }

    public boolean isOptimizationApplicable(Problem problem) {
        List<Campaign> campaigns = problem.getCampaigns();
        for (Campaign campaign: campaigns) {
            if(campaign.getImpressions() < optimizeDp) {
                return false;
            }
        }
        return true;
    }

    public List<Campaign> optimizeProblemCampaigns(Problem problem) {
        List<Campaign> tempCampaigns = copyCampaigns(problem);
        tempCampaigns.stream().forEach(campaign -> {
            int newImpressions = campaign.getImpressions() / optimizeDp;
            campaign.setImpressions(newImpressions);
        });
        return tempCampaigns;
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

    protected void setAllArrayValue(int[] array, int value) {
        for(int i = 0; i< array.length; i++) {
            array[i] = value;
        }
    }

    private int[] trackCombination(int[] lastAddedInSack, List<Campaign> campaigns) {
        int[] optimalCombination = new int[campaigns.size()];

        int postTracker = lastAddedInSack.length-1;
        int itemTracker = lastAddedInSack[postTracker];

        while (itemTracker != -1 && postTracker > 0)
        {
            Campaign campaign = campaigns.get(itemTracker);
            int wj = campaign.getImpressions();
            optimalCombination[itemTracker]++;
            postTracker = postTracker - wj;
            itemTracker = lastAddedInSack[postTracker];
        }
        return optimalCombination;
    }
}
