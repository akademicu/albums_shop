package com.akademicu.albums;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlbumsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumsApplication.class, args);
	}


//	@Bean
//	public OpenAPI todoApiInfo() {
//		return new OpenAPI()
//				.info(new Info().title("Todo list")
//						.description("Need a todo list? This is the API for you! \uD83D\uDE3A")
//						.version("v1")
//						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
//	}
}
