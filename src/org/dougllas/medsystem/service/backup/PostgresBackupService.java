package org.dougllas.medsystem.service.backup;

import org.dougllas.medsystem.service.backup.PostgreBackupProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PostgresBackupService {

//    "C:\Program Files\PostgreSQL\9.2\bin\pg_dump.exe

    public static void realizaBackup(PostgreBackupProperties properties) throws IOException, InterruptedException{
        realizaBackup(properties.getPgdumpPath(), properties.getDestino(), properties.getIp(),properties.getPorta(),properties.getBanco(),properties.getUser(),properties.getPass());
    }

    public static void realizaRestore() throws IOException, InterruptedException{
        final List<String> comandos = new ArrayList<String>();
        comandos.add("C:\\Arquivos de programas\\PostgreSQL\\9.1\\bin\\pg_restore.exe"); //testado no windows xp
        comandos.add("-i");
        comandos.add("-h");
        comandos.add("localhost");    //ou   comandos.add("192.168.0.1");
        comandos.add("-p");
        comandos.add("5432");
        comandos.add("-U");
        comandos.add("NOME DO USUARIO");
        comandos.add("-d");
        comandos.add("NOME DO BANCO");
        comandos.add("-v");
        comandos.add("C:\\bkp.backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.
        ProcessBuilder pb = new ProcessBuilder(comandos);
        pb.environment().put("PGPASSWORD", "SUA SENHA");     //Somente coloque sua senha
        try {
            final Process process = pb.start();
            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
            while (line != null) {
                System.err.println(line);
                line = r.readLine();
            }
            r.close();
            process.waitFor();
            process.destroy();
            JOptionPane.showMessageDialog(null,"Restore realizado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public static void realizaBackup(String pgdumpPath, String destino, String ip, String porta, String banco, String user, String pass) throws IOException, InterruptedException {
        final List<String> comandos = new ArrayList<String>();

        comandos.add(pgdumpPath);
        comandos.add("-i");
        comandos.add("-h");
        comandos.add(ip);
        comandos.add("-p");
        comandos.add(porta);
        comandos.add("-U");
        comandos.add(user);
        comandos.add("-F");
        comandos.add("c");
        comandos.add("-b");
        comandos.add("-v");
        comandos.add("-f");

        comandos.add(destino);
        comandos.add(banco);
        ProcessBuilder pb = new ProcessBuilder(comandos);
        pb.environment().put(user, pass);

        try {
            final Process process = pb.start();
            final BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
            while (line != null) {
                System.err.println(line);
                line = r.readLine();
            }
            r.close();
            process.waitFor();
            process.destroy();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        PostgreBackupProperties properties = new PostgreBackupProperties();
        properties.setBanco("clinicadrclaudio");
        properties.setDestino("C:\\users\\dougllas.sousa\\bkp.backup");
        properties.setIp("localhost");
        properties.setUser("postgres");
        properties.setPass("postgres");
        properties.setPgdumpPath("C:\\Program Files\\PostgreSQL\\9.2\\bin\\pg_dump.exe");
        properties.setPorta("5432");

        realizaBackup(properties);
        System.out.println("ok! realizado");


    }
}