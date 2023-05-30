package Coop.coop.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Remark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int stemNumber;
    private String body;
    private double timeInTrack;
    private String author;
    private Date dateAdded;

    @ManyToOne
    @JoinColumn(name = "song_id")
    @JsonIgnoreProperties("remarks")

    private Song song;

    public void setSong(Song song) {
        this.song = song;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;


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



    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
