package com.jhw.module.util.local_server.repo.repo_impl;

import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.clean.core.domain.services.Resource;
import com.jhw.module.util.local_server.core.domain.Configuration;
import javax.inject.Inject;
import com.jhw.utils.jackson.JACKSONRepoGeneral;
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
        Notification.showConfirmDialog(NotificationsGeneralType.CONFIRM_INFO,
                Resource.getString("msg.mysql.error.read"));
    }
}
