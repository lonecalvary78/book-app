package demo.app.book.infra.healthcheck;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
@Readiness
public class ReadinessCheck implements HealthCheck {
  @ConfigProperty(name="quarkus.application.name")
  private String applicationName;


  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse.up(applicationName);
  }
}
