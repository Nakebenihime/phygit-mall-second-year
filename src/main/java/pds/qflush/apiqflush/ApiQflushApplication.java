package pds.qflush.apiqflush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiQflushApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiQflushApplication.class, args);
	}
}
