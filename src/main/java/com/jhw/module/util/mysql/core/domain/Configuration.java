package com.jhw.module.util.mysql.core.domain;

import com.jhw.utils.clean.EntityDomainObjectValidated;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class Configuration extends EntityDomainObjectValidated {

    private boolean startMysqlService = false;
    private String batchFolder = "C:\\meca_xampp\\xampp";

    private boolean saveDbAfterClose = true;
    private String dbSaveFolder = "salvas_bd";
    private String user = "root";
    private String pass = "";
    private int port = 7733;
    private String ip = "localhost";

    public Configuration() {
    }

    public boolean isStartMysqlService() {
        return startMysqlService;
    }

    public void setStartMysqlService(boolean startMysqlService) {
        this.startMysqlService = startMysqlService;
    }

    public String getBatchFolder() {
        return batchFolder;
    }

    public void setBatchFolder(String batchFolder) {
        this.batchFolder = batchFolder;
    }

    public boolean isSaveDbAfterClose() {
        return saveDbAfterClose;
    }

    public void setSaveDbAfterClose(boolean saveDbAfterClose) {
        this.saveDbAfterClose = saveDbAfterClose;
    }

    public String getDbSaveFolder() {
        return dbSaveFolder;
    }

    public void setDbSaveFolder(String dbSaveFolder) {
        this.dbSaveFolder = dbSaveFolder;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
