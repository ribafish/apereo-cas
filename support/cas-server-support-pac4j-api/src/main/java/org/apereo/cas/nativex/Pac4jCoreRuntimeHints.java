package org.apereo.cas.nativex;

import org.apereo.cas.authentication.principal.DelegatedAuthenticationCandidateProfile;
import org.apereo.cas.authentication.principal.DelegatedAuthenticationCredentialExtractor;
import org.apereo.cas.authentication.principal.DelegatedAuthenticationPreProcessor;
import org.apereo.cas.authentication.principal.DelegatedClientAuthenticationCredentialResolver;
import org.apereo.cas.authentication.principal.provision.DelegatedClientUserProfileProvisioner;
import org.apereo.cas.pac4j.client.DelegatedClientAuthenticationFailureEvaluator;
import org.apereo.cas.pac4j.client.DelegatedClientAuthenticationRequestCustomizer;
import org.apereo.cas.pac4j.client.DelegatedClientNameExtractor;
import org.apereo.cas.pac4j.discovery.DelegatedAuthenticationDynamicDiscoveryProviderLocator;
import org.apereo.cas.util.nativex.CasRuntimeHintsRegistrar;
import org.apereo.cas.web.DelegatedClientIdentityProviderConfiguration;
import org.pac4j.core.client.IndirectClient;
import org.pac4j.core.client.config.BaseClientConfiguration;
import org.pac4j.core.credentials.Credentials;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import java.util.Collection;
import java.util.List;

/**
 * This is {@link Pac4jCoreRuntimeHints}.
 *
 * @author Misagh Moayyed
 * @since 7.0.0
 */
public class Pac4jCoreRuntimeHints implements CasRuntimeHintsRegistrar {
    @Override
    public void registerHints(final RuntimeHints hints, final ClassLoader classLoader) {
        hints.serialization()
            .registerType(Credentials.class)
            .registerType(DelegatedClientIdentityProviderConfiguration.class)
            .registerType(DelegatedAuthenticationCandidateProfile.class);
        registerReflectionHints(hints,
            findSubclassesInPackage(BaseClientConfiguration.class, "org.pac4j"));
        registerReflectionHints(hints,
            findSubclassesInPackage(IndirectClient.class, "org.pac4j"));
        registerReflectionHints(hints,
            List.of(DelegatedClientIdentityProviderConfiguration.class));

        registerProxyHints(hints, DelegatedClientUserProfileProvisioner.class,
            DelegatedClientAuthenticationCredentialResolver.class,
            DelegatedAuthenticationDynamicDiscoveryProviderLocator.class,
            DelegatedClientAuthenticationFailureEvaluator.class,
            DelegatedClientAuthenticationRequestCustomizer.class,
            DelegatedClientNameExtractor.class,
            DelegatedAuthenticationCredentialExtractor.class,
            DelegatedAuthenticationPreProcessor.class,
            DelegatedClientAuthenticationRequestCustomizer.class);

    }

    private static void registerReflectionHints(final RuntimeHints hints, final Collection entries) {
        entries.forEach(el -> hints.reflection().registerType((Class) el,
            MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
            MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
            MemberCategory.INVOKE_DECLARED_METHODS,
            MemberCategory.INVOKE_PUBLIC_METHODS,
            MemberCategory.DECLARED_FIELDS,
            MemberCategory.PUBLIC_FIELDS));
    }
}
