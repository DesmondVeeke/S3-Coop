package Coop.coop.Services;

import Coop.coop.Entities.Plugin;
import Coop.coop.Entities.Remark;
import Coop.coop.Entities.Song;
import Coop.coop.Entities.SongStatus;
import Coop.coop.MockRepos.MockSongRepos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Executable;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Tag("Unit tests - Services")
class SongServiceTest {

    private SongService service;

    private MockSongRepos repos;

    private Song testSong = new Song();
    private List<Song> songList = new ArrayList<>();
    private Plugin testPlugin = new Plugin();
    private Remark testRemark = new Remark();

    @BeforeEach
    void setUp() {
        this.repos = new MockSongRepos();
        this.service = new SongService(repos);

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
        testPlugin.setSongId(2L);

        List<Plugin> plugins = new ArrayList<>();
        plugins.add(testPlugin);

        testSong.setPlugins(plugins);

        testRemark.setId(1L);
        testRemark.setTimeInTrack(65.40);
        testRemark.setSongID(2L);
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

        Optional<Song> option = service.getSong(1L);
        Song result = option.get();

        assertEquals("Changed trackname!", result.getTrackName());
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
        boolean result = service.deleteSong(1L);

        //Assert
        assertEquals(true,result);
    }

    @Test
    void deleteSong_SongDoesntExist_Fail() {
        //Act
        boolean result = service.deleteSong(999L);

        //Assert
        assertEquals(false,result);
    }

    @Test
    void getSong_Pass() {
        //Act
        var result = service.getSong(1L);
        Song songResult = result.get();

        //Assert
        assertEquals(testSong, songResult);
    }

    @Test
    void getSong_SongDoesntExist_Fail(){
        //Act
        var result = service.getSong(2L);

        //Assert
        assertEquals(Optional.empty(), result);
    }
}