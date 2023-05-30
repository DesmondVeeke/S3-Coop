package Coop.coop.Services;

import Coop.coop.DTO.PluginDTO;
import Coop.coop.Entities.Plugin;
import Coop.coop.Entities.Song;
import Coop.coop.Interfaces.PluginRepository;
import Coop.coop.Interfaces.PluginRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    public Boolean updatePlugin(PluginDTO pluginDTO){

        Optional<Plugin> optionalPlugin = pluginRepository.findById(pluginDTO.getPluginid());

        if(optionalPlugin.isPresent()){
            var oldPlugin = optionalPlugin.get();

            if(pluginDTO.name != null)
            {
                oldPlugin.setName(pluginDTO.getName());
            }
            if(pluginDTO.version != null)
            {
                oldPlugin.setVersion(pluginDTO.getVersion());
            }
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

    public List<Plugin> getPluginsForSong(long songID) {
        Optional<List<Plugin>> optionalPlugins = pluginRepository.findAllBySong_Id(songID);
        return optionalPlugins.orElse(Collections.emptyList());
    }

    public Plugin GetPlugin(long pluginID){
        return pluginRepository.getById(pluginID);
    }

    public Optional<Plugin> getPlugin(long pluginID) {
        return pluginRepository.findById(pluginID);
    }

    public List<Plugin> createListForSong(List<Plugin> receivedPlugins){

        List<Plugin> plugins = new ArrayList<>();

        for(Plugin plugin : receivedPlugins){
            Plugin newPlugin = new Plugin();
            newPlugin.setName(plugin.getName());
            newPlugin.setVersion(plugin.getVersion());

            plugins.add(newPlugin);
        }

        return plugins;

    }

    public void AddPluginsForSong(List<Plugin> plugins, Song song){
        for(Plugin plugin : plugins){
            plugin.setSong(song);
            this.addPlugin(plugin);
        }
    }
}
