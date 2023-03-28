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

    @Override
    public String toString(){
        return (this.trackName + ", " + this.author);
    }

}
