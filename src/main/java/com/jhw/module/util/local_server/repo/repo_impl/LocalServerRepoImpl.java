package com.jhw.module.util.local_server.repo.repo_impl;

import com.root101.clean.core.app.services.NotificationHandler;
import com.root101.clean.core.app.services.NotificationsGeneralType;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.jhw.module.util.local_server.core.domain.Configuration;
import javax.inject.Inject;
import com.root101.repo.json.JACKSONRepoGeneral;
import com.jhw.module.util.local_server.core.repo_def.LocalServerRepo;

/**
 * 
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LocalServerRepoImpl extends JACKSONRepoGeneral<Configuration> implements LocalServerRepo {

    /**
     * Constructor por defecto, usado par injectar.
     */
    @Inject
    public LocalServerRepoImpl() {
        super("local_server.json", Configuration.class);
    }

    @Override
    protected void onReadError(Exception e) {
        NotificationHandler.showConfirmDialog(NotificationsGeneralType.CONFIRM_INFO,
                ResourceHandler.getString("msg.mysql.error.read"));
    }
}
