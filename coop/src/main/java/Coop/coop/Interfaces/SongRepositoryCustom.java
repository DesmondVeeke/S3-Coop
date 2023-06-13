package Coop.coop.Interfaces;

import Coop.coop.Entities.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepositoryCustom extends SongRepository {

    List<Song> findAllByEnvironment(long environmentID);

}
