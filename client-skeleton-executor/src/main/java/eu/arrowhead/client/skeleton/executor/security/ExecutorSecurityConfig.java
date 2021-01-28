package eu.arrowhead.client.skeleton.executor.security;

import eu.arrowhead.client.library.config.DefaultSecurityConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@ConditionalOnWebApplication
@EnableWebSecurity
public class ExecutorSecurityConfig extends DefaultSecurityConfig {
}
