package Coop.coop;

import Coop.coop.Entities.Plugin;
import Coop.coop.Entities.Song;
import Coop.coop.Entities.SongStatus;
import Coop.coop.Interfaces.PluginRepository;
import Coop.coop.Interfaces.SongRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class CoopApplication {

	private static final Logger log = LoggerFactory.getLogger(CoopApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(CoopApplication.class, args);
	}

	@Bean
	public CommandLineRunner addSongs(SongRepository songRepository){
		return (args) -> {
			Song song = new Song();
			song.setTrackName("Track number 1");
			song.setAuthor("Desmond");
			song.setLength(400);
			song.setStatus(SongStatus.Mastering);
			song.setDateAdded(new Date(2023-05-05));
			song.setLastModifiedBy("Desmond");
			song.setDateModified(new Date(2023-05-05));
			song.setId(1L);

			songRepository.save(song);
		};
	}

	@Bean
	public CommandLineRunner addPlugin(PluginRepository pluginRepository){
		return (args) -> {
			Plugin plugin = new Plugin();

			plugin.setAvailable(true);
			plugin.setVersion("1.0");
			plugin.setName("Omnisphere");
			plugin.setSongId(1L);
			plugin.setId(1L);

			pluginRepository.save(plugin);
		};
	}
}



