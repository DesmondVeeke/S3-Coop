package Coop.coop.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Setter
@Getter

public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String trackName;
    private String author;
    private long length;
    private Date dateAdded;
    private Date dateModified;
    private String lastModifiedBy;
    @OneToMany
    private List<Plugin> plugins;
    private SongStatus status = SongStatus.Production;
    @OneToMany
    private List<Remark> remarks;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;


    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public void setPlugins(List<Plugin> plugins) {
        this.plugins = plugins;
    }

    public void setStatus(SongStatus status) {
        this.status = status;
    }

    public void setRemarks(List<Remark> remarks) {
        this.remarks = remarks;
    }


    @Override
    public String toString(){
        return (this.trackName + ", " + this.author);
    }

    public String getTrackName() {
        return trackName;
    }

    public String getAuthor() {
        return author;
    }

    public long getLength() {
        return length;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }

    public SongStatus getStatus() {
        return status;
    }

    public List<Remark> getRemarks() {
        return remarks;
    }

    public Date getDateAdded() {
        return dateAdded;
    }


}
