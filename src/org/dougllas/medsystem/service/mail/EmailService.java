package org.dougllas.medsystem.service.mail;

import org.apache.commons.io.IOUtils;
import org.dougllas.medsystem.service.backup.PostgreBackupProperties;
import org.dougllas.medsystem.service.backup.PostgresBackupService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Criado por dougllas.sousa em 06/03/2017.
 */

@Service
public class EmailService implements Serializable{

    public void enviar(String mensagem, String to, Map<String, byte[]> anexos) throws IOException, EmailException {

        final String host = "smtp.gmail.com";
        final String username = "dougllasfps@gmail.com";
        final String password = "Doug11a$3684";

        EmailSender emailSender = new EmailSender(host, username, password);
        EmailGenerator generator = Email.create();

        generator.from("dougllasfps@gmail.com");
        generator.to("dougllasfps@gmail.com");
        generator.subject("Sistema Dr. Cláudio - Backup dos Dados da Clinica");

        generator.message("Segue anexo arquivo de backup dos dados do sistema. Obs: este email é automático.");

        if(anexos != null)
            for( String key : anexos.keySet() ){
                byte[] anexo = anexos.get(key);
                generator.attachment(key, anexo);
            }

        emailSender.doSend(generator.mail());
    }

    public static void main(String[] args) throws IOException, InterruptedException, EmailException {

//        String bkpFile = "C:\\users\\dougllas.sousa\\bkp.backup";
//
//        PostgreBackupProperties properties = new PostgreBackupProperties();
//        properties.setBanco("clinicadrclaudio");
//        properties.setDestino(bkpFile);
//        properties.setIp("localhost");
//        properties.setUser("postgres");
//        properties.setPass("postgres");
//        properties.setPgdumpPath("C:\\Program Files\\PostgreSQL\\9.2\\bin\\pg_dump.exe");
//        properties.setPorta("5432");
//
//        PostgresBackupService.realizaBackup(properties);
//
//        Map<String, byte[]> anexos = new HashMap<>();
//        byte[] bytes = IOUtils.toByteArray(new FileInputStream(new File(bkpFile)));
//        anexos.put("bkp.backup", bytes);

        EmailService service = new EmailService();

        service.enviar("sem mensagem", "dougllasfps@gmail.com", null);

        System.out.println("FOi");
    }
}