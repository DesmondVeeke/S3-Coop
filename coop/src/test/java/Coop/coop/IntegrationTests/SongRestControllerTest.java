package Coop.coop.IntegrationTests;

import Coop.coop.Entities.Song;
import Coop.coop.Entities.SongStatus;
import Coop.coop.Interfaces.SongRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Tag("IntegrationTests")
public class SongRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private SongRepository songRepository;

    @Test
    public void GetSongAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/songs/{id}", 1)
                        .header("COOP", "TRUE")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void CreateSongAPI() throws Exception {
        Song testSong = new Song();
        testSong.setTrackName("Integration track");
        testSong.setAuthor("Desmond");
        testSong.setLength(400);
        testSong.setStatus(SongStatus.Mastering);
        testSong.setDateAdded(new Date(2023 - 05 - 05));
        testSong.setLastModifiedBy("Desmond");
        testSong.setDateModified(new Date(2023 - 05 - 05));

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/songs")
                        .content(asJsonString(testSong))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("COOP", "TRUE")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Song optionalResult = songRepository.findById(5L).orElse(null);

        Long resultID = optionalResult.getId();
        String trackName = optionalResult.getTrackName();
        String authorName = optionalResult.getAuthor();

        // Assert
        assertEquals(5L, resultID);
        assertEquals(testSong.getAuthor(), authorName);
        assertEquals(testSong.getTrackName(), trackName);
    }

    @Test
    public void DeleteSongAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/songs/{id}", 4L)
                        .header("COOP", "TRUE"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void getAllSongsApi() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/songs/")
                        .header("COOP", "TRUE"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

