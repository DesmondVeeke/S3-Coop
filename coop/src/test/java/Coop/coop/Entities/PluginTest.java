package Coop.coop.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Tag("UnitTests")
class PluginTest {

    Plugin testPlugin = new Plugin();

    @BeforeEach
    void setUp() {
        testPlugin = new Plugin();
    }

    @Test
    void getName() {
        testPlugin.setName("Omnisphere");
        assertEquals("Omnisphere", testPlugin.getName());
    }

    @Test
    void getVersion() {
        testPlugin.setVersion("2.1.3");
        assertEquals("2.1.3", testPlugin.getVersion());
    }

    @Test
    void isAvailable() {
        testPlugin.setAvailable(true);
        assertEquals(true,testPlugin.isAvailable());
    }

    @Test
    void getId() {
        testPlugin.setId(1L);
        assertEquals(1L, testPlugin.getId());

    }

//    @Test void getSongID(){
//        assertEquals(2L,testPlugin.getSongId());
//    }
}