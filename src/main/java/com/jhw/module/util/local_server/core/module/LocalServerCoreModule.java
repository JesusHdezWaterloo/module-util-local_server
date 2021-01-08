package com.jhw.module.util.local_server.core.module;

import com.root101.clean.core.app.modules.AbstractModule;
import com.root101.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jhw.module.util.local_server.repo.module.LocalServerRepoModule;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LocalServerCoreModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new LocalServerCoreInjectionConfig());

    private static LocalServerCoreModule INSTANCE;

    public static LocalServerCoreModule getInstance() {
        if (INSTANCE == null) {
            throw new NullPointerException("El modulo de Local Server no se ha inicializado");
        }
        return INSTANCE;
    }


    public static LocalServerCoreModule init() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        INSTANCE = new LocalServerCoreModule();
        INSTANCE.registerModule(LocalServerRepoModule.init());
        return getInstance();
    }

    /**
     * Usar init() sin repo por parametro para usar el repo por defecto
     *
     * @param repoModule
     * @return
     * @deprecated
     */
    @Deprecated
    public static LocalServerCoreModule init(AbstractModule repoModule) {
        INSTANCE = new LocalServerCoreModule();
        INSTANCE.registerModule(repoModule);
        return getInstance();
    }

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

    @Override
    public String getModuleName() {
        return "Local Server Core Module";
    }

}
