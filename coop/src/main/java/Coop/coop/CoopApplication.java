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

	public static void main(String[] args) {

		SpringApplication.run(CoopApplication.class, args);
	}

	@Bean
	public CommandLineRunner addSongs(SongRepository songRepository, PluginRepository pluginRepository){
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
			song.setEnvironment(1);
			song.setAuthorid(1);

			songRepository.save(song);

			song = new Song();
			song.setTrackName("Farma");
			song.setAuthor("Brian");
			song.setLength(300);
			song.setStatus(SongStatus.Mix);
			song.setDateAdded(new Date(2023-05-05));
			song.setLastModifiedBy("Brian");
			song.setDateModified(new Date(2023-05-05));
			song.setId(2L);
			song.setEnvironment(1);
			song.setAuthorid(2);

			songRepository.save(song);

			song = new Song();
			song.setTrackName("Dog's song");
			song.setAuthor("Big Dog");
			song.setLength(150);
			song.setStatus(SongStatus.Production);
			song.setDateAdded(new Date(2023-05-05));
			song.setLastModifiedBy("Big dog");
			song.setDateModified(new Date(2023-05-05));
			song.setId(3L);
			song.setEnvironment(1);
			song.setAuthorid(1);

			songRepository.save(song);

			song = new Song();
			song.setTrackName("Desmond is echt artistiek zeg");
			song.setAuthor("Desmond");
			song.setLength(1500);
			song.setStatus(SongStatus.Mastering);
			song.setDateAdded(new Date(2023-05-05));
			song.setLastModifiedBy("Big Bri");
			song.setDateModified(new Date(2023-05-05));
			song.setId(4L);
			song.setEnvironment(2);
			song.setAuthorid(4);

			songRepository.save(song);

			Plugin plugin = new Plugin();

			plugin.setAvailable(true);
			plugin.setVersion("1.0");
			plugin.setName("Omnisphere");
			plugin.setSong(song);

			pluginRepository.save(plugin);

			plugin = new Plugin();

			plugin.setAvailable(true);
			plugin.setVersion("5.3");
			plugin.setName("Soundtoys");
			plugin.setSong(song);

			pluginRepository.save(plugin);

			plugin = new Plugin();

			plugin.setAvailable(true);
			plugin.setVersion("X.X");
			plugin.setName("Ozone");

			pluginRepository.save(plugin);

			plugin = new Plugin();

			plugin.setAvailable(false);
			plugin.setVersion("");
			plugin.setName("Corrupt");

			pluginRepository.save(plugin);
		};
	}

}



