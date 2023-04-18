package Coop.coop.Services;

import Coop.coop.Entities.Song;
import Coop.coop.Interfaces.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService {
    private final SongRepository songRepository;
@Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }
    public Song addSong(Song song)
    {
        if(song.getTrackName().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return songRepository.save(song);
    }

    public Boolean updateSong(Song song){

    Song oldSong = new Song();
    Optional<Song> optionalSong = songRepository.findById(song.getId());

        if(song.getTrackName().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if(optionalSong.isPresent()){
            oldSong = optionalSong.get();

            oldSong.setId(optionalSong.get().getId());
            oldSong.setLastModifiedBy(optionalSong.get().getLastModifiedBy());
            oldSong.setAuthor(optionalSong.get().getAuthor());
            oldSong.setLength(optionalSong.get().getLength());
            oldSong.setStatus(optionalSong.get().getStatus());
            oldSong.setTrackName(optionalSong.get().getTrackName());
            oldSong.setRemarks(optionalSong.get().getRemarks());
            oldSong.setPlugins(optionalSong.get().getPlugins());
            oldSong.setDateAdded(optionalSong.get().getDateAdded());

            songRepository.save(oldSong);
        }
        else{
            return false;
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

    public Optional<Song> getSong(long songID){

        return songRepository.findById(songID);
    }
}
