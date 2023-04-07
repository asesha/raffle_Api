package com.alkimi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.alkimi.config.MailConfig;

@SpringBootApplication
@Import({ MailConfig.class })
public class AlkimiRaffleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlkimiRaffleApplication.class, args);
	}

}
