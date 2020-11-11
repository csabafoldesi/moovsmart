package com.progmasters.moovsmart.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@EnableAsync
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private static final String EMAIL_ACTIVATION_TEMPLATE_NAME = "html/email-activation";
    private static final String EMAIL_NEWPASSWORD_TEMPLATE_NAME = "html/email-newpassword";

    private JavaMailSender javaMailSender;
    private TemplateEngine htmlTemplateEngine;

    @Value("${spring.mail.username}")
    private String MESSAGE_FROM;

    @Value("${spring.mail.url-activate}")
    private String URL_ACTIVATE;

    @Value("${spring.mail.url-newpassword}")
    private String URL_NEWPASSWORD;

    @Autowired
    public EmailService(JavaMailSender javaMailSender,
                        @Qualifier("emailTemplateEngine") TemplateEngine htmlTemplateEngine) {
        this.javaMailSender = javaMailSender;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }

    @Async
    public void sendActivationMail(String fullName, final String emailAddress, String activationKey) {
        try {
            final Context ctx = new Context();
            ctx.setVariable("fullName", fullName);
            ctx.setVariable("url", this.URL_ACTIVATE + activationKey);

            final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
            message.setSubject("Account activation");
            message.setFrom(this.MESSAGE_FROM);
            message.setTo(emailAddress);

            final String htmlContent = this.htmlTemplateEngine.process(EMAIL_ACTIVATION_TEMPLATE_NAME, ctx);
            message.setText(htmlContent, true /* isHtml */);

            this.javaMailSender.send(mimeMessage);
            logger.info("Email successfully sent to {}", emailAddress);
        } catch (MessagingException e) {
            logger.error("Email sending FAILED to {}", emailAddress, e);
        }

    }

    @Async
    public void sendNewPasswordMail(String fullName, String emailAddress, String newPasswordKey) {
        try {
            final Context ctx = new Context();
            ctx.setVariable("fullName", fullName);
            ctx.setVariable("url", this.URL_NEWPASSWORD + newPasswordKey);

            final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
            message.setSubject("New password");
            message.setFrom(this.MESSAGE_FROM);
            message.setTo(emailAddress);

            final String htmlContent = this.htmlTemplateEngine.process(EMAIL_NEWPASSWORD_TEMPLATE_NAME, ctx);
            message.setText(htmlContent, true /* isHtml */);

            this.javaMailSender.send(mimeMessage);
            logger.info("Email successfully sent to {}", emailAddress);
        } catch (MessagingException e) {
            logger.error("Email sending FAILED to {}", emailAddress, e);
        }
    }
}
