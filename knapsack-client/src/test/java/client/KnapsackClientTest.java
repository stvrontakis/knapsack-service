package client;


import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.junit.Before;
import org.junit.Test;
import parameters.Problem;
import parameters.Solution;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-21
 * Time: 15:23
 * Project: knapsack-service
 */
public class KnapsackClientTest {
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
    }

    @Test
    public void testClient() throws IOException {
        Problem problem = new Problem(123231231, null);

        KnapsackClient client = new KnapsackClient("http://localhost:9000", jerseyClient);

        Solution solution = client.getSolution(problem);

        assertEquals(0, solution.getMaximizedImpressions());
        assertEquals(1, solution.getMaximizedRevenue());
        assertEquals(null, solution.getMaximizedCampaigns());
    }
}
