package org.apereo.cas.services;

import org.apereo.cas.category.FileSystemCategory;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;
import java.util.Collection;

/**
 * This is {@link AbstractResourceBasedServiceRegistryTests}.
 *
 * @author Misagh Moayyed
 * @since 5.0.0
 */
@Slf4j
@Category(FileSystemCategory.class)
@RunWith(Parameterized.class)
public abstract class AbstractResourceBasedServiceRegistryTests extends AbstractServiceRegistryTests {
    public static final ClassPathResource RESOURCE = new ClassPathResource("services");

    protected ServiceRegistry dao;

    public AbstractResourceBasedServiceRegistryTests(final Class<? extends RegisteredService> registeredServiceClass) {
        super(registeredServiceClass);
    }

    @Parameterized.Parameters
    public static Collection<Object> getTestParameters() {
        return Arrays.asList(RegexRegisteredService.class);
    }

    @Override
    @SneakyThrows
    public void tearDownServiceRegistry() {
        FileUtils.cleanDirectory(RESOURCE.getFile());
        super.tearDownServiceRegistry();
    }

    @Test
    public void verifyServiceWithInvalidFileName() {
        val r = buildRegisteredServiceInstance(RandomUtils.nextInt());
        r.setName("hell/o@world:*");
        this.thrown.expect(IllegalArgumentException.class);
        this.dao.save(r);
    }

    @Override
    public ServiceRegistry getNewServiceRegistry() {
        return this.dao;
    }
}
