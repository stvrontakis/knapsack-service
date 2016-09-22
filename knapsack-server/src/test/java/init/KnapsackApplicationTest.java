package init;

import com.codahale.metrics.health.HealthCheckRegistry;
import configuration.KnapsackConfiguration;
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
 * Time: 15:54
 * Project: knapsack-service
 */
public class KnapsackApplicationTest {
    private Environment environment;
    private KnapsackConfiguration config;
    private JerseyEnvironment jerseyEnvironment;

    @Before
    public void setup() {
        config = new KnapsackConfiguration();
        config.setEnvironment("localhost");
        config.setOptimizeDp(3);
        config.setLargeProblemSet(250000000);
        environment = mock(Environment.class);
        jerseyEnvironment = mock(JerseyEnvironment.class);
        HealthCheckRegistry registry = mock(HealthCheckRegistry.class);
        when(environment.jersey()).thenReturn(jerseyEnvironment);
        when(environment.healthChecks()).thenReturn(registry);
    }

    @Test
    public void testKnapsackApplication() {
        try {
            KnapsackApplication app = new KnapsackApplication();
            Bootstrap bootstrap = mock(Bootstrap.class);
            app.initialize(bootstrap);
            app.run(config, environment);
            assertTrue(true);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            assertTrue(false);
        }
    }
}
