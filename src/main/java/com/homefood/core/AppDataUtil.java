package com.homefood.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homefood.model.ApplicationData;
import com.homefood.service.ApplicationDataService;

@Component
public class AppDataUtil {

	@Autowired
	ApplicationDataService applicationDataService;

	public String getValueByKey(String key) {
		ApplicationData applicationData = applicationDataService.getByKey(key);
		if (null != applicationData) {
			return applicationData.getObsValue();
		}
		return "";
	}

}
