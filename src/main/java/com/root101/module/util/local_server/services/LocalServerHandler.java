/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.util.local_server.services;

import com.root101.module.util.local_server.core.domain.Configuration;
import com.root101.module.util.local_server.core.module.LocalServerCoreModule;
import com.root101.module.util.local_server.core.usecase_def.LocalServerUseCase;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LocalServerHandler {

    private static LocalServerUseCase LocalServerUC = LocalServerCoreModule.init().getImplementation(LocalServerUseCase.class);

    private LocalServerHandler() {
    }

    public static void registerLocalServerService(LocalServerUseCase newService) {
        LocalServerUC = newService;
    }

    public static void start() {
        LocalServerUC.start();
    }

    public static void close() {
        LocalServerUC.close();
    }

    public static Configuration load() throws Exception {
        return LocalServerUC.read();
    }

    public static boolean isRunning() {
        return LocalServerUC.isRunning();
    }

    public static void addPropertyChangeListener(PropertyChangeListener pl) {
        LocalServerUC.addPropertyChangeListener(pl);
    }

    public static void removePropertyChangeListener(PropertyChangeListener pl) {
        LocalServerUC.removePropertyChangeListener(pl);
    }
}
