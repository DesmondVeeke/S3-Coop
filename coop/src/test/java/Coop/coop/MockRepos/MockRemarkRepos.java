package Coop.coop.MockRepos;

import Coop.coop.Entities.Remark;
import Coop.coop.Entities.Song;
import Coop.coop.Interfaces.RemarkRepository;
import Coop.coop.Interfaces.RemarkRepositoryCustom;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MockRemarkRepos implements RemarkRepository, RemarkRepositoryCustom {

    public List<Remark> remarkList;

    public MockRemarkRepos()
    {
        remarkList = new ArrayList<>();
    }

    public void FillDatabase(List<Remark> remarks){
        this.remarkList = remarks;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Remark> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Remark> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Remark> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Remark getOne(Long aLong) {
        return null;
    }

    @Override
    public Remark getById(Long aLong) {
        Map<Long, Remark> remarks = new HashMap<Long, Remark>();

        for(Remark remark : remarkList){
            remarks.put(remark.getId(), remark);
        }

        Remark remark = remarks.get(aLong);

        return remark;
    }

    @Override
    public Remark getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Remark> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Remark> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Remark> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Remark> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Remark> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Remark> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Remark, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Remark> S save(S remark) {
        long newId = generateNewId();
        remark.setId(newId);
        remarkList.add(remark);
        return remark;
    }

    @Override
    public <S extends Remark> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Remark> findById(Long aLong) {
        Map<Long, Remark> remarks = new HashMap<Long, Remark>();

        for(Remark remark : remarkList){
            remarks.put(remark.getId(), remark);
        }

        Optional<Remark> remark = Optional.ofNullable(remarks.get(aLong));

        return remark;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Remark> findAll() {
        return null;
    }

    @Override
    public List<Remark> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Remark entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Remark> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Remark> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Remark> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<List<Remark>> findAllBySong_Id(long songid) {
        List<Remark> filteredRemarks = new ArrayList<>();
        for (Remark remark : remarkList) {
            if (remark.getSong().getId() == songid) {
                filteredRemarks.add(remark);
            }
        }
        return Optional.of(filteredRemarks);
    }

    private long generateNewId() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }


}
