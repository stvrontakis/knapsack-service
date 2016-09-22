package client;

import api.KnapsackService;
import parameters.Problem;
import parameters.Solution;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-20
 * Time: 20:38
 * Project: knapsack-service
 */
public class KnapsackClient implements KnapsackService {
    private String serverHostname;
    private Client jerseyClient;

    public KnapsackClient(String serverHostname, Client jerseyClient) {
        this.serverHostname = serverHostname;
        this.jerseyClient = jerseyClient;
    }

    @Override
    public Solution getSolution(Problem problem) throws IOException {
        Response post = jerseyClient
                .target(serverHostname + "/knapsack/calculate")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(problem));
        return post.readEntity(Solution.class);
    }
}