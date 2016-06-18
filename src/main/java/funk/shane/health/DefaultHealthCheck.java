package funk.shane.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Only default check (for now)
 * Dropwizard strongly recommends this to be filled out better
 */
public class DefaultHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
