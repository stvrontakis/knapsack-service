package configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.util.Duration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-20
 * Time: 20:16
 * Project: knapsack-service
 */
public class KnapsackClientConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty("server_hostname")
    private String serverHostname;

    @NotEmpty
    @JsonProperty("connection_timeout")
    private String connectionTimeout;


    @JsonProperty("socket_timeout")
    private String socketTimeout;


    @JsonProperty("time_to_live")
    private String timeToLive;


    @JsonProperty("max_connections")
    private String maxConnections;


    @JsonProperty("keep_alive")
    private String keepAlive;

    @Valid
    @NotNull
    @JsonProperty
    private JerseyClientConfiguration jerseyClientConfig = new JerseyClientConfiguration();

    public JerseyClientConfiguration getJerseyClientConfiguration() {
        jerseyClientConfig.setConnectionTimeout(Duration.seconds(Long.parseLong(connectionTimeout)));
        jerseyClientConfig.setTimeout(Duration.seconds(Long.parseLong(socketTimeout)));
        jerseyClientConfig.setConnectionRequestTimeout(Duration.seconds(Long.parseLong(socketTimeout)));
        jerseyClientConfig.setMaxConnections(Integer.parseInt(maxConnections));
        jerseyClientConfig.setKeepAlive(Duration.seconds(Long.parseLong(keepAlive)));
        return jerseyClientConfig;
    }

    public String getServerHostname() {
        return serverHostname;
    }
}
