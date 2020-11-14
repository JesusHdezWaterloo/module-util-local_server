package com.jhw.module.util.local_server.core.usecase_impl;

import com.clean.core.app.services.ExceptionHandler;
import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.clean.core.app.usecase.DefaultReadWriteUseCase;
import com.clean.core.domain.services.Resource;
import com.jhw.module.util.local_server.core.domain.Configuration;
import com.jhw.module.util.local_server.core.module.LocalServerCoreModule;
import javax.inject.Inject;
import com.jhw.utils.others.Red;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.jhw.module.util.local_server.core.repo_def.LocalServerRepo;
import com.jhw.module.util.local_server.core.usecase_def.LocalServerUseCase;
import org.springframework.web.client.RestTemplate;

public class LocalServerUseCaseImpl extends DefaultReadWriteUseCase<Configuration> implements LocalServerUseCase {

    public static final String LOCALHOST = "http://localhost";

    public static final String NOTIFICATION_SALVA_DB = "notification.mysql.saved";
    public static final String MSG_SAVED = "msg.mysql.success.saved_db";
    public static final String MSG_NO_SAVED = "msg.mysql.error.no_save";
    public static final String MSG_STARTED = "msg.mysql.success.started";
    public static final String MSG_NO_STARTED = "msg.mysql.error.no_start";
    public static final String MSG_CLOSED = "msg.mysql.success.closed";
    public static final String MSG_NO_CLOSED = "msg.mysql.error.no_close";

    /**
     * Instancia del repo para almacenar las cosas en memoria
     */
    private final LocalServerRepo repo = LocalServerCoreModule.getInstance().getImplementation(LocalServerRepo.class);

    /**
     * Constructor por defecto, usado par injectar.
     */
    @Inject
    public LocalServerUseCaseImpl() {
        super.setRepo(repo);
    }

    @Override
    public void start() {
        try {
            Configuration cfg = read();
            if (cfg.isStartRestService() && !isRunning()) {//si hay que iniciar y no esta corriendo
                String cmd = "java -jar " + cfg.getExecutable();
                int resp = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", cmd}).waitFor();
                Thread.sleep(5 * 1000);//para qeu le de tiempo de verdad a arrancar, no hace falta, pero no sobra
                if (resp == 0) {
                    Notification.showNotification(NotificationsGeneralType.NOTIFICATION_SUCCESS,
                            Resource.getString(MSG_STARTED));
                }
            }
        } catch (Exception e) {
            Exception ex = new Exception(Resource.getString(MSG_NO_STARTED));
            ex.setStackTrace(e.getStackTrace());
            ExceptionHandler.handleException(ex);
        }
    }

    @Override
    public void close() {
        try {
            Configuration cfg = read();
            if (cfg.isStartRestService() && isRunning()) {//si hay que cerrar, y esta corriendo
                //ejecuto un get a la url de close
                new RestTemplate().getForEntity(LOCALHOST + ":" + cfg.getPort() + "/admin/close", String.class);
            }
        } catch (Exception e) {
            Exception ex = new Exception(Resource.getString(MSG_NO_CLOSED));
            ex.setStackTrace(e.getStackTrace());
            ExceptionHandler.handleException(ex);
        }
    }

    @Override
    public boolean isRunning() {
        try {
            Configuration cfg = read();
            return Red.isRunning(LOCALHOST, cfg.getPort());
        } catch (Exception e) {
            return false;
        }
    }

}
