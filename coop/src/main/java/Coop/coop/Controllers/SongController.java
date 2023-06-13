package Coop.coop.Controllers;

import Coop.coop.DTO.MessageDTO;
import Coop.coop.Entities.Plugin;
import Coop.coop.Entities.Song;
import Coop.coop.Services.PluginService;
import Coop.coop.Services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/songs")

public class SongController
{
    final SongService songService;
    final PluginService pluginService;

    final SimpMessagingTemplate simpTemplate;
    @Autowired
    public SongController(SongService songService, PluginService pluginService, SimpMessagingTemplate simpTemplate)
    {
        this.songService = songService;
        this.pluginService = pluginService;
        this.simpTemplate = simpTemplate;
    }

    @PostMapping()
    public ResponseEntity<String> addSong(@RequestBody Song song)
    {
        try
        {
            List<Plugin> plugins = new ArrayList<>();

            if(song.getPlugins() != null){
                plugins = pluginService.createListForSong(song.getPlugins());
                song.setPlugins(plugins);
            }

            var savedSong = this.songService.addSong(song);

            if(savedSong.getPlugins() != null)
            {
                pluginService.AddPluginsForSong(plugins, savedSong);

            }

            MessageDTO message = new MessageDTO();
            message.setText("New song was added.");
            simpTemplate.convertAndSend("/topic/message", message);

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

    @PostMapping("/batch")
    public ResponseEntity<List<Song>> getSongsByIds(@RequestBody List<Long> songIds) {
        List<Song> songs = songService.getSongsByID(songIds);
        if (songs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/environment/{environmentID}")
    public ResponseEntity<List<Song>> getSongsByEnvironment(@PathVariable long environmentID) {
        List<Song> songs = songService.getByEnvironment(environmentID);
        if (songs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(songs);
    }
}
