package Coop.coop.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RemarkTest {

    Remark testRemark = new Remark();
    @BeforeEach
    void setUp() {
        testRemark = new Remark();
    }

    @Test
    void getStemNumber() {
        testRemark.setStemNumber(1);
        assertEquals(1, testRemark.getStemNumber());
    }

    @Test
    void getTimeInTrack() {
        testRemark.setTimeInTrack(10.5);
        assertEquals(10.5, testRemark.getTimeInTrack());
    }

    @Test
    void getAuthor() {
        testRemark.setAuthor("Desmond");
        assertEquals("Desmond", testRemark.getAuthor());
    }

    @Test
    void getDateAdded() {
        testRemark.setDateAdded(new Date(2023-05-05));
        assertEquals(new Date(2023-05-05), testRemark.getDateAdded());
    }

    @Test
    void getId() {
        testRemark.setId(1L);
        assertEquals(1,testRemark.getId());
    }
}