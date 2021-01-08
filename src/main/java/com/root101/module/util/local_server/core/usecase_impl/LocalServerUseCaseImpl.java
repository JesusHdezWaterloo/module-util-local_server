package com.jhw.module.util.local_server.core.usecase_impl;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.clean.core.app.services.NotificationHandler;
import com.root101.clean.core.app.services.NotificationsGeneralType;
import com.root101.clean.core.app.usecase.DefaultReadWriteUseCase;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.jhw.module.util.local_server.core.domain.Configuration;
import com.jhw.module.util.local_server.core.module.LocalServerCoreModule;
import com.root101.utils.others.Network;
import com.jhw.module.util.local_server.core.repo_def.LocalServerRepo;
import com.jhw.module.util.local_server.core.usecase_def.LocalServerUseCase;
import static com.jhw.module.util.local_server.core.utils.PreparedURLs.*;
import java.util.concurrent.TimeoutException;
import org.springframework.web.client.RestTemplate;

public class LocalServerUseCaseImpl extends DefaultReadWriteUseCase<Configuration> implements LocalServerUseCase {

    public static final String PROPERTY_STARTED = "rest_started";
    public static final String PROPERTY_CLOSED = "rest_closed";

    public static final String MSG_STARTED = "msg.local_server.success.started";
    public static final String MSG_NO_STARTED = "msg.local_server.error.no_start";
    public static final String MSG_CLOSED = "msg.local_server.success.closed";
    public static final String MSG_NO_CLOSED = "msg.local_server.error.no_close";
    public static final String MSG_TIMEOUT = "msg.local_server.error.timeout";

    public static final int TIME_WAIT_SERVER = 120;
    /**
     * Instancia del repo para almacenar las cosas en memoria
     */
    private final LocalServerRepo repo = LocalServerCoreModule.getInstance().getImplementation(LocalServerRepo.class);

    /**
     * Constructor por defecto, usado par injectar.
     */
    public LocalServerUseCaseImpl() {
        super.setRepo(repo);
    }

    @Override
    public void start() {
        try {
            Configuration cfg = read();
            if (!cfg.isStartRestService()) {
                System.out.println("El FLAG para iniciar el servicio REST está desactivado");
            } else if (isRunning()) {
                System.out.println("El servicio REST ya esta corriendo");
            } else {//si hay que iniciar y no esta corriendo
                String cmd = "java -jar " + cfg.getExecutable();

                System.out.println("Iniciando el servicio con: " + cmd);

                Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", cmd});

                //check if start
                int millis = 1 * 1000;
                for (int i = 0; i < TIME_WAIT_SERVER; i++) {
                    System.out.println("Iniciando el servicio (" + (TIME_WAIT_SERVER - i) + " sec.)");
                    if (isRunning()) {
                        System.out.println("EL SERVICIO SE HA INICIADO EXITOSAMENTE");
                        NotificationHandler.showNotification(NotificationsGeneralType.NOTIFICATION_SUCCESS,
                                ResourceHandler.getString(MSG_STARTED));
                        firePropertyChange(PROPERTY_STARTED, false, true);
                        return;
                    } else {
                        Thread.sleep(millis);
                    }
                }
                throw new TimeoutException(ResourceHandler.getString(MSG_TIMEOUT));
            }
        } catch (TimeoutException ex) {
            ExceptionHandler.handleException(ex);
        } catch (Exception e) {
            Exception ex = new Exception(ResourceHandler.getString(MSG_NO_STARTED));
            ex.setStackTrace(e.getStackTrace());
            ExceptionHandler.handleException(ex);
        }
    }

    @Override
    public void close() {
        try {
            Configuration cfg = read();
            if (!cfg.isStartRestService()) {
                System.out.println("El FLAG para cerrar el servicio REST está desactivado");
            } else if (!isRunning()) {
                System.out.println("El servicio REST NO esta corriendo");
            } else {
                System.out.println("Cerrando el servicio REST");

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
            firePropertyChange(PROPERTY_CLOSED, false, true);
            NotificationHandler.showNotification(NotificationsGeneralType.NOTIFICATION_SUCCESS,
                    ResourceHandler.getString(MSG_CLOSED));
        } catch (Exception e) {
            System.out.println("No se cerro el servicio");
            Exception ex = new Exception(ResourceHandler.getString(MSG_NO_CLOSED));
            ex.setStackTrace(e.getStackTrace());
            ExceptionHandler.handleException(ex);
        }
    }

    @Override
    public boolean isRunning() {
        try {
            Configuration cfg = read();
            return Network.isRunning(LOCALHOST, cfg.getPort());
        } catch (Exception e) {
            return false;
        }
    }

}
