package tw.exmaple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import tw.exmaple.service.AdvertisementSource;

@Configuration
@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootExample {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SpringBootExample.class);
		application.run(args);
	}

	@Bean
	public AdvertisementSource tenMaxSource() {
		return new AdvertisementSource("https://beta-ssp.tenmax.io/supply/mobile/native/rmax-ad");
	}

	@Bean
	public AdvertisementSource mockSource() {
		return new AdvertisementSource("http://localhost:8080/supply/mobile/native/rmax-ad");
	}
}
