package resources;

import client.KnapsackClient;
import configuration.KnapsackClientConfiguration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parameters.Problem;
import parameters.Solution;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-21
 * Time: 18:29
 * Project: knapsack-service
 */
@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
public class KnapsackClientResource {
    private KnapsackClient knapsackClient;
    private Environment environment;
    private KnapsackClientConfiguration knapsackClientConfiguration;
    private ClientBuilder clientBuilder;
    private static Logger logger = LoggerFactory.getLogger(KnapsackClientResource.class);

    public KnapsackClientResource(Environment environment, KnapsackClientConfiguration knapsackConfiguration, ClientBuilder builder) {
        logger.info("Starting Knapsack Client");
        this.environment = environment;
        this.knapsackClientConfiguration = knapsackConfiguration;
        this.clientBuilder = builder;
    }

    @POST
    @Path("/call")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Solution call(Problem problem) throws IOException {
        Client jerseyClient = clientBuilder.buildClient(environment, knapsackClientConfiguration);
        knapsackClient = new KnapsackClient(knapsackClientConfiguration.getServerHostname(), jerseyClient);
        return knapsackClient.getSolution(problem);
    }
}
