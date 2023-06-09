package Coop.coop.Services;

import Coop.coop.Entities.Plugin;
import Coop.coop.Entities.Remark;
import Coop.coop.Entities.Song;
import Coop.coop.Entities.SongStatus;
import Coop.coop.MockRepos.MockPluginRepos;
import Coop.coop.MockRepos.MockSongRepos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Tag("UnitTests")
class SongServiceTest {

    @Autowired
    private SongService service;

    private MockSongRepos repos;

    private Song testSong = new Song();
    private List<Song> songList = new ArrayList<>();
    private Plugin testPlugin = new Plugin();
    private Remark testRemark = new Remark();

    @BeforeEach
    void setUp() {
        this.repos = new MockSongRepos();

        //Arrange
        testSong.setTrackName("Track number 1");
        testSong.setAuthor("Desmond");
        testSong.setLength(400);
        testSong.setStatus(SongStatus.Mastering);
        testSong.setDateAdded(new Date(2023-05-05));
        testSong.setLastModifiedBy("Desmond");
        testSong.setDateModified(new Date(2023-05-05));
        testSong.setId(1L);

        testPlugin.setId(1L);
        testPlugin.setAvailable(true);
        testPlugin.setName("SoundToys Tremolator");
        testPlugin.setVersion("2.0.1");

        List<Plugin> plugins = new ArrayList<>();
        plugins.add(testPlugin);

        testSong.setPlugins(plugins);

        testRemark.setId(1L);
        testRemark.setTimeInTrack(65.40);
        testRemark.setAuthor("Henry");
        testRemark.setStemNumber(1);
        testRemark.setDateAdded(new Date(2023-05-05));
        testRemark.setBody("This is a remark's body");

        List<Remark> remarks = new ArrayList<>();
        remarks.add(testRemark);

        testSong.setRemarks(remarks);

        songList.add(testSong);

        repos.FillDatabase(songList);
    }

    @Test
    void addSong_Pass() {

        //Act
        testSong.setId(2L);

        //Assert
        assertEquals(testSong, repos.getById(2L));
    }

    @Test
    void addSong_EmptyName_Fail() {
        //Arrange
        String expectedMessage = "Name cannot be empty";

        //Act
        testSong.setTrackName("");
        testSong.setId(2L);
        //Assert!
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addSong(testSong);
        });

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void updateSong_Pass() throws NoSuchFileException {
        //Act
        testSong.setTrackName("Changed trackname!");
        service.updateSong(testSong);

        ResponseEntity<Song> response = service.getSong(1L);

        assertEquals("Changed trackname!", response.getBody().getTrackName() );
    }

    @Test
    void updateSong_EmptyName_Fail() {
        //Arrange
        String expectedMessage = "Name of the track cannot be empty";

        //Act
        testSong.setTrackName("");

        //Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateSong(testSong);
        });
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deleteSong_Pass() {
        //Act
        boolean result = service.deleteSong(2L);

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteSong_SongDoesntExist_Fail() {
        //Act
        boolean result = service.deleteSong(0L);

        //Assert
        assertFalse(result);
    }

    @Test
    void getSong_Pass() {
        //Act
        var result = service.getSong(1L);

        //Assert
        assertEquals(1L, result.getBody().getId());
        assertEquals("Track number 1", result.getBody().getTrackName());
        assertEquals("Desmond", result.getBody().getAuthor());
    }

    @Test
    void getSong_SongDoesntExist_Fail(){
        //Act
        var result = service.getSong(-1L);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}