/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.local_server.core.utils;

import com.jhw.module.util.local_server.core.domain.Configuration;
import com.jhw.module.util.local_server.services.LocalServerHandler;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class PreparedURLs {

    private static Configuration cfg = null;

    static {
        try {
            cfg = LocalServerHandler.load();
        } catch (Exception e) {
        }
    }

    public static final String LOCALHOST = "localhost";
    public static final String CLOSE = "http://" + LOCALHOST + ":" + cfg.getPort() + "/admin/close";
}
