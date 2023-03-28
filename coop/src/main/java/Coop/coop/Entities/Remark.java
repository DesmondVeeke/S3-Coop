package Coop.coop.Entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Remark {
    @jakarta.persistence.Id
    private Long id;

    private Long songID;
    private int stemNumber;
    private double timeInTrack;
    private String author;
    private Date dateAdded;



}
