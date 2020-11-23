/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.local_server;

import com.jhw.module.util.local_server.services.LocalServerHandler;
import com.jhw.module.util.local_server.ui.MonitorFrame;
import com.jhw.module.util.local_server.ui.module.LocalServerSwingModule;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        LocalServerSwingModule.init();
        MonitorFrame monitor = MonitorFrame.from();
        monitor.setVisible(true);
    }

}
