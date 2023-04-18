package Coop.coop.MockRepos;

import Coop.coop.Entities.Plugin;
import Coop.coop.Entities.Remark;
import Coop.coop.Interfaces.PluginRepository;
import Coop.coop.Interfaces.PluginRepositoryCustom;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class MockPluginRepos implements PluginRepository, PluginRepositoryCustom {

    public List<Plugin> pluginList;
    public MockPluginRepos(){pluginList = new ArrayList<>();}

    public void FillDatabase(List<Plugin> plugins) { this.pluginList = plugins;}

    @Override
    public List<Plugin> findAllBySongId(Long songID) {
        List<Plugin> plugins = new ArrayList<>();

        for(int i = 0; i < pluginList.size(); i++){
            if(songID == pluginList.get(i).getSongId()){
                plugins.add(pluginList.get(i));
            }
        }

        return plugins;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Plugin> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Plugin> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Plugin> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Plugin getOne(Long aLong) {
        return null;
    }

    @Override
    public Plugin getById(Long aLong) {
        Map<Long, Plugin> plugins = new HashMap<Long, Plugin>();

        for(Plugin plugin : pluginList){
            plugins.put(plugin.getId(), plugin);
        }

        Plugin plugin = plugins.get(aLong);

        return plugin;
    }

    @Override
    public Plugin getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Plugin> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Plugin> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Plugin> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Plugin> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Plugin> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Plugin> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Plugin, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Plugin> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Plugin> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Plugin> findById(Long aLong) {
        Map<Long, Plugin> plugins = new HashMap<>();

        for(Plugin plugin : pluginList){
            plugins.put(plugin.getId(), plugin);
        }

        Optional<Plugin> plugin = Optional.ofNullable(plugins.get(aLong));

        return plugin;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Plugin> findAll() {
        return null;
    }

    @Override
    public List<Plugin> findAllById(Iterable<Long> longs) {
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
    public void delete(Plugin entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Plugin> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Plugin> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Plugin> findAll(Pageable pageable) {
        return null;
    }
}
