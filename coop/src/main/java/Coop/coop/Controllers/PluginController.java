package Coop.coop.Controllers;

import Coop.coop.Entities.Plugin;
import Coop.coop.Services.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http:localhost:3000")
@RestController
@RequestMapping("/api/plugins")
public class PluginController {

    final PluginService pluginService;

    @Autowired
    public PluginController(PluginService pluginService){
        this.pluginService = pluginService;
    }

    @PostMapping()
    public ResponseEntity<String> addPlugin(@RequestBody Plugin plugin)
    {
        try
        {
            pluginService.addPlugin(plugin);
            return new ResponseEntity<>("The plugin " + plugin.getName() + " was created.", HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlugin(@RequestBody Plugin plugin)
    {
        try
        {
            pluginService.updatePlugin(plugin);
            return new ResponseEntity<>("The plugin was succesfully updated.", HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/forsong/{songId}")
    public List<Plugin> getPluginsForSong(@PathVariable long songId)
    {
        return pluginService.getPluginsForSong(songId);
    }

    @GetMapping("/{id}")
    public Optional<Plugin> getPlugin(@PathVariable long id)
    {
        return pluginService.getPlugin(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePlugin(@PathVariable long id)
    {
        try
        {
            pluginService.deletePlugin(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
