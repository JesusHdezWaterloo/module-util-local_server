package com.jhw.module.util.local_server.ui.module;

import com.clean.swing.app.DefaultAbstractSwingMainModule;
import com.jhw.module.util.local_server.services.LocalServerHandler;

public class LocalServerSwingModule extends DefaultAbstractSwingMainModule {

    private LocalServerSwingModule() {
    }

    public static LocalServerSwingModule init() {
        System.out.println("Iniciando el modulo de Servidor local");
        LocalServerHandler.init();
        return new LocalServerSwingModule();
    }

    @Override
    public void closeModule() {
        LocalServerHandler.close();
    }

}
