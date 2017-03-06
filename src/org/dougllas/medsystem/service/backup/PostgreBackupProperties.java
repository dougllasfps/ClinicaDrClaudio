package org.dougllas.medsystem.service.backup;

/**
 * Criado por dougllas.sousa em 06/03/2017.
 */
public class PostgreBackupProperties {

    private String pgdumpPath;
    private String destino;
    private String ip;
    private String porta;
    private String banco;
    private String user;
    private String pass;

    public String getPgdumpPath() {
        return pgdumpPath;
    }

    public void setPgdumpPath(String pgdumpPath) {
        this.pgdumpPath = pgdumpPath;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
