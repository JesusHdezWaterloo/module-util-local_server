package com.jhw.module.util.local_server.repo.module;

import com.jhw.module.util.local_server.repo.repo_impl.LocalServerRepoImpl;
import com.google.inject.AbstractModule;
import com.jhw.module.util.local_server.core.repo_def.LocalServerRepo;

/**
 * Configuracion del injection del modulo de licencia-repo.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LocalServerRepoInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(LocalServerRepo.class).to(LocalServerRepoImpl.class);
    }

}
