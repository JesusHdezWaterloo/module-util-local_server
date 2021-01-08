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
package com.root101.module.util.local_server.ui.module;

import com.root101.clean.swing.app.AbstractSwingApplication;
import com.root101.clean.swing.app.DefaultAbstractSwingMainModule;
import com.root101.clean.swing.app.dashboard.DashBoardSimple;
import com.root101.clean.swing.app.dashboard.DashboardConstants;
import com.root101.module.util.local_server.services.LocalServerHandler;
import com.root101.module.util.local_server.services.LocalServerResourceService;
import com.root101.module.util.local_server.ui.MonitorFrame;
import com.root101.swing.material.standards.MaterialColors;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class LocalServerSwingModule extends DefaultAbstractSwingMainModule {

    private final MonitorFrame monitoFrame;

    private LocalServerSwingModule() {
        monitoFrame = MonitorFrame.from();
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
    public void register(AbstractSwingApplication app) {
        DashBoardSimple dash = app.rootView().dashboard();

        dash.addKeyValue(DashboardConstants.UP_ELEMENT,
                new AbstractAction("Monitor de Servicios",
                        LocalServerModuleNavigator.ICON_MONITOR.deriveIcon(MaterialColors.WHITE)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                monitoFrame.setVisible(true);
            }
        });
    }

    @Override
    public void closeModule() {
        LocalServerHandler.close();
    }

}
