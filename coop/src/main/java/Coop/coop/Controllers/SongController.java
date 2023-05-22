package Coop.coop.Controllers;

import Coop.coop.Entities.Song;
import Coop.coop.Services.SongService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http:localhost:3000")
@RestController
@RequestMapping("/api/songs")

public class SongController
{
    final SongService songService;
    @Autowired
    public SongController(SongService songService)
    {
        this.songService = songService;
    }

    @PostMapping()
    public ResponseEntity<String> addSong(@RequestBody Song song)
    {
        try
        {
            this.songService.addSong(song);
            return new ResponseEntity<>("Song created: " + song.getTrackName(), HttpStatus.CREATED);

        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSong(@RequestBody Song song)
    {
        try{
            songService.updateSong(song);
            return new ResponseEntity<>(song.getTrackName() + " was successfully updated.", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSong(@PathVariable long id)
    {
        try
        {
            return songService.getSong(id);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSong(@PathVariable("id") long id)
    {

        try
        {
            songService.deleteSong(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Song>> getAllSongs(){
        var songs = songService.getAllSongs();
        return null;
    }
}
