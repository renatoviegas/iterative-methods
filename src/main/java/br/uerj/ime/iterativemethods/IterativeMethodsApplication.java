package br.uerj.ime.iterativemethods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.uerj.ime.iterativemethods.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class IterativeMethodsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IterativeMethodsApplication.class, args);
	}

}
