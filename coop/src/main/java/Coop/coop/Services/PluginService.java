package Coop.coop.Services;

import Coop.coop.Entities.Plugin;
import Coop.coop.Interfaces.PluginRepository;
import Coop.coop.Interfaces.PluginRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PluginService {
    private final PluginRepositoryCustom pluginRepository;

    @Autowired
    public PluginService(PluginRepositoryCustom pluginRepository){
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

    public Boolean deletePlugin(long pluginID){
        if(pluginRepository.getById(pluginID) != null){
            pluginRepository.deleteById(pluginID);
            return true;
        }
        return false;
    }

    public List<Plugin> getPluginsForSong(long songID){
        return pluginRepository.findAllBySongId(songID);
    }

    public Plugin GetPlugin(long pluginID){
        return pluginRepository.getById(pluginID);
    }

    public Optional<Plugin> getPlugin(long pluginID) {
        return pluginRepository.findById(pluginID);
    }
}
