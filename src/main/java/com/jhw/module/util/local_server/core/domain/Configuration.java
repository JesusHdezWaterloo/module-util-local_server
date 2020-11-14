package com.jhw.module.util.local_server.core.domain;

import com.jhw.utils.clean.EntityDomainObjectValidated;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class Configuration extends EntityDomainObjectValidated {

    private boolean startRestService = false;
    private String executable = "server\\server.war";

    private int port = 7733;

    public Configuration() {
    }

    public boolean isStartRestService() {
        return startRestService;
    }

    public void setStartRestService(boolean startRestService) {
        this.startRestService = startRestService;
    }

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
