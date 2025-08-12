package com.tienda.service;

import jakarta.mail.MessagingException;

public interface CorreoService {
    public void enviarCorreoHtml(
            String para, //A quien se le va a enviar el correo
            String asunto, //Titulo principal
            String contenidoHtml) //Cuerpo del correo
            throws MessagingException; //NO puede enviar el correo 
}

 