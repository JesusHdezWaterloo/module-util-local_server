package com.jhw.module.util.mysql.core.module;

import com.google.inject.AbstractModule;
import com.jhw.module.util.mysql.core.usecase_impl.MySQLUseCaseImpl;
import com.jhw.module.util.mysql.core.usecase_def.MySQLUseCase;

/**
 * Configuracion del injection del modulo de licencia-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class InjectionConfigMySQLCore extends AbstractModule {

    @Override
    protected void configure() {
        bind(MySQLUseCase.class).to(MySQLUseCaseImpl.class)/*.in(Singleton.class)*/;
    }

}
