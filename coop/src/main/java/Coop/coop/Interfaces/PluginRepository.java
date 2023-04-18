package Coop.coop.Interfaces;

import Coop.coop.Entities.Plugin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PluginRepository extends JpaRepository<Plugin, Long> {

}
