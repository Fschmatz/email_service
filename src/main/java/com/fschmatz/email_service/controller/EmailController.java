package com.fschmatz.email_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    //http://localhost:9094/email/teste
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(path = "/cancelamento", method = RequestMethod.GET)
    public String sendMailCancelamento() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Você cancelou");
        message.setTo("tvsalastickmi@gmail.com");
        message.setFrom("tvsalastickmi@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }

    @RequestMapping(path = "/inscricao/{nome}", method = RequestMethod.GET)
    public String sendMailInscricao() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Você se inscreveu");
        message.setTo("tvsalastickmi@gmail.com");
        message.setFrom("tvsalastickmi@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }

    @RequestMapping(path = "/certificado/{nome}", method = RequestMethod.GET)
    public String sendMailCertificado() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Seu certificado");
        message.setTo("tvsalastickmi@gmail.com");
        message.setFrom("tvsalastickmi@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
}
