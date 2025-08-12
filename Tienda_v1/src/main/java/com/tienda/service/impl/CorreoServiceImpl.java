package com.tienda.service.impl;


import com.tienda.service.CorreoService; 
import jakarta.mail.MessagingException; 
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper; 
import org.springframework.stereotype.Service;
 
@Service

public class CorreoServiceImpl implements CorreoService{
    
    @Autowired 
    private JavaMailSender mailSender;
    
    @Override
    public void enviarCorreoHtml(
            String para, //A quien se le va a enviar el correo
            String asunto, //Titulo principal
            String contenidoHtml) //Cuerpo del correo
            throws MessagingException{
    
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper 
                = new MimeMessageHelper(message, 
                        true);
        helper.setTo(para);
        helper.setSubject(asunto);
        helper.setText(contenidoHtml,true);//setteamos el valor o el texto 
        mailSender.send(message);// enviar el correo
        
    }

}  



