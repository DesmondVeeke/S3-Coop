package Coop.coop.Services;

import Coop.coop.Entities.Remark;
import Coop.coop.Interfaces.RemarkRepository;
import Coop.coop.Interfaces.RemarkRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RemarkService
{
    private final RemarkRepositoryCustom remarkRepository;

    @Autowired
    public RemarkService(RemarkRepositoryCustom remarkRepository){
        this.remarkRepository = remarkRepository;
    }

    public Remark addRemark (Remark remark)
    {
        if(remark.getBody().isEmpty()){
            throw new IllegalArgumentException("Your remark cannot be empty");
        }

        return remarkRepository.save((remark));
    }

    public Boolean updateRemark(Remark remark){

        if(remark.getBody().isEmpty()){
            throw new IllegalArgumentException("Your remark cannot be empty");
        }

        Remark oldRemark = new Remark();
        Optional<Remark> optionalRemark = remarkRepository.findById(remark.getId());

        if(optionalRemark.isPresent()){

            oldRemark.setId(optionalRemark.get().getId());
            oldRemark.setAuthor(optionalRemark.get().getAuthor());
            oldRemark.setDateAdded(optionalRemark.get().getDateAdded());
            oldRemark.setStemNumber(optionalRemark.get().getStemNumber());
            oldRemark.setTimeInTrack(optionalRemark.get().getTimeInTrack());
            oldRemark.setBody(optionalRemark.get().getBody());

            remarkRepository.save(oldRemark);
        }
        else{
            return false;
        }
        return true;
    }

    public boolean deleteRemark(long remarkID){
        if(remarkRepository.getById(remarkID) != null){
            remarkRepository.deleteById(remarkID);
            return true;
        }
        return false;
    }
    public List<Remark> getRemarksForSong(long songID) {
        Optional<List<Remark>> optionalRemarks = remarkRepository.findAllBySong_Id(songID);
        return optionalRemarks.orElse(Collections.emptyList());
    }

    public Optional<Remark> getRemark(long remarkID) {
        Optional<Remark> optionalRemark = remarkRepository.findById(remarkID);

        if (optionalRemark.isEmpty()) {
            Remark empty = new Remark();
            empty.setBody("No remarks were found");
        }

        return optionalRemark;
    }
}
