

package com.Vinoteca.AppVinos.controller;
import com.Vinoteca.AppVinos.dto.ContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Vinoteca.AppVinos.service.EmailService;

@RestController
@RequestMapping("/contacto")
@CrossOrigin(origins = "http://localhost:9002")
public class ContactController {

    @Autowired
    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }


    @PostMapping
    public String sendContactEmail(@RequestBody ContactRequest request) {
        String subject = "Nuevo mensaje de contacto de " + request.getName();
        String text = "Email: " + request.getEmail() + "\n\nMensaje:\n" + request.getMessage();
        emailService.sendEmail("herny3154@gmail.com", subject, text);
        return "Mensaje enviado correctamente !";
    }
}



