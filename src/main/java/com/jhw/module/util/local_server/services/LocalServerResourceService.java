package com.jhw.module.util.local_server.services;

import com.clean.core.domain.services.Resource;
import com.clean.core.domain.services.ResourceBundleUtils;
import com.clean.core.domain.services.ResourceService;
import com.clean.core.domain.services.DefaultResourceBundleService;
import java.net.MalformedURLException;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LocalServerResourceService implements ResourceService {

    public static final String RESOURCE_URL = "mysql";

    private final DefaultResourceBundleService resourceService;

    public static LocalServerResourceService init() throws MalformedURLException {
        LocalServerResourceService res = new LocalServerResourceService();
        Resource.registerResourceService(res);
        return res;
    }

    private LocalServerResourceService() throws MalformedURLException {
        resourceService = new DefaultResourceBundleService(
                ResourceBundleUtils.fromInternalFile(RESOURCE_URL,
                        ResourceBundleUtils.SPANISH));
    }

    @Override
    public String getString(String string) {
        return resourceService.getString(string);
    }

    @Override
    public Object getObject(String string) {
        return resourceService.getObject(string);
    }

    @Override
    public boolean contain(String string) {
        return resourceService.contain(string);
    }
}
