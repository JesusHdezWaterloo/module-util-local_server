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
import com.jhw.module.util.local_server.core.repo_def.LocalServerRepo;
import com.jhw.module.util.local_server.core.usecase_def.LocalServerUseCase;
import static com.jhw.module.util.local_server.core.utils.PreparedURLs.*;
import java.util.concurrent.TimeoutException;
import org.springframework.web.client.RestTemplate;

public class LocalServerUseCaseImpl extends DefaultReadWriteUseCase<Configuration> implements LocalServerUseCase {

    public static final String MSG_STARTED = "msg.local_server.success.started";
    public static final String MSG_NO_STARTED = "msg.local_server.error.no_start";
    public static final String MSG_CLOSED = "msg.local_server.success.closed";
    public static final String MSG_NO_CLOSED = "msg.local_server.error.no_close";
    public static final String MSG_TIMEOUT = "msg.local_server.error.timeout";

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

                System.out.println("Iniciando el servicio en con: " + cmd);

                Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", cmd});

                //check if start
                int millis = 3 * 1000;
                for (int i = 0; i < 10; i++) {
                    System.out.println("Checkeando que se haya iniciado el servicio");
                    if (isRunning()) {
                        System.out.println("EL SERVICIO SE HA INICIADO EXITOSAMENTE");
                        Notification.showNotification(NotificationsGeneralType.NOTIFICATION_SUCCESS,
                                Resource.getString(MSG_STARTED));
                        return;
                    } else {
                        System.out.println("No se ha iniciado el servicio, probando nuevamente en " + millis + " millisegundos");
                        Thread.sleep(millis);
                    }
                }
                throw new TimeoutException(Resource.getString(MSG_TIMEOUT));
            }
        } catch (TimeoutException ex) {
            ExceptionHandler.handleException(ex);
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
                System.out.println("Cerrando el servicio");

                //ejecuto un get a la url de close
                new RestTemplate().getForEntity(CLOSE, String.class);

                if (isRunning()) {
                    throw new Exception();
                }
            }
        } catch (org.springframework.web.client.ResourceAccessException ok) {
            //si da esta excepcion es xq antes habia algo corriendo en el puerto xq entro al if
            //y luego cerro el servicio y dio el error al tratar de leer
            //por lo tanto, si da esta excepcion es que lo cerro bien
            System.out.println("Error al conectarse al servicio, por lo tanto se cerro exitosamente");
            Notification.showNotification(NotificationsGeneralType.NOTIFICATION_SUCCESS,
                    Resource.getString(MSG_CLOSED));
        } catch (Exception e) {
            System.out.println("No se cerro el servicio");
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
