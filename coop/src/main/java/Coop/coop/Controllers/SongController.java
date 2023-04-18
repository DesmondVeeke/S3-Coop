package Coop.coop.Controllers;

import Coop.coop.Entities.Song;
import Coop.coop.Services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http:localhost:3000")
@RestController
@RequestMapping("/api/Songs")

public class SongController
{
    final SongService songService;
    @Autowired
    public SongController(SongService songService)
    {
        this.songService = songService;
    }

    @PostMapping("/post")
    public Song addSong(@RequestBody Song song)
    {
        return songService.addSong(song);
    }

    @PutMapping("/put/{id}")
    public Boolean updateSong(@RequestBody Song song)
    {
        try{
            return songService.updateSong(song);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @GetMapping("/get/{id}")
    public Optional<Song> getSong(@PathVariable long id)
    {
        return songService.getSong(id);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteSong(@PathVariable long id)
    {
        songService.deleteSong(id);
    }
}
