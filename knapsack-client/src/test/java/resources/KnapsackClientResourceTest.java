package resources;

import configuration.KnapsackClientConfiguration;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.junit.Before;
import org.junit.Ignore;
import parameters.Problem;
import parameters.Solution;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-21
 * Time: 21:25
 * Project: knapsack-service
 */
public class KnapsackClientResourceTest {
    private Environment environment;
    private KnapsackClientConfiguration configuration;
    private JerseyClient jerseyClient;

    @Before
    public void setup() {
        jerseyClient = mock(JerseyClient.class);
        Response response = mock(Response.class);
        JerseyWebTarget target = mock(JerseyWebTarget.class);
        JerseyInvocation.Builder builder = mock(JerseyInvocation.Builder.class);
        JerseyInvocation.Builder builder2 = mock(JerseyInvocation.Builder.class);
        when(jerseyClient.target("http://localhost:9000/knapsack/calculate")).thenReturn(target);
        when(target.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        when(builder.accept("application/json")).thenReturn(builder2);
        when(builder2.post(any())).thenReturn(response);
        when(response.readEntity(Solution.class)).thenReturn(new Solution(0, 1, null));

        configuration = new KnapsackClientConfiguration();
        configuration.setConnectionTimeout("3");
        configuration.setKeepAlive("1");
        configuration.setMaxConnections("1");
        configuration.setServerHostname("http://localhost:9000");
        configuration.setSocketTimeout("60");
        configuration.setTimeToLive("60");

        environment = mock(Environment.class);
        Client client = mock(Client.class);
//        JerseyClientBuilder jerseyClientBuilder = mock(JerseyClientBuilder.class);
//        JerseyClientBuilder jerseyClientBuilder2 = mock(JerseyClientBuilder.class);
//        when(any(JerseyClientBuilder.class).using(configuration.getJerseyClientConfiguration())).thenReturn(any(JerseyClientBuilder.class));
        when(any(JerseyClientBuilder.class).build("knapsack-client")).thenReturn(client);
    }

    @Ignore
    public void testKnapsackResource() throws IOException {
        //TODO: fix
        Problem problem = new Problem(3423413, null);

        KnapsackClientResource clientResource = new KnapsackClientResource(environment, configuration);
        Solution solution = clientResource.call(problem);
    }
}
