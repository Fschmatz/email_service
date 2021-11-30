package com.fschmatz.email_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    //http://localhost:9094/email/teste
    @Autowired
    private JavaMailSender mailSender;


    //UM EMAIL PRA TER UM CHAMADO UNIVERSAL
    @RequestMapping(path = "/send/{nome}/{email}/{msg}", method = RequestMethod.GET)
    public String sendMail(@PathVariable("nome") String nomeUsuario,@PathVariable("email") String emailUser,@PathVariable("msg") String mensagem) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(mensagem);
        message.setTo(emailUser);
        message.setFrom("tvsalastickmi@gmail.com");

        //--------- TRATAMENTO DO SUBJECT

        System.out.println(mensagem);
        if(mensagem.contains("cancelada.")){
            message.setSubject("Fschmatz Eventos LLC - Confirmação de Cancelamento");
        }
        else if(mensagem.contains("confirmada.")){
            message.setSubject("Fschmatz Eventos LLC - Inscrição Confirmada");
        }
        else if(mensagem.contains("certificado.")){
            message.setSubject("Fschmatz Eventos LLC - Link Validação");
        }

        //---------

        try {
            System.out.println("Disparou email");
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }




// CHAMADOS SEPARADOS

    @RequestMapping(path = "/cancelamento/{nome}", method = RequestMethod.GET)
    public String sendMailCancelamento(@PathVariable("nome") String nomeUsuario) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Você cancelou, "+nomeUsuario);
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
    public String sendMailInscricao(@PathVariable("nome") String nomeUsuario) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Você se inscreveu "+nomeUsuario);
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
    public String sendMailCertificado(@PathVariable("nome") String nomeUsuario) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Seu certificado "+nomeUsuario);
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
