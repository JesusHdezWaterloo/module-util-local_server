/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.mysql.services;

import com.jhw.module.util.mysql.core.domain.Configuration;
import com.jhw.module.util.mysql.core.module.MySQLCoreModule;
import com.jhw.module.util.mysql.core.usecase_def.MySQLUseCase;
import com.jhw.module.util.mysql.repo.module.MySQLRepoModule;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class MySQLHandler {

    private static MySQLUseCase MySQLUC = MySQLCoreModule.init(MySQLRepoModule.init()).getImplementation(MySQLUseCase.class);

    private MySQLHandler() {
    }

    public static void init() {
        System.out.println("Iniciando 'Base de Datos'");
        try {
            MySQLResourceService.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MySQLHandler.start();
    }

    public static void registerMySQLService(MySQLUseCase newService) {
        MySQLUC = newService;
    }

    public static void start() {
        MySQLUC.start();
    }

    public static void close() {
        MySQLUC.close();
    }

    public static void save(String DB_name, String... tables) {
        MySQLUC.save(DB_name, tables);
    }

    public static Configuration load() throws Exception {
        return MySQLUC.read();
    }

    public static boolean isRunning() {
        return MySQLUC.isRunning();
    }

    public static Map propertiesMap(String DB_Name) throws Exception {
        Configuration cfg = load();
        HashMap map = new HashMap();
        String url = "jdbc:mysql://" + cfg.getIp() + ":" + cfg.getPort() + "/" + DB_Name + "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
        map.put("javax.persistence.jdbc.url", url);
        map.put("javax.persistence.jdbc.user", cfg.getUser());
        map.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        map.put("javax.persistence.jdbc.password", cfg.getPass());
        return map;
    }

}
