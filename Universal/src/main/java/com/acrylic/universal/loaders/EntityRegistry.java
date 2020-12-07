package com.acrylic.universal.loaders;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public interface EntityRegistry {

    /**
     * The ids are adapted from https://minecraft.gamepedia.com/Java_Edition_data_value/Pre-flattening/Entity_IDs
     *
     * @param entityType The entity type.
     * @return The entity registry id.
     */
    static int getId(@NotNull EntityType entityType) {
        switch (entityType) {
            case GIANT: return 53;
            case ARMOR_STAND: return 30;
            case CREEPER: return 50;
            case SKELETON: return 51;
            case SPIDER: return 52;
            case ZOMBIE: return 54;
            case SLIME: return 55;
            case GHAST: return 56;
            case ENDERMAN: return 58;
            case CAVE_SPIDER: return 59;
            default:
                return -1;
        }
    }

    default void registerEntity(Class<?> entityClass) throws InvalidEntityRegistry {
        CustomEntity annotation = entityClass.getAnnotation(CustomEntity.class);
        if (annotation != null) {
            int id = annotation.entityId();
            EntityType entityType = annotation.entityType();
            if (id == -1) {
                id = getId(entityType);
                if (id == -1)
                    throw new InvalidEntityRegistry(entityClass, "The specified class entity id is not supported. Please specify a specific ID.");
            }
            registerEntity(id, annotation.name(), entityType, entityClass, annotation.entityTypeNMSClass());
        }
    }

    void registerEntity(int id, String name, EntityType entityType, Class<?> mainClass, Class<?> nmsEntityClass) throws InvalidEntityRegistry;

}
