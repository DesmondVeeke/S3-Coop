package Coop.coop.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Remark {
    public Long getId() {
        return id;
    }

    public Long getSongID() {
        return songID;
    }

    @Id
    private Long id;
    private Long songID;
    private int stemNumber;
    private String body;
    private double timeInTrack;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public int getStemNumber() {
        return stemNumber;
    }

    public void setStemNumber(int stemNumber) {
        this.stemNumber = stemNumber;
    }

    public double getTimeInTrack() {
        return timeInTrack;
    }

    public void setTimeInTrack(double timeInTrack) {
        this.timeInTrack = timeInTrack;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    private String author;
    private Date dateAdded;

    public void setId(Long id) {
        this.id = id;
    }
}
