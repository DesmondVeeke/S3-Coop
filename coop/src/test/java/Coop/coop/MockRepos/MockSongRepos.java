package Coop.coop.MockRepos;

import Coop.coop.Entities.Song;
import Coop.coop.Interfaces.SongRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class MockSongRepos  implements SongRepository {

    public List<Song> songList;

    public MockSongRepos()
    {
        songList = new ArrayList<>();
    }

    public void FillDatabase(List<Song> songList)
    {
        this.songList = songList;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Song> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Song> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Song> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Song getOne(Long aLong) {
        return null;
    }

    @Override
    public Song getById(Long aLong) {
        Map<Long, Song> songs = new HashMap<Long, Song>();

        for(Song song : songList){
            songs.put(song.getId(), song);
        }

        Song song = songs.get(aLong);
        return song;
    }

    @Override
    public Song getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Song> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Song> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Song> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Song> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Song> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Song> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Song, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Song> S save(S entity) {
        songList.add(entity);
        return entity;
    }

    @Override
    public <S extends Song> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Song> findById(Long aLong) {
        Map<Long, Song> songs = new HashMap<Long, Song>();

        for(Song song : songList){
            songs.put(song.getId(), song);
        }

        Optional<Song> song = Optional.ofNullable(songs.get(aLong));
        return song;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Song> findAll() {
        return null;
    }

    @Override
    public List<Song> findAllById(Iterable<Long> longs) {
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
    public void delete(Song entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Song> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Song> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Song> findAll(Pageable pageable) {
        return null;
    }
}
