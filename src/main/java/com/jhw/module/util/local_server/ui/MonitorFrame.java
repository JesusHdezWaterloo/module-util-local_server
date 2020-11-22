/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.local_server.ui;

import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.jhw.module.util.local_server.core.domain.Configuration;
import com.jhw.module.util.local_server.services.LocalServerHandler;
import com.jhw.module.util.local_server.ui.module.LocalServerModuleNavigator;
import com.jhw.module.util.local_server.ui.rest.RESTMonitor;
import com.jhw.swing.material.components.container.MaterialContainersFactory;
import com.jhw.swing.util.Utils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class MonitorFrame extends JFrame {

    public static MonitorFrame from() {
        return new MonitorFrame();
    }
    
    private Configuration cfg;

    public MonitorFrame() {
        initComponents();
        try {
            cfg = LocalServerHandler.load();
        } catch (Exception e) {
            Notification.showConfirmDialog(
                    NotificationsGeneralType.NOTIFICATION_ERROR,
                    "Error cargando configuracion de servidor local.\nContacte con soporte");
        }
        personalize();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        this.setTitle("Monitor");
        this.setIconImage(LocalServerModuleNavigator.ICON_MONITOR.getImage());

        Rectangle screen = Utils.getScreenSize();
        this.setSize(new Dimension((int) screen.getWidth() / 2, (int) screen.getHeight() / 2));
        this.setResizable(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        tabbed = MaterialContainersFactory.buildTabbedHeader();
        this.add(tabbed);
    }
    private JTabbedPane tabbed;

    private void personalize() {
        tabbed.addTab("REST", new RESTMonitor(cfg));
    }
}
