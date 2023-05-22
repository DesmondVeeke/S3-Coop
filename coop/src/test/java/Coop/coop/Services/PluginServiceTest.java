package Coop.coop.Services;

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
    private Plugin testPlugin = new Plugin();
    private List<Plugin> pluginList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.repos = new MockPluginRepos();
        this.service = new PluginService(repos);

        //Arrange
        testPlugin.setId(1L);
        testPlugin.setAvailable(true);
        testPlugin.setName("SoundToys Tremolator");
        testPlugin.setVersion("2.0.1");
        testPlugin.setSongId(2L);

        pluginList.add(testPlugin);
        repos.FillDatabase(pluginList);

    }

    @Test
    void addPlugin_Pass() {
        //Arrange
        testPlugin.setId(5L);

        //Act
        service.addPlugin(testPlugin);

        //Assert
        assertEquals(testPlugin, repos.getById(5L));

    }

    @Test
    void updatePlugin_Pass() {
        testPlugin.setAvailable(false);
        service.updatePlugin(testPlugin);

        Optional<Plugin> optional = service.getPlugin(1L);
        Plugin result = optional.get();

        assertFalse(result.isAvailable());
    }

    @Test
    void deletePlugin() {
        //Act
        boolean result = service.deletePlugin(1L);

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
        var result = service.getPluginsForSong(2L);

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
        var result = service.getPlugin(1L);
        Plugin pluginResult = result.get();

        //Assert
        assertEquals(testPlugin, pluginResult);
    }

    @Test
    void getPlugin_PluginDoesntExist_Fail(){
        //Act
        var result = service.getPlugin(999L);

        //Assert
        assertEquals(Optional.empty(), result);

    }
}