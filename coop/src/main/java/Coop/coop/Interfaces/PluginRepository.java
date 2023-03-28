package Coop.coop.Interfaces;

import Coop.coop.Entities.Plugin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PluginRepository extends JpaRepository<Plugin, Long> {

}
