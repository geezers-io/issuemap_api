package com.ex.befinal.authentication.config;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.client.ClientsConfiguredCondition;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientPropertiesRegistrationAdapter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
@Configuration
@EnableConfigurationProperties(OAuth2ClientProperties.class)
@Conditional(ClientsConfiguredCondition.class)
@RequiredArgsConstructor
public class OAuth2ClientRegistrationRepositoryConfiguration {
  private final OAuth2ClientProperties properties;


  @Bean
  @ConditionalOnMissingBean(ClientRegistrationRepository.class)
  public InMemoryClientRegistrationRepository clientRegistrationRepository() {
    List<ClientRegistration> registrations = new ArrayList<>(
        OAuth2ClientPropertiesRegistrationAdapter.getClientRegistrations(this.properties).values());
    return new InMemoryClientRegistrationRepository(registrations);
  }

}
