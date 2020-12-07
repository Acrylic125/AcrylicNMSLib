package com.acrylic.universal.loaders;

import org.bukkit.entity.EntityType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomEntity {

    /**
     * @return The entity id. You can specify your own or
     * leave it as -1 for the API to handle.
     */
    int entityId() default -1;

    /**
     * @return The name of the entity.
     */
    String name() default "";

    /**
     * @return The entity type.
     */
    EntityType entityType();

    /**
     * @return The NMS super/parent entity class.
     */
    Class<?> entityTypeNMSClass();

}
