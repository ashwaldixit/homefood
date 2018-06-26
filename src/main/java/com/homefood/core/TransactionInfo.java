package com.homefood.core;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homefood.codetype.NotificationInfo;
import com.homefood.exception.HomeFoodInfoException;
import com.homefood.exception.HomeFoodRuntimeException;

@Component
@Transactional(dontRollbackOn = HomeFoodInfoException.class, rollbackOn = HomeFoodRuntimeException.class)
public class TransactionInfo {

	@Autowired
	private ErrorMessageReader messagesProperties;
	@Autowired
	private EmailService emailService;
	@Autowired
	private AppDataUtil appDataUtil;
	@Autowired
	private UtilMessageReader messageReader;

	public void generateException(String messageKey, NotificationInfo notificationinfo, int errorCode) {
		throw new HomeFoodInfoException(messagesProperties.getProperty(messageKey), notificationinfo, errorCode);
	}

	public void generateException(String messageKey, List<Object> args, NotificationInfo notificationinfo,
			int errorCode) {
		throw new HomeFoodInfoException(messagesProperties.getProperty(messageKey, args), notificationinfo, errorCode);
	}

	public void generateRuntimeException(String messageKey, NotificationInfo notificationinfo, int errorCode) {
		throw new HomeFoodRuntimeException(messagesProperties.getProperty(messageKey), notificationinfo, errorCode);
	}

	public void generateRuntimeException(String messageKey, List<Object> args, NotificationInfo notificationinfo,
			int errorCode) {
		throw new HomeFoodRuntimeException(messagesProperties.getProperty(messageKey, args), notificationinfo,
				errorCode);
	}

	public void sendErrorMail(String stackTrace) {
		String mailinglist = appDataUtil.getValueByKey("error.mailinglist");
		String[] mailToList = mailinglist.split(",");
		for (String to : mailToList) {
			emailService.sendEmailWithText(null, to, "Error in CMT", stackTrace, null);
		}

	}

}