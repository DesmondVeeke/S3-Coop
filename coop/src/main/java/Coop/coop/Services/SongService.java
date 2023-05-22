package Coop.coop.Services;

import Coop.coop.Entities.Song;
import Coop.coop.Interfaces.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    private final SongRepository songRepository;
@Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }
    public ResponseEntity<Song> addSong(Song song)
    {
        if (song.getTrackName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        Song savedSong = songRepository.save(song);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSong);
    }


    public Boolean updateSong(Song song) throws NoSuchFileException {


        if(song.getTrackName().isEmpty()){
            throw new IllegalArgumentException("Name of the track cannot be empty");
        }

        Optional<Song> optionalSong = songRepository.findById(song.getId());


        if(optionalSong.isPresent()){
            var oldSong = optionalSong.get();

            oldSong.setId(optionalSong.get().getId());
            oldSong.setLastModifiedBy(song.getLastModifiedBy());
            oldSong.setAuthor(song.getAuthor());
            oldSong.setLength(song.getLength());
            oldSong.setStatus(song.getStatus());
            oldSong.setTrackName(song.getTrackName());
            oldSong.setRemarks(song.getRemarks());
            oldSong.setPlugins(song.getPlugins());
            oldSong.setDateAdded(song.getDateAdded());

            songRepository.save(oldSong);
        }
        else{
            throw new NoSuchFileException("This track could not be found.");
        }
        return true;
    }

    public boolean deleteSong(long songID){
        if(songRepository.getById(songID) != null){
            songRepository.deleteById(songID);
            return true;
        }
        return false;
    }

    public ResponseEntity<Song> getSong(long songID)
    {
        Optional<Song> song = songRepository.findById(songID);
        if (song.isPresent()) {
            return ResponseEntity.ok(song.get()); // return 200 OK with the song if found
        }
        return ResponseEntity.notFound().build(); // return 404 Not Found if song not found
    }

    public ResponseEntity<List<Song>> getAllSongs() {
        var songs = songRepository.findAll();
        if (songs.isEmpty()) {
            return ResponseEntity.noContent().build(); // return 204 No Content if no songs found
        }
        return ResponseEntity.ok(songs); // return 200 OK with the list of songs
    }
}
