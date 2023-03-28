package Coop.coop.Controllers;

import Coop.coop.Entities.Plugin;
import Coop.coop.Services.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http:localhost:3000")
@RestController
@RequestMapping("/api/Song/{id}")
public class PluginController {

    final PluginService pluginService;

    @Autowired
    public PluginController(PluginService pluginService){
        this.pluginService = pluginService;
    }

    @PostMapping("/post/Plugin")
    public Plugin addPlugin(@RequestBody Plugin plugin){
        return pluginService.addPlugin(plugin);
    }

    @PutMapping("/put/Plugin/{id}")
    public Boolean updatePlugin(@RequestBody Plugin plugin){
        return pluginService.updatePlugin(plugin);
    }

    @GetMapping("/get/Plugin/{id}")
    public List<Plugin> getPlugins(@PathVariable long id){
        return pluginService.getPluginsForSong(id);
    }

    @DeleteMapping("/delete/Plugin/{id}")
    public void deletePlugin(@PathVariable long id){
        pluginService.deletePlugin(id);
    }
}
