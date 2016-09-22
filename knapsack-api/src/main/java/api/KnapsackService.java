package api;

import parameters.Problem;
import parameters.Solution;

import java.io.IOException;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-20
 * Time: 18:57
 * Project: knapsack-service
 */
public interface KnapsackService {
    Solution getSolution(Problem problem) throws IOException;
}
