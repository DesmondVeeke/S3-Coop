package Coop.coop.Controllers;

import Coop.coop.Entities.Plugin;
import Coop.coop.Entities.Song;
import Coop.coop.Services.PluginService;
import Coop.coop.Services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Coop.coop.DTO.PluginDTO;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http:localhost:3000")
@RestController
@RequestMapping("/api/songs")

public class SongController
{
    final SongService songService;
    final PluginService pluginService;
    @Autowired
    public SongController(SongService songService, PluginService pluginService)
    {
        this.songService = songService;
        this.pluginService = pluginService;
    }

    @PostMapping()
    public ResponseEntity<String> addSong(@RequestBody Song song)
    {
        try
        {
            var plugins = pluginService.createListForSong(song.getPlugins());

            song.setPlugins(plugins);

            var savedSong = this.songService.addSong(song);

            pluginService.AddPluginsForSong(plugins, savedSong);

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
        try
        {
            return songService.getAllSongs();
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
