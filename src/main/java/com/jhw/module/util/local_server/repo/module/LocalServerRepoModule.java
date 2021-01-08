package com.jhw.module.util.local_server.repo.module;

import com.root101.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Modulo de licencia-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LocalServerRepoModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new LocalServerRepoInjectionConfig());

    private static LocalServerRepoModule INSTANCE;

    private LocalServerRepoModule() {
    }

    public static LocalServerRepoModule getInstance() {
        if (INSTANCE == null) {
            throw new NullPointerException("El modulo de MySQL no se ha inicializado");
        }
        return INSTANCE;
    }

    public static LocalServerRepoModule init() {
        INSTANCE = new LocalServerRepoModule();
        return getInstance();
    }

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

    @Override
    public String getModuleName() {
        return "MySQL Repo Module";
    }

}
