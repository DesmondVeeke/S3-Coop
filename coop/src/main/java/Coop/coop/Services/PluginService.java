package Coop.coop.Services;

import Coop.coop.Entities.Plugin;
import Coop.coop.Interfaces.PluginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PluginService {
    private final PluginRepository pluginRepository;

    @Autowired
    public PluginService(PluginRepository pluginRepository){
        this.pluginRepository = pluginRepository;
    }

    public Plugin addPlugin(Plugin plugin){
        return pluginRepository.save(plugin);
    }

    public Boolean updatePlugin(Plugin plugin){

        Plugin oldPlugin = new Plugin();
        Optional<Plugin> optionalPlugin = pluginRepository.findById(plugin.getId());

        if(optionalPlugin.isPresent()){
            oldPlugin = optionalPlugin.get();

            oldPlugin.setId(optionalPlugin.get().getId());
            oldPlugin.setName(optionalPlugin.get().getName());
            oldPlugin.setAvailable(optionalPlugin.get().isAvailable());
            oldPlugin.setVersion(optionalPlugin.get().getVersion());

            pluginRepository.save(oldPlugin);
        }
        else{
            return false;
        }
        return true;
    }

    public void deletePlugin(long pluginID){
        pluginRepository.deleteById(pluginID);
    }

    public List<Plugin> getPluginsForSong(long songID){
        return pluginRepository.findAll(Sort.by(Sort.Direction.ASC, "songID"));
    }
}
