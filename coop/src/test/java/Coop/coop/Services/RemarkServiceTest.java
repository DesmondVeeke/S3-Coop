package Coop.coop.Services;

import Coop.coop.Entities.Remark;
import Coop.coop.Entities.Song;
import Coop.coop.MockRepos.MockRemarkRepos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Tag("UnitTests")
class RemarkServiceTest {

    private RemarkService service;
    private MockRemarkRepos repos;
    private Remark testRemark = new Remark();
    private Song testSong = new Song();
    private List<Remark> remarkList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        this.repos = new MockRemarkRepos();
        this.service = new RemarkService(repos);

        Song testSong = new Song();
        testSong.setTrackName("Remark test song");

        testRemark.setTimeInTrack(65.40);
        testRemark.setAuthor("Henry");
        testRemark.setStemNumber(1);
        testRemark.setDateAdded(new Date(2023-05-05));
        testRemark.setBody("This is a remark's body");
        testRemark.setSong(testSong);

        remarkList.add(testRemark);
        repos.FillDatabase(remarkList);
        service.addRemark(testRemark);
    }

    @Test
    void addRemark_Pass() {
        //Arrange
        testSong = new Song();
        testSong.setId(1L);
        testSong.setTrackName("addRemark_Pass");

        Remark newRemark = new Remark();
        newRemark.setSong(testSong);


        //Act
        Remark savedRemark = service.addRemark(testRemark);

        //Assert
        assertEquals(testRemark.getBody(), savedRemark.getBody());
    }
    @Test
    void addRemark_EmptyBody_Fail() {
        //Arrange
        String expectedMessage = "Your remark cannot be empty";

        //Act
        testRemark.setBody("");

        //Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addRemark(testRemark);
        });

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void updateRemark_Pass() {
        //Act
        testRemark.setBody("This body has been changed!");
        service.updateRemark(testRemark);

        Optional<Remark> optional = service.getRemark(testRemark.getId());
        Remark result = optional.get();

        assertEquals("This body has been changed!", result.getBody());

    }
    @Test
    void updateRemark_EmptyBody_Fail() {
        //Arrange
        String expectedMessage = "Your remark cannot be empty";

        //Act
        testRemark.setBody("");

        //Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            service.updateRemark(testRemark);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void deleteRemark_Pass() {
        //Act
        boolean result = service.deleteRemark(testRemark.getId());

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteRemark_RemarkDoesntExist_Fail() {
        //Act
        boolean result = service.deleteRemark(-999L);

        //Assert
        assertFalse(result);
    }


    @Test
    void getRemarksForSong() {
        //Act
        var result = service.getRemarksForSong(testRemark.getSong().getId());

        //Assert
        assertEquals(remarkList, result);
    }
    @Test
    void getRemarksForSong_SongDoesntExist_Failed() {
        //Act
        var result = service.getRemarksForSong(5L);

        //Assert
        assertEquals(0,result.size());
    }

    @Test
    void getRemarks_Pass(){
        //Act
        var result = service.getRemark(testRemark.getId());
        Remark remarkResult = result.get();

        //Assert
        assertEquals(testRemark, remarkResult);
    }

    @Test
    void getRemarks_RemarkDoesntExist_Fail(){
        //Act
        var result = service.getRemark(999L);

        //Assert
        assertEquals(Optional.empty(), result);
    }
}