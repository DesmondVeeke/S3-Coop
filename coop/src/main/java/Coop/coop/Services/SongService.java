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
    public Song addSong(Song song){
        return songRepository.save((song));
    }

    public Boolean updateSong(Song song){

    Song oldSong = new Song();
    Optional<Song> optionalSong = songRepository.findById(song.getId());

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

    public void deleteSong(long songID){

        songRepository.deleteById(songID);
    }

    public Optional<Song> getSong(long songID){

        return songRepository.findById(songID);
    }
}
