package org.apereo.cas.config;

import org.apereo.cas.authentication.AuthenticationServiceSelectionStrategyConfigurer;
import org.apereo.cas.authentication.DefaultAuthenticationServiceSelectionStrategy;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.configuration.features.CasFeatureModule;
import org.apereo.cas.util.spring.boot.ConditionalOnFeatureEnabled;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * This is {@link CasCoreAuthenticationServiceSelectionStrategyConfiguration}.
 *
 * @author Misagh Moayyed
 * @since 5.0.0
 */
@EnableConfigurationProperties(CasConfigurationProperties.class)
@ConditionalOnFeatureEnabled(feature = CasFeatureModule.FeatureCatalog.Authentication)
@AutoConfiguration
public class CasCoreAuthenticationServiceSelectionStrategyConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "casCoreAuthenticationServiceSelectionStrategyConfigurer")
    @RefreshScope(proxyMode = ScopedProxyMode.DEFAULT)
    public AuthenticationServiceSelectionStrategyConfigurer casCoreAuthenticationServiceSelectionStrategyConfigurer() {
        return plan -> plan.registerStrategy(new DefaultAuthenticationServiceSelectionStrategy());
    }
}
