package Coop.coop.Interfaces;

import Coop.coop.Entities.Plugin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PluginRepositoryCustom extends PluginRepository {
    public List<Plugin> findAllBySongId(Long songID);
}
