package Coop.coop.Interfaces;

import Coop.coop.Entities.Remark;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemarkRepositoryCustom extends RemarkRepository {

    public List<Remark> findAllBySongID(Long songID);
}
