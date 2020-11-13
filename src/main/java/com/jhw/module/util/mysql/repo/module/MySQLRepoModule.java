package com.jhw.module.util.mysql.repo.module;

import com.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Modulo de licencia-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class MySQLRepoModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new InjectionConfigMySQLRepo());

    private static MySQLRepoModule INSTANCE;

    private MySQLRepoModule() {
    }

    public static MySQLRepoModule getInstance() {
        if (INSTANCE == null) {
            throw new NullPointerException("El modulo de MySQL no se ha inicializado");
        }
        return INSTANCE;
    }

    public static MySQLRepoModule init() {
        INSTANCE = new MySQLRepoModule();
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
