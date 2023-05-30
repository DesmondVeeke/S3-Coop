package Coop.coop.Interfaces;

import Coop.coop.Entities.Plugin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PluginRepositoryCustom extends PluginRepository {
    Optional<List<Plugin>> findAllBySong_Id(long songid);

    Optional<Plugin> findPluginByName(String name);
}
