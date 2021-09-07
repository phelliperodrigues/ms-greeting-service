package dev.phellipe.greetingservice.controllers;

import java.util.concurrent.atomic.AtomicLong;

import ch.qos.logback.core.joran.GenericConfigurator;
import dev.phellipe.greetingservice.config.GreetingConfig;
import dev.phellipe.greetingservice.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "%s, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private GreetingConfig config;

	
	@RequestMapping("/greeting")
	public Greeting greeting(
			@RequestParam(value="name",
			defaultValue = "") String name) {
		if (name.isEmpty()) name = config.getDefaultValue();
		return new Greeting(
					counter.incrementAndGet(),
					String.format(template,config.getGreeting(), name)
				);
	}
}
