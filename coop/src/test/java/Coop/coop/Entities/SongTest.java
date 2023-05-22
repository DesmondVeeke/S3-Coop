package Coop.coop.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Tag("UnitTests")
class SongTest {

    Song songTest = new Song();
    @BeforeEach
    void setUp() {
        songTest = new Song();
    }

    @Test
    void getTrackName() {
        songTest.setTrackName("Test name!");
        assertEquals("Test name!", songTest.getTrackName());

    }

    @Test
    void getAuthor() {
        songTest.setAuthor("Desmond");
        assertEquals("Desmond", songTest.getAuthor());
    }

    @Test
    void getLength() {
        songTest.setLength(5);
        assertEquals(5, songTest.getLength());
    }

    @Test
    void getDateModified() {
        songTest.setDateModified(new Date(2023-05-05));
        assertEquals(new Date(2023-05-05), songTest.getDateModified());
    }

    @Test
    void getLastModifiedBy() {
        songTest.setLastModifiedBy("Joep");
        assertEquals("Joep", songTest.getLastModifiedBy());
    }

    @Test
    void getPlugins() {
        Plugin testPlugin = new Plugin();
        List<Plugin> plugins = new ArrayList<Plugin>();
        songTest.setPlugins(plugins);
        assertEquals(plugins, songTest.getPlugins());

    }

    @Test
    void getStatus() {
        songTest.setStatus(SongStatus.Mix);
        assertEquals(SongStatus.Mix, songTest.getStatus());
    }

    @Test
    void getRemarks() {
        Remark testRemark = new Remark();
        List<Remark> remarks = new ArrayList<Remark>();
        songTest.setRemarks(remarks);
        assertEquals(remarks, songTest.getRemarks());
    }

    @Test
    void getDateAdded() {
        songTest.setDateAdded(new Date(2023-05-05));
        assertEquals(new Date(2023-05-05), songTest.getDateAdded());
    }

    @Test
    void getId() {
        songTest.setId(1);
        assertEquals(1, songTest.getId());
    }
}