package Coop.coop.Interfaces;

import Coop.coop.Entities.Remark;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RemarkRepositoryCustom extends RemarkRepository {

    Optional<List<Remark>> findAllBySong_Id(long songid);
}
