package resources;

import configuration.KnapsackClientConfiguration;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-21
 * Time: 23:02
 * Project: knapsack-service
 */
public class KnapsackClientInfoResourceTest {
    private KnapsackClientConfiguration config;
    private HttpServletRequest request;

    @Before
    public void setup() {
        request = mock(HttpServletRequest.class);
        when(request.getHeader("Host")).thenReturn("localhost");
        config = new KnapsackClientConfiguration();

        config.setConnectionTimeout("3");
        config.setKeepAlive("1");
        config.setMaxConnections("1");
        config.setServerHostname("http://localhost:9000");
        config.setSocketTimeout("60");
        config.setTimeToLive("60");
    }

    @Test
    public void testKnapsackClientInfoResource() {
        KnapsackClientInfoResource knapsackClientInfoResource = new KnapsackClientInfoResource(config);
        KnapsackClientInfo info = knapsackClientInfoResource.getInfo(request);
        String hostname = info.get("Hostname");
        assertTrue(hostname.equals("localhost"));
        assertEquals("60 seconds", info.get("Timeout"));
        assertEquals("3 seconds", info.get("Connection timeout"));
        assertEquals("60 seconds", info.get("Connection request timeout"));
        assertEquals("60 minutes", info.get("Time to live"));
        assertEquals("1", info.get("Max connections"));
        assertEquals("1 second", info.get("Keep alive"));
    }
}