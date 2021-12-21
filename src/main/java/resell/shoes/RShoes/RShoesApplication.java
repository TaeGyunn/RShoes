package resell.shoes.RShoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RShoesApplication {

	private static final String PROPERTIES =
			"spring.config.location="
					+"classpath:/application.yml"
			+",classpath:/security.yml";

	public static void main(String[] args) {

//		SpringApplication.run(RShoesApplication.class, args);
		new SpringApplicationBuilder(RShoesApplication.class)
				.properties(PROPERTIES)
				.run(args);
	}

}
