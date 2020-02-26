package com.gudigudigudi.mdtemplate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("EmailValidator")
class EmailValidatorTest {
    @Nested
    class CorrectEmail {
        @Test
        void simple_ReturnsTrue() {
            assertTrue(EmailValidator.isValidEmail("name@email.com"));
        }

        @Test
        void subDomain_ReturnsTrue() {
            assertTrue(EmailValidator.isValidEmail("name@email.co.uk"));
        }
    }

    @Nested
    class InvalidEmail {
        @Test
        void NoTld_ReturnsFalse() {
            assertFalse(EmailValidator.isValidEmail("name@email"));
        }

        @Test
        void doubleDot_ReturnsFalse() {
            assertFalse(EmailValidator.isValidEmail("name@email..com"));
        }

        @Test
        void noUsername_ReturnsFalse() {
            assertFalse(EmailValidator.isValidEmail("@email.com"));
        }
    }

    @Test
    void emptyString_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(""));
    }

    @Test
    void nullEmail_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(null));
    }
}