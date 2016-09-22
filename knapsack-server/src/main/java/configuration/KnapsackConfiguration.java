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
    @JsonProperty("optimize_dp")
    private String optimizeDp;

    @NotEmpty
    @JsonProperty("large_problem_set")
    private String largeProblemSet;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public int getOptimizeDp() {
        return Integer.parseInt(optimizeDp);
    }

    public void setOptimizeDp(int optimizeDp) {
        this.optimizeDp = String.valueOf(optimizeDp);
    }

    public void setLargeProblemSet(int largeProblemSet) {
        this.largeProblemSet = String.valueOf(largeProblemSet);
    }

    public int getLargeProblemSet() {
        return Integer.parseInt(largeProblemSet);
    }
}