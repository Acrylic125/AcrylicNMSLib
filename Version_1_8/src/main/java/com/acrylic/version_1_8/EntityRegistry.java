package com.acrylic.version_1_8;

import com.acrylic.universal.loaders.InvalidEntityRegistry;
import com.acrylic.version_1_8.entityanimator.ArmorStandEntityInstance;
import com.acrylic.version_1_8.entityanimator.GiantEntityInstance;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityRegistry implements com.acrylic.universal.loaders.EntityRegistry {

    public EntityRegistry() {
        setupDefaults();
    }

    @Override
    public void setupDefaults() {
        registerEntity(ArmorStandEntityInstance.class);
        registerEntity(GiantEntityInstance.class);
    }

    /**
     * Ref: https://www.spigotmc.org/threads/tutorial-register-and-use-nms-entities-1-8.77607/
     *
     * @see com.acrylic.universal.loaders.EntityRegistry
     */
    @Override
    public void registerEntity(int id, String name, EntityType entityType, Class<?> mainClass, Class<?> nmsEntityClass) throws InvalidEntityRegistry {
        if (mainClass.isAssignableFrom(EntityInsentient.class))
            throw new InvalidEntityRegistry(mainClass, "The main class must be an instance of " + EntityInsentient.class.getName());
        if (nmsEntityClass.isAssignableFrom(EntityInsentient.class))
            throw new InvalidEntityRegistry(mainClass, "The NMS Entity class must be an instance of " + EntityInsentient.class.getName());
        try {

            List<Map<?, ?>> dataMap = new ArrayList<>();
            for (Field f : EntityTypes.class.getDeclaredFields()) {
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())) {
                    f.setAccessible(true);
                    dataMap.add((Map<?, ?>) f.get(null));
                    f.setAccessible(false);
                }
            }

            Map<?, ?> mMap = dataMap.get(2);
            if (mMap.containsKey(id)){
                dataMap.get(0).remove(name);
                mMap.remove(id);
            }

            Method method = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
            method.setAccessible(true);
            method.invoke(null, mainClass, name, id);
            method.setAccessible(false);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
