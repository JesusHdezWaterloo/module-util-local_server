/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.local_server.services;

import com.jhw.module.util.local_server.core.domain.Configuration;
import com.jhw.module.util.local_server.core.module.LocalServerCoreModule;
import com.jhw.module.util.local_server.repo.module.LocalServerRepoModule;
import java.util.HashMap;
import java.util.Map;
import com.jhw.module.util.local_server.core.usecase_def.LocalServerUseCase;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LocalServerHandler {

    private static LocalServerUseCase LocalServerUC = LocalServerCoreModule.init(LocalServerRepoModule.init()).getImplementation(LocalServerUseCase.class);

    private LocalServerHandler() {
    }

    public static void init() {
        System.out.println("Iniciando 'Servidor Local'");
        try {
            LocalServerResourceService.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalServerHandler.start();
    }

    public static void registerMySQLService(LocalServerUseCase newService) {
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
}