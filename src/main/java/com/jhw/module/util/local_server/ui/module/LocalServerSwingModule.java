package com.jhw.module.util.local_server.ui.module;

import com.clean.swing.app.DefaultAbstractSwingMainModule;
import com.jhw.module.util.local_server.services.*;

public class LocalServerSwingModule extends DefaultAbstractSwingMainModule {

    private LocalServerSwingModule() {
    }

    public static LocalServerSwingModule init() {
        System.out.println("Iniciando el modulo de Servidor local");
        try {
            LocalServerResourceService.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalServerHandler.start();
        return new LocalServerSwingModule();
    }

    @Override
    public void closeModule() {
        LocalServerHandler.close();
    }

}
