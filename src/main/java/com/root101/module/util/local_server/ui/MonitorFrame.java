/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.module.util.local_server.ui;

import com.root101.module.util.local_server.ui.module.LocalServerModuleNavigator;
import com.root101.module.util.local_server.ui.mysql.MySQLMonitor;
import com.root101.module.util.local_server.ui.rest.RESTMonitor;
import com.root101.swing.material.components.container.MaterialContainersFactory;
import com.root101.swing.util.Utils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class MonitorFrame extends JFrame {

    public static MonitorFrame from() {
        return new MonitorFrame();
    }

    public MonitorFrame() {
        initComponents();
        personalize();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        this.setTitle("Monitor");
        this.setIconImage(LocalServerModuleNavigator.ICON_MONITOR.getImage());

        Rectangle screen = Utils.getScreenSize();
        this.setSize(new Dimension((int) screen.getWidth() / 4, (int) screen.getHeight() / 3));
        this.setResizable(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        tabbed = MaterialContainersFactory.buildTabbedHeader();
        this.add(tabbed);
    }
    private JTabbedPane tabbed;

    private void personalize() {
        tabbed.addTab("REST", new RESTMonitor());
        tabbed.addTab("MySQL", new MySQLMonitor());
    }
}
