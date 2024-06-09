package com.akademicu.albums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AlbumsApplication {

	private final static Logger log = LoggerFactory.getLogger(AlbumsApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(AlbumsApplication.class, args);
		log.info("Somthing old");
	}

}
