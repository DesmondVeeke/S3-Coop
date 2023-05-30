package Coop.coop.Services;

import Coop.coop.DTO.PluginDTO;
import Coop.coop.Entities.Song;
import Coop.coop.MockRepos.MockPluginRepos;
import Coop.coop.Entities.Plugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Tag("UnitTests")
class PluginServiceTest {

    private PluginService service;
    private MockPluginRepos repos;
    private Song testSong = new Song();
    private Plugin testPlugin = new Plugin();
    private List<Plugin> pluginList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.repos = new MockPluginRepos();
        this.service = new PluginService(repos);

        //Arrange
        testSong.setTrackName("Plugin tests");
        testSong.setId(1L);

        testPlugin.setSong(testSong);
        testPlugin.setAvailable(true);
        testPlugin.setName("SoundToys Tremolator");
        testPlugin.setVersion("2.0.1");

        pluginList.add(testPlugin);
        repos.FillDatabase(pluginList);
        testPlugin = service.addPlugin(testPlugin);

    }

    @Test
    void addPlugin_Pass() {
        //Arrange
        testSong = new Song();
        testSong.setId(1L);
        testSong.setTrackName("addPlugin_Pass");

        Plugin newPlugin = new Plugin();
        newPlugin.setSong(testSong);

        //Act
        Plugin savedPlugin = service.addPlugin(testPlugin);

        //Assert
        assertEquals(testPlugin.getName(), savedPlugin.getName());

    }

    @Test
    void updatePlugin_Pass() {
        PluginDTO dto = new PluginDTO();

        dto.setName("updatePlugin_Pass");
        dto.setPluginid(testPlugin.getId());
        service.updatePlugin(dto);

        Plugin result = service.getPlugin(testPlugin.getId()).orElse(null);

        assertEquals("updatePlugin_Pass", result.getName());
    }

    @Test
    void deletePlugin() {
        //Act
        boolean result = service.deletePlugin(testPlugin.getId());

        //Assert
        assertTrue(result);

    }

    @Test
    void deletePlugin_PluginDoesntExist_Fail(){
        //Act
        boolean result = service.deletePlugin(999L);

        //Assert
        assertFalse(result);

    }

    @Test
    void getPluginsForSong() {
        //Act
        var result = service.getPluginsForSong(1L);

        //Assert
        assertEquals(pluginList, result);
    }

    @Test
    void getPluginsForSong_SongDoesntExist_Failed(){
        //Act
        var result = service.getPluginsForSong(999L);

        //Assert
        assertEquals(0, result.size());
    }

    @Test
    void getPlugin_Pass(){
        //Act
        var result = service.getPlugin(testPlugin.getId());
        Plugin pluginResult = result.get();

        //Assert
        assertEquals(testPlugin, pluginResult);
    }

    @Test
    void getPlugin_PluginDoesntExist_Fail(){
        //Act
        var result = service.getPlugin(-999L);

        //Assert
        assertEquals(Optional.empty(), result);

    }
}