package Coop.coop;

import Coop.coop.Entities.Song;
import Coop.coop.Interfaces.SongRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoopApplication {

	private static final Logger log = LoggerFactory.getLogger(CoopApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(CoopApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(SongRepository repository){
		return (args) -> {
			repository.save(new Song());
		};
	}
}



