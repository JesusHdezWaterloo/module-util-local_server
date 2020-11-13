package com.jhw.module.util.mysql.core.module;

import com.clean.core.app.modules.AbstractModule;
import com.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Modulo de licencia-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class MySQLCoreModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new InjectionConfigMySQLCore());

    private static MySQLCoreModule INSTANCE;

    public static MySQLCoreModule getInstance() {
        if (INSTANCE == null) {
            throw new NullPointerException("El modulo de MySQL no se ha inicializado");
        }
        return INSTANCE;
    }

    public static MySQLCoreModule init(AbstractModule repoModule) {
        INSTANCE = new MySQLCoreModule();
        INSTANCE.registerModule(repoModule);
        return getInstance();
    }

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

    @Override
    public String getModuleName() {
        return "MySQL Core Module";
    }

}
