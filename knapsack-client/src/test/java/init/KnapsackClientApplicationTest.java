package init;

import com.codahale.metrics.health.HealthCheckRegistry;
import configuration.KnapsackClientConfiguration;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-22
 * Time: 18:37
 * Project: knapsack-service
 */
public class KnapsackClientApplicationTest {
        private Environment environment;
        private KnapsackClientConfiguration config;
        private JerseyEnvironment jerseyEnvironment;

        @Before
        public void setup() {
            config = new KnapsackClientConfiguration();
            config.setConnectionTimeout("3");
            config.setKeepAlive("1");
            config.setMaxConnections("1");
            config.setServerHostname("http://localhost:9000");
            config.setSocketTimeout("60");
            config.setTimeToLive("60");
            environment = mock(Environment.class);
            jerseyEnvironment = mock(JerseyEnvironment.class);
            HealthCheckRegistry registry = mock(HealthCheckRegistry.class);
            when(environment.jersey()).thenReturn(jerseyEnvironment);
            when(environment.healthChecks()).thenReturn(registry);
        }

        @Test
        public void testKnapsackApplication() {
            try {
                KnapsackClientApplication app = new KnapsackClientApplication();
                Bootstrap bootstrap = mock(Bootstrap.class);
                app.initialize(bootstrap);
                assertTrue(true);
            } catch(Exception e) {
                System.out.println(e.getMessage());
                assertTrue(false);
            }
        }

}
