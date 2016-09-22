package algorithm;

import parameters.Campaign;
import parameters.MaximizedCampaign;
import parameters.Problem;
import parameters.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-19
 * Time: 21:50
 * Project: knapsack-service
 */
public class Knapsack {
    private int largeProblemSet;
    private ExhaustiveKnapsack uek;
    private FractorialKnapsack ufk;

    public Knapsack(int largeProblemSet) {
        this.largeProblemSet = largeProblemSet;
        uek = new ExhaustiveKnapsack();
        ufk = new FractorialKnapsack();
    }

    public int[] calculateKnapsack(Problem problem) {
        if(problem.getInventory() < largeProblemSet) {
            return uek.calculateKnapsack(problem);
        } else {
            return ufk.calculateKnapsack(problem);
        }
    }

    public Solution createSolution(int[] optimalCombination, List<Campaign> campaigns) {
        List<MaximizedCampaign> maximizedCampaigns = new ArrayList<>();
        for (int i = 0; i < optimalCombination.length; i++) {
            Campaign campaign = campaigns.get(i);
            MaximizedCampaign maximizedCampaign = new MaximizedCampaign(
                    campaign.getCustomer(),
                    optimalCombination[i],
                    optimalCombination[i] * campaign.getImpressions(),
                    optimalCombination[i] * campaign.getRevenue()
            );
            maximizedCampaigns.add(maximizedCampaign);
        }

        int maximizedImpressions = maximizedCampaigns.stream().mapToInt(p -> p.getMaximizedImpressions()).sum();
        int maximizedRevenues = maximizedCampaigns.stream().mapToInt(mc -> mc.getMaximizedRevenue()).sum();
        Solution solution = new Solution(maximizedImpressions,
                maximizedRevenues, maximizedCampaigns);
        return solution;
    }
}
