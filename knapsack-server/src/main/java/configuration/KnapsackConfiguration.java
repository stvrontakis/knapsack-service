package configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-16
 * Time: 19:41
 * Project: knapsack-service
 */
public class KnapsackConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty("environment")
    private String environment;

    @NotEmpty
    @JsonProperty("large_problem_set")
    private String largeProblemSet;

    public String getEnvironment() {
        return environment;
    }

    public int getLargeProblemSet() {
        return Integer.parseInt(largeProblemSet);
    }

    public void setLargeProblemSet(int largeProblemSet) {
        this.largeProblemSet = String.valueOf(largeProblemSet);
    }
}