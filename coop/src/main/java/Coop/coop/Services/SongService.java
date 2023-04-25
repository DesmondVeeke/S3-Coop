package Coop.coop.Services;

import Coop.coop.Entities.Song;
import Coop.coop.Interfaces.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.NoSuchFileException;
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

    public Optional<Song> getSong(long songID)
    {
        return songRepository.findById(songID);
    }
}
