package org.apereo.cas.nativex;

import lombok.val;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.RuntimeHints;

/**
 * This is {@link CoreSamlRuntimeHintsTests}.
 *
 * @author Misagh Moayyed
 * @since 7.0.0
 */
@Tag("Native")
public class CoreSamlRuntimeHintsTests {
    @Test
    public void verifyHints() {
        val hints = new RuntimeHints();
        new CoreSamlRuntimeHints().registerHints(hints, getClass().getClassLoader());
    }
}