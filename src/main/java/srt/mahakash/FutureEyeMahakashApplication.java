package srt.mahakash;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FutureEyeMahakashApplication {

	public static void main(String[] args) {
		// SpringApplication.run(FutureEyeMahakashApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(FutureEyeMahakashApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

}
