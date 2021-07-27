package br.com.easylearn.config.mailsender;

import br.com.easylearn.domain.Mail;
import javax.mail.MessagingException;

public interface SendMailService {
    void sendMail(Mail mail);
    void sendMailWithAttachments(Mail mail) throws MessagingException;
}
