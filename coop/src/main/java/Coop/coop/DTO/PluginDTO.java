package Coop.coop.DTO;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PluginDTO {

    public long pluginid;

    public String name;
    public String version;

}
