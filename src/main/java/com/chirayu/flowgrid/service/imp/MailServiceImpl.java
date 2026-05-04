package com.chirayu.flowgrid.service.imp;

import com.chirayu.flowgrid.dto.MailDTO;
import com.chirayu.flowgrid.enums.MailType;
import com.chirayu.flowgrid.service.MailService;
import jakarta.mail.internet.MimeMessage;
import freemarker.template.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chirayu
 * @created 2026-05-03
 */

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final Configuration templateConfig;
    @Value( "${spring.mail.username}")
    private String senderEmail;
    @Value( "${success.message}")
    private String succsessMessage;
    @Value( "${failure.message}")
    private String failureMessage;

    @Override
    public String sendMail(MailDTO mailDTO) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setFrom(senderEmail);
            mimeMessageHelper.setTo(mailDTO.to());
            mimeMessageHelper.setSubject(mailDTO.category().getSubject());
            mimeMessageHelper.setText(buildMailBodyWithTemplate(mailDTO.category(), mailDTO.firstName().concat(" ").concat(mailDTO.LastName())), true);
            mailSender.send(message);
            return String.format(succsessMessage, mailDTO.to(), LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
            return String.format(failureMessage, mailDTO.to(), LocalDateTime.now());
        }
    }

    private String buildMailBodyWithTemplate(MailType category, String ownerName) {
        Map<String, String> dataModel = new HashMap<>();
        dataModel.put("ownerName", ownerName);
        Writer writer = new StringWriter();
        try {
            templateConfig.getTemplate(category.getTemplateFileName()).process(dataModel, writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return ownerName;
        }
    }
}
