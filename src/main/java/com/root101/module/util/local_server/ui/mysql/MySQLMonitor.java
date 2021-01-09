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
package com.root101.module.util.local_server.ui.mysql;

import com.root101.clean.core.app.services.NotificationHandler;
import com.root101.clean.core.app.services.NotificationsGeneralType;
import com.root101.module.util.mysql.core.domain.Configuration;
import static com.root101.module.util.mysql.core.usecase_impl.MySQLUseCaseImpl.*;
import com.root101.module.util.mysql.services.MySQLHandler;
import com.root101.module.util.mysql.services.MySQLResourceService;
import com.root101.swing.material.components.button.MaterialButton;
import com.root101.swing.material.components.button.MaterialButtonIcon;
import com.root101.swing.material.components.button.MaterialButtonsFactory;
import com.root101.swing.material.components.container.MaterialContainersFactory;
import com.root101.swing.material.components.container.layout.HorizontalLayoutContainer;
import com.root101.swing.material.components.container.layout.VerticalLayoutContainer;
import com.root101.swing.material.components.container.panel._PanelGradient;
import com.root101.swing.material.components.labels.MaterialLabel;
import com.root101.swing.material.components.labels.MaterialLabelsFactory;
import com.root101.swing.material.standards.MaterialColors;
import com.root101.swing.material.standards.MaterialFontRoboto;
import com.root101.swing.material.standards.MaterialIcons;
import com.root101.utils.interfaces.Update;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class MySQLMonitor extends _PanelGradient implements Update, PropertyChangeListener {

    private Configuration cfgMysql;

    public MySQLMonitor() {
        try {
            MySQLResourceService.init();//cargo el sistema de resources
            cfgMysql = MySQLHandler.load();
        } catch (Exception e) {
            NotificationHandler.showConfirmDialog(
                    NotificationsGeneralType.NOTIFICATION_ERROR,
                    "Error cargando configuracion de BD.\nContacte con soporte");
        }
        initComponents();
        personalize();
        addListeners();
        update();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        labelHeader = MaterialLabelsFactory.build();
        labelHeader.setText("Monitor servicio MySQL");
        labelHeader.setHorizontalAlignment(SwingConstants.CENTER);
        labelHeader.setFont(MaterialFontRoboto.BOLD.deriveFont(24f));

        labelURL = MaterialLabelsFactory.build();
        labelURL.setHorizontalAlignment(SwingConstants.CENTER);
        labelURL.setFont(MaterialFontRoboto.REGULAR.deriveFont(20f));

        buttonUpdate = MaterialButtonsFactory.buildIconTransparent();
        buttonUpdate.setIcon(MaterialIcons.UPDATE);
        buttonUpdate.setToolTipText("Actualiza el estado de la conección hacia la base de datos");

        labelRunning = MaterialLabelsFactory.build();
        labelRunning.setHorizontalAlignment(SwingConstants.CENTER);
        labelRunning.setFont(MaterialFontRoboto.BOLD.deriveFont(18f));

        panelRunning = MaterialContainersFactory.buildPanelGradient();
        panelRunning.setLayout(new BorderLayout());
        panelRunning.add(labelRunning);
        panelRunning.add(buttonUpdate, BorderLayout.EAST);

        buttonStart = MaterialButtonsFactory.buildButton();
        buttonStart.setBackground(MaterialColors.GREEN_500);
        buttonStart.setText("Iniciar");
        buttonStart.setToolTipText("Inicia la BD en caso de que este parado");
        buttonStart.setIcon(MaterialIcons.POWER_SETTINGS_NEW);

        buttonStop = MaterialButtonsFactory.buildButton();
        buttonStop.setBackground(MaterialColors.RED_500);
        buttonStop.setText("Cerrar");
        buttonStop.setToolTipText("Cerrar la BD en caso de que este corriendo");
        buttonStop.setIcon(MaterialIcons.BLOCK);

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();
        vlc.add(labelHeader);
        vlc.add(labelURL);
        vlc.add(panelRunning);

        HorizontalLayoutContainer.builder buttons = HorizontalLayoutContainer.builder();
        buttons.add(buttonStart);
        buttons.add(buttonStop);
        vlc.add(buttons.build());

        this.add(vlc.build());
    }

    private MaterialLabel labelHeader;
    private MaterialLabel labelURL;
    private MaterialLabel labelRunning;
    private JPanel panelRunning;
    private MaterialButtonIcon buttonUpdate;
    private MaterialButton buttonStart;
    private MaterialButton buttonStop;

    private void personalize() {
        labelURL.setText("http://" + cfgMysql.getIp() + ":" + cfgMysql.getPort());
    }

    @Override
    public void update() {
        boolean runningNow = MySQLHandler.isRunning();
        try {
            panelRunning.setBackground(runningNow ? MaterialColors.GREEN_200 : MaterialColors.RED_200);
            labelRunning.setText(runningNow ? "INICIADO" : "CERRADO");
        } catch (Exception e) {
        }
    }

    private void addListeners() {
        buttonStart.addActionListener((ActionEvent e) -> {
            actionStart();
        });

        buttonStop.addActionListener((ActionEvent e) -> {
            actionClose();
        });

        buttonUpdate.addActionListener((ActionEvent e) -> {
            update();
        });

        MySQLHandler.addPropertyChangeListener(this);
    }

    private void actionStart() {
        MySQLHandler.start();
    }

    private void actionClose() {
        MySQLHandler.close();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case PROPERTY_STARTED:
                update();
                break;
            case PROPERTY_CLOSED:
                update();
                break;
        }
    }
}
