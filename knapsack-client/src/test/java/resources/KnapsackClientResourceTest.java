package resources;

import configuration.KnapsackClientConfiguration;
import io.dropwizard.setup.Environment;
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
    private Client jerseyClient;

    @Before
    public void setup() {
        jerseyClient = mock(Client.class);
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
    }

    @Ignore
    public void testKnapsackResource() throws IOException {
        //TODO: fix
        Problem problem = new Problem(3423413, null);
        Client client = mock(Client.class);
        ClientBuilder builder = mock(ClientBuilder.class);
        when(builder.buildClient(environment, configuration)).thenReturn(client);
        KnapsackClientResource clientResourceReal = new KnapsackClientResource(client, environment, configuration);
//        KnapsackClientResource clientResource = mock(KnapsackClientResource.class);
//        when(clientResource.buildClient(environment, configuration)).thenReturn(client);


        Solution solution = clientResourceReal.call(problem);
    }
}
