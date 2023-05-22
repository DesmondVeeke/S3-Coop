package Coop.coop.IntegrationTests;

import Coop.coop.Entities.Plugin;
import Coop.coop.Interfaces.PluginRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PluginRestControllerTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    private PluginRepository pluginRepository;

    @Test
    public void GetPluginApi() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/plugins/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty());
    }

    @Test
    public void CreatePluginApi() throws Exception{
        Plugin plugin = new Plugin();

        plugin.setAvailable(true);
        plugin.setVersion("A");
        plugin.setName("Sausage Fattener");
        plugin.setSongId(1L);
        plugin.setId(5L);

        mvc.perform(MockMvcRequestBuilders.post("/api/plugins")
                .content(asJsonString(plugin))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Plugin optionalResult = pluginRepository.findById(5L).orElse(null);

        Long resultId = optionalResult.getId();
        String pluginName = optionalResult.getName();
        String version = optionalResult.getVersion();

        assertEquals(5L, resultId);
        assertEquals(plugin.getName(), pluginName);
        assertEquals(plugin.getVersion(), version);

    }

    @Test
    public void UpdatePluginAPI() throws Exception{
        Plugin plugin = new Plugin();

        plugin.setAvailable(true);
        plugin.setVersion("1.0");
        plugin.setName("updated name!");
        plugin.setSongId(1L);
        plugin.setId(1L);

        mvc.perform(MockMvcRequestBuilders
                .put("/api/plugins/{id}", 1L)
                .content(asJsonString(plugin))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Plugin optionalResult = pluginRepository.findById(1L).orElse(null);

        String pluginName = optionalResult.getName();

        assertEquals(plugin.getName(), pluginName);
    }

    @Test
    public void GetPluginsForSong() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/plugins/forsong/{songId}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].songId").isNotEmpty());
    }

    @Test
    public void DeletePluginAPI() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/plugins/{id}", 4L))
                .andExpect(status().isAccepted());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
