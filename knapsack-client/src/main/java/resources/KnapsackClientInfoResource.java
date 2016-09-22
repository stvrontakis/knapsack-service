package resources;

import configuration.KnapsackClientConfiguration;
import io.dropwizard.client.JerseyClientConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-16
 * Time: 19:47
 * Project: knapsack-service
 */

@Path("/info")
@Produces(MediaType.APPLICATION_JSON)
public class KnapsackClientInfoResource {
    private final KnapsackClientConfiguration config;

    public KnapsackClientInfoResource(KnapsackClientConfiguration config) {
        this.config = config;
    }

    @GET
    public KnapsackClientInfo getInfo(@Context HttpServletRequest request) {
        KnapsackClientInfo info = new KnapsackClientInfo();
        JerseyClientConfiguration jerseyConfig = config.getJerseyClientConfiguration();
        info.put("Hostname", request.getHeader("Host"));
        info.put("Timeout", String.valueOf(jerseyConfig.getTimeout()));
        info.put("Connection timeout", String.valueOf(jerseyConfig.getConnectionTimeout()));
        info.put("Connection request timeout", String.valueOf(jerseyConfig.getConnectionRequestTimeout()));
        info.put("Time to live", String.valueOf(jerseyConfig.getTimeToLive()));
        info.put("Max connections", String.valueOf(jerseyConfig.getMaxConnections()));
        info.put("Keep alive", String.valueOf(jerseyConfig.getKeepAlive()));
        return info;
    }
}
