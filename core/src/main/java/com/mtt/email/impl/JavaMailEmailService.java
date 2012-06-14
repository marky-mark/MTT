package com.mtt.email.impl;

import com.mtt.email.preperation.EmailPreparator;
import com.mtt.email.EmailService;
import com.mtt.email.preperation.EmailPreparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * Implementation {@link EmailService} that utilises Spring Mail helpers.
 */
@Service
public final class JavaMailEmailService implements EmailService {
//
//    protected static final SimpleLogEvent SUCCESS_EVENT = new SimpleLogEvent("EMAIL_SENT_SUCCESSFULLY", LogLevel.INFO);
//
//    protected static final SimpleLogEvent FAILURE_EVENT = new SimpleLogEvent("FAILED_TO_SEND_EMAIL", LogLevel.ERROR);

    @Autowired
    private JavaMailSender mailSender;

//    @Autowired
//    private LoggingService loggingService;

    @Override
    public void sendEmail(final EmailPreparator emailPreparator) {
        try {
            mailSender.send(new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    emailPreparator.prepareMessage(new MimeMessageHelper(mimeMessage, emailPreparator.isMultipart()));
                }
            });
//            EventLogMessage logMessage = loggingService.create(SUCCESS_EVENT, JavaMailEmailService.class);
//            logMessage.addLoggable(emailPreparator).log();
        } catch (RuntimeException ex) {
//            EventLogMessage logMessage = loggingService.create(FAILURE_EVENT, JavaMailEmailService.class);
//            logMessage.addLoggable(emailPreparator).setException(ex).log();
        }
    }
}
