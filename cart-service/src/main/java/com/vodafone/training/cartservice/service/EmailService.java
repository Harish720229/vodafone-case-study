package com.vodafone.training.cartservice.service;

import com.vodafone.training.cartservice.domainObjects.VodafoneUser;
import com.vodafone.training.cartservice.entities.Order;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${email.sender}")
    public String emailSender;

    @Value("${email.bcc}")
    public String emailBcc;

    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(Order order,VodafoneUser vodafoneUser)
            throws MessagingException, UnsupportedEncodingException {

        String toEmail = vodafoneUser.getUserEmail();
        log.info("Email sending process has been starting, to email {}", toEmail);
        String subject = "Order confirmation for order id - " + order.getOrderId();
        MimeMessage message = javaMailSender.createMimeMessage();

        // Enable multipart mode (for adding attachments)
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.toString());

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setBcc(emailBcc);
        helper.setFrom(emailSender);

        String emailContent = order.getProduct().getProductDetails();
        emailContent = emailContent + "<br> Total Price: " + order.getTotalPrice();
        helper.setText(emailContent, true);


        javaMailSender.send(message);
        log.info("Email sending process has been finished, to email {}", toEmail);
    }
}

