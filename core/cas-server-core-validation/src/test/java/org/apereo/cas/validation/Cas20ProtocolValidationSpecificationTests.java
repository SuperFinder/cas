package org.apereo.cas.validation;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.*;

/**
 * @author Scott Battaglia
 * @since 3.0.0
 */
@Slf4j
public class Cas20ProtocolValidationSpecificationTests {

    @Test
    public void verifyRenewGettersAndSettersFalse() {
        val s = new Cas20ProtocolValidationSpecification();
        s.setRenew(false);
        assertFalse(s.isRenew());
    }

    @Test
    public void verifyRenewGettersAndSettersTrue() {
        val s = new Cas20ProtocolValidationSpecification();
        s.setRenew(true);
        assertTrue(s.isRenew());
    }

    @Test
    public void verifyRenewAsTrueAsConstructor() {
        assertTrue(new Cas20ProtocolValidationSpecification(true).isRenew());
    }

    @Test
    public void verifyRenewAsFalseAsConstructor() {
        assertFalse(new Cas20ProtocolValidationSpecification(false).isRenew());
    }

    @Test
    public void verifySatisfiesSpecOfTrue() {
        assertTrue(new Cas20ProtocolValidationSpecification(true).isSatisfiedBy(CoreValidationTestUtils.getAssertion(true),
            new MockHttpServletRequest()));
    }

    @Test
    public void verifyNotSatisfiesSpecOfTrue() {
        assertFalse(new Cas20ProtocolValidationSpecification(true).isSatisfiedBy(CoreValidationTestUtils.getAssertion(false),
            new MockHttpServletRequest()));
    }

    @Test
    public void verifySatisfiesSpecOfFalse() {
        assertTrue(new Cas20ProtocolValidationSpecification(false).isSatisfiedBy(CoreValidationTestUtils.getAssertion(true),
            new MockHttpServletRequest()));
    }

    @Test
    public void verifySatisfiesSpecOfFalse2() {
        assertTrue(new Cas20ProtocolValidationSpecification(false).isSatisfiedBy(CoreValidationTestUtils.getAssertion(false),
            new MockHttpServletRequest()));
    }
}
