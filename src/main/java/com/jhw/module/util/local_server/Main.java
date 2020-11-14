/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.local_server;

import com.jhw.module.util.local_server.services.LocalServerHandler;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        LocalServerHandler.init();
        System.out.println(LocalServerHandler.isRunning());
        LocalServerHandler.start();
        System.out.println(LocalServerHandler.isRunning());
        System.out.println("Esperando para cerrarlo");
        Thread.sleep(10 * 1000);
        LocalServerHandler.close();
        System.out.println(LocalServerHandler.isRunning());
    }

}
