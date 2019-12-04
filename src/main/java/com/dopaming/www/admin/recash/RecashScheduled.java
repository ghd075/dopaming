package com.dopaming.www.admin.recash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RecashScheduled {

	@Autowired
	ReCashService reCashService;

	@Scheduled(fixedDelay = 60000)
	public void recasher() {
		reCashService.recashing();
	}
}
