package Coop.coop.Services;

import Coop.coop.Entities.Song;
import Coop.coop.Interfaces.SongRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    private final SongRepositoryCustom songRepository;

    @Autowired

    public SongService(SongRepositoryCustom songRepository) {
        this.songRepository = songRepository;
    }


    public Song addSong(Song song)
    {
        if (song.getTrackName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return songRepository.save(song);
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

    public boolean deleteSong(long songID) {
        Optional<Song> optionalSong = songRepository.findById(songID);
        if (optionalSong.isPresent()) {
            songRepository.deleteById(songID);
            return true;
        }
        return false;
    }

    public ResponseEntity<Song> getSong(long songID)
    {
        Optional<Song> song = songRepository.findById(songID);
        return song.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Song>> getAllSongs() {
        var songs = songRepository.findAll();
        if (songs.isEmpty()) {
            return ResponseEntity.noContent().build(); // return 204 No Content if no songs found
        }
        return ResponseEntity.ok(songs); // return 200 OK with the list of songs
    }

    public List<Song> getSongsByID(List<Long> songIds){
        return songRepository.findAllById(songIds);
    }

    public List<Song> getByEnvironment(long environmentID) {
        return songRepository.findAllByEnvironment(environmentID);
    }
}
