package Coop.coop.Services;

import Coop.coop.Entities.Remark;
import Coop.coop.Interfaces.RemarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RemarkService
{
    private final RemarkRepository remarkRepository;

    @Autowired
    public RemarkService(RemarkRepository remarkRepository){
        this.remarkRepository = remarkRepository;
    }

    public Remark addRemark (Remark remark){
        return remarkRepository.save((remark));
    }

    public Boolean updateRemark(Remark remark){

        Remark oldRemark = new Remark();
        Optional<Remark> optionalRemark = remarkRepository.findById(remark.getId());

        if(optionalRemark.isPresent()){

            oldRemark.setId(optionalRemark.get().getId());
            oldRemark.setAuthor(optionalRemark.get().getAuthor());
            oldRemark.setDateAdded(optionalRemark.get().getDateAdded());
            oldRemark.setStemNumber(optionalRemark.get().getStemNumber());
            oldRemark.setTimeInTrack(optionalRemark.get().getTimeInTrack());

            remarkRepository.save(oldRemark);
        }
        else{
            return false;
        }
        return true;
    }

    public void deleteRemark(long remarkID){
        remarkRepository.deleteById(remarkID);
    }
    public List<Remark> getRemarksForSong(long songID){

        return remarkRepository.findAll(Sort.by(Sort.Direction.ASC, "songID"));
    }
}
