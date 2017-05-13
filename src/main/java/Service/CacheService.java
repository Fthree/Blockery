package Service;

import Entity.IEntity;
import Entity.Player;
import Model.Terrain;
import Model.Voxel;

import java.io.*;
import java.util.Base64;

public class CacheService {
    private static String PLAYER = "player";

    public Voxel getVoxelByName(String name) {
        String store = HazelcastService.getEntityStore().get(name);
        return (Voxel)deserializeEntity(store);
    }

    public void setVoxel(Voxel voxel, String name) {
        String serialize = serializeEntity(voxel);
        HazelcastService.getEntityStore().put(name, serialize);
    }

    public Terrain getTerrain(String name) {
        String store = HazelcastService.getEntityStore().get(name);
        Terrain terrain = (Terrain)deserializeEntity(store);
        return terrain;
    }

    public void setTerrain(Terrain terrain, String name) {
        String serialize = serializeEntity(terrain);
        HazelcastService.getEntityStore().put(name, serialize);
    }

    public Player getPlayer() {
        String store = HazelcastService.getEntityStore().get(PLAYER);
        return (Player)deserializeEntity(store);
    }

    public void setPlayer(IEntity object) {
        String serialize = serializeEntity(object);
        HazelcastService.getEntityStore().put(PLAYER, serialize);
    }


    private String serializeEntity(Object entity) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(entity);
            objectOutputStream.flush();

            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException ioe) {
            return "";
        }
    }

    private Object deserializeEntity(String serializedEntity) {
        try {
            byte b[] = Base64.getDecoder().decode(serializedEntity);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            return objectInputStream.readObject();
        } catch (IOException ioe) {

            return null;
        } catch (ClassNotFoundException cnfe) {

            return null;
        }
    }
}
