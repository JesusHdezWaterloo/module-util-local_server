package com.jhw.module.util.local_server.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.jhw.module.util.local_server.core.usecase_impl.LocalServerUseCaseImpl;
import com.jhw.module.util.local_server.core.usecase_def.LocalServerUseCase;

/**
 * 
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LocalServerCoreInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(LocalServerUseCase.class).to(LocalServerUseCaseImpl.class).in(Singleton.class);
    }

}
