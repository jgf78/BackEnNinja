package com.udemy.component;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskComponent")
public class TaskComponent {
	private static final Log LOG = LogFactory.getLog(TaskComponent.class);

	@Scheduled(fixedDelay = 5000) // Cada 5 segundos escribe la hora(en el main
									// de BackendninjaApplication hay que poner
									// la anotación @EnableScheduling
	public void doTask() {
		LOG.info("TIME IS: " + new Date());
	}
}
