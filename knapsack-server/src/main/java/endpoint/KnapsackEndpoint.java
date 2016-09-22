package endpoint;

import algorithm.Knapsack;
import configuration.KnapsackConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parameters.Problem;
import parameters.Solution;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-16
 * Time: 19:44
 * Project: knapsack-service
 */
@Path("/knapsack")
@Produces(MediaType.APPLICATION_JSON)
public class KnapsackEndpoint {
    private static Logger logger = LoggerFactory.getLogger(KnapsackEndpoint.class);
    private Knapsack knapsack;

    public KnapsackEndpoint(KnapsackConfiguration knapsackConfiguration) {
        logger.info("Starting KnapsackEndpoint");
        this.knapsack = new Knapsack(knapsackConfiguration.getLargeProblemSet());
    }

    @POST
    @Path("/calculate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Solution calculate(Problem problem) throws IOException {
        int[] optimalCombination = knapsack.calculateKnapsack(problem);
        System.out.println("Knapsack server was called!");
        System.out.println("Inventory: " + problem.getInventory());
        return knapsack.createSolution(optimalCombination, problem.getCampaigns());
    }
}