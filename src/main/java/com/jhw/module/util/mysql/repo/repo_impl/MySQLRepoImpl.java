package com.jhw.module.util.mysql.repo.repo_impl;

import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.clean.core.domain.services.Resource;
import com.jhw.module.util.mysql.core.domain.Configuration;
import javax.inject.Inject;
import com.jhw.module.util.mysql.core.repo_def.MySQLRepo;
import com.jhw.utils.jackson.JACKSONRepoGeneral;

/**
 * Implementacion de la Interfaz {@code LicenceRepo} para manejar el
 * comportamiento de las definiciones de su interfaz
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class MySQLRepoImpl extends JACKSONRepoGeneral<Configuration> implements MySQLRepo {

    /**
     * Constructor por defecto, usado par injectar.
     */
    @Inject
    public MySQLRepoImpl() {
        super("data_base_config.json", Configuration.class);
    }

    @Override
    protected void onReadError(Exception e) {
        Notification.showConfirmDialog(NotificationsGeneralType.CONFIRM_INFO,
                Resource.getString("msg.mysql.error.read"));
    }
}
