package com.jhw.module.util.local_server.ui.module;

import com.clean.swing.app.AbstractSwingApplication;
import com.clean.swing.app.DefaultAbstractSwingMainModule;
import com.clean.swing.app.dashboard.DashBoardSimple;
import com.clean.swing.app.dashboard.DashboardConstants;
import com.jhw.module.util.local_server.services.*;
import com.jhw.module.util.local_server.ui.MonitorFrame;
import com.jhw.swing.material.standards.MaterialColors;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

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
