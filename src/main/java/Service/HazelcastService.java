package Service;

import Cache.CacheTypes;
import Entity.IEntity;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import lombok.Data;

import java.util.Map;

@Data
public class HazelcastService {
    static String entityMap = "entity-map";

    public static void initialize(CacheTypes types) {
        Config config = new Config();
        config.setInstanceName(types.name());
        Hazelcast.newHazelcastInstance(config).getMap(entityMap);
    }

    public static Map<String, String> getEntityStore() {
        return Hazelcast.getHazelcastInstanceByName(CacheTypes.ENTITY.name()).getMap(entityMap);
    }
}
