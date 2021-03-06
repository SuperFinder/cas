package org.apereo.cas.adaptors.trusted.web.flow;

import org.apereo.cas.AbstractCentralAuthenticationServiceTests;
import org.apereo.cas.adaptors.trusted.config.TrustedAuthenticationConfiguration;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.test.MockRequestContext;

import static org.junit.Assert.*;

/**
 * @author Scott Battaglia
 * @since 3.0.0
 */
@Import(TrustedAuthenticationConfiguration.class)
@Slf4j
public class PrincipalFromRequestRemoteUserNonInteractiveCredentialsActionTests extends AbstractCentralAuthenticationServiceTests {

    @Autowired
    @Qualifier("principalFromRemoteUserAction")
    private Action action;

    @Test
    public void verifyRemoteUserExists() throws Exception {
        val request = new MockHttpServletRequest();
        request.setRemoteUser("test");

        val context = new MockRequestContext();
        context.setExternalContext(new ServletExternalContext(
            new MockServletContext(), request, new MockHttpServletResponse()));

        assertEquals("success", this.action.execute(context).getId());
    }

    @Test
    public void verifyRemoteUserDoesntExists() throws Exception {
        val context = new MockRequestContext();
        context.setExternalContext(new ServletExternalContext(
            new MockServletContext(), new MockHttpServletRequest(), new MockHttpServletResponse()));

        assertEquals("error", this.action.execute(context).getId());
    }
}
