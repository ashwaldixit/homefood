package com.homefood.core;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Component
public class EmailService {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	VelocityEngine velocityEngine;

	@Autowired
	private UtilMessageReader messageReader;

	@Autowired
	private AppDataUtil appDataUtil;

	private void send(final Object object, String toAddress, String subject, String content, Map<String, Object> model,
			boolean isTemplateMail) {

		if (!Boolean.parseBoolean(appDataUtil.getValueByKey("email.enabled"))) {
			return;
		}

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(toAddress);
				message.setFrom(messageReader.getProperty("FROM_ADDRESS"));
				message.setSubject(subject);
				if (isTemplateMail) {
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, content, model);
					message.setText(text, true);
				} else {
					message.setText(content, true);
				}

			}
		};

		javaMailSender.send(preparator);

	}

	public void sendEmail(final Object object, String toAddress, String subject, String templatePath,
			Map<String, Object> model) {
		send(object, toAddress, subject, templatePath, model, true);
	}

	public void sendEmailWithText(final Object object, String toAddress, String subject, String text,
			Map<String, Object> model) {
		send(object, toAddress, subject, text, model, false);
	}
}
