package de.uni.stuttgart.ipvs.em.entities.service;

import lombok.Getter;
import lombok.ToString;

import de.uni.stuttgart.ipvs.em.entities.dto.EntityCreateDTO;
import de.uni.stuttgart.ipvs.em.entities.dto.EntityEditDTO;
import de.uni.stuttgart.ipvs.em.entities.properties.*;

import java.util.UUID;

@Getter
@ToString
public class Entity
        implements EntityProperties, TopicProperties, HardwareProperties, MessageProperties, LocationProperties {

    private String id;
    private String owner;

    private String path;
    private String middlewareEndpoint;
    private String dataType;
    private String protocol;
    private String topicType;

    private String hardwareType;
    private String unit;

    private String messageFormat;
    private String metaModelType;
    private String metaModel;

    private String country;
    private String state;
    private String city;
    private String street;
    private String point;


    private Entity(String owner, String id) {
        this.owner = owner;
        this.id = id;
    }


    static Entity buildFromEntityDTO(EntityCreateDTO entityCreateDTO, String ownerId) {
        return build(EntityBuilder.ownerId(ownerId), entityCreateDTO);
    }

    static Entity buildFromEntityDTO(EntityEditDTO entityEditDTO, String entityId) {
        return build(EntityBuilder.ownerIdAndEntityId(entityEditDTO.getOwner(), entityId), entityEditDTO);
    }

    private static Entity build(EntityBuilder entityBuilder, EntityEditDTO entityEditDTO) {

        topicProperties(entityBuilder, entityEditDTO);
        hardwareProperties(entityBuilder, entityEditDTO);
        messageProperties(entityBuilder, entityEditDTO);
        locationProperties(entityBuilder, entityEditDTO);
        return entityBuilder.get();
    }

    private static Entity build(EntityBuilder entityBuilder, EntityCreateDTO entityCreateDTO) {

        topicProperties(entityBuilder, entityCreateDTO);
        hardwareProperties(entityBuilder, entityCreateDTO);
        messageProperties(entityBuilder, entityCreateDTO);
        locationProperties(entityBuilder, entityCreateDTO);
        return entityBuilder.get();

    }

    private static void topicProperties(EntityBuilder entityBuilder,
                                        TopicProperties topicProperties) {
        entityBuilder
                .path(topicProperties.getPath())
                .protocol(topicProperties.getProtocol().toLowerCase())
                .middlewareEndpoint(topicProperties.getMiddlewareEndpoint())
                .topicType(topicProperties.getTopicType().toLowerCase())
                .dataType(topicProperties.getDataType().toLowerCase())
        ;
    }

    private static void hardwareProperties(EntityBuilder entityBuilder,
                                           HardwareProperties hardwareProperties) {
        entityBuilder
                .hardwareType(requiredTextFormat(hardwareProperties.getHardwareType()))
                .unit(hardwareProperties.getUnit())
        ;
    }

    private static void messageProperties(EntityBuilder entityBuilder,
                                          MessageProperties messageProperties) {
        entityBuilder
                .messageFormat(messageProperties.getMessageFormat().toLowerCase())
                .metaModelType(requiredTextFormat(messageProperties.getMetaModelType()))
                .metaModel(properJsonFormat(messageProperties.getMetaModel()))
        ;
    }

    private static void locationProperties(EntityBuilder entityBuilder,
                                           LocationProperties locationProperties) {
        entityBuilder
                .country(requiredTextFormat(locationProperties.getCountry()))
                .state(requiredTextFormat(locationProperties.getState()))
                .city(requiredTextFormat(locationProperties.getCity()))
                .street(requiredTextFormat(locationProperties.getStreet()))
                .point(locationProperties.getPoint())
        ;
    }


    static class EntityBuilder {

        private Entity entity;

        EntityBuilder(Entity entity) {
            this.entity = entity;
        }

        static EntityBuilder ownerId(String ownerId) {
            return new EntityBuilder(new Entity(ownerId, UUID.randomUUID().toString()));
        }

        static EntityBuilder ownerIdAndEntityId(String ownerId, String entityId) {
            return new EntityBuilder(new Entity(ownerId, entityId));
        }

        EntityBuilder path(String path) {
            this.entity.path = path;
            return this;
        }

        EntityBuilder protocol(String protocol) {
            this.entity.protocol = protocol;
            return this;
        }

        EntityBuilder middlewareEndpoint(String middlewareEndpoint) {
            this.entity.middlewareEndpoint = middlewareEndpoint;
            return this;
        }

        EntityBuilder topicType(String topicType) {
            this.entity.topicType = topicType;
            return this;
        }

        EntityBuilder dataType(String dataType) {
            this.entity.dataType = dataType;
            return this;
        }

        EntityBuilder hardwareType(String hardwareType) {
            this.entity.hardwareType = hardwareType;
            return this;
        }

        EntityBuilder unit(String unit) {
            this.entity.unit = unit;
            return this;
        }

        EntityBuilder messageFormat(String messageFormat) {
            this.entity.messageFormat = messageFormat;
            return this;
        }

        EntityBuilder metaModelType(String metaModelType) {
            this.entity.metaModelType = metaModelType;
            return this;
        }

        EntityBuilder metaModel(String metaModel) {
            this.entity.metaModel = metaModel;
            return this;
        }

        EntityBuilder country(String country) {
            this.entity.country = country;
            return this;
        }

        EntityBuilder state(String state) {
            this.entity.state = state;
            return this;
        }

        EntityBuilder city(String city) {
            this.entity.city = city;
            return this;
        }

        EntityBuilder street(String street) {
            this.entity.street = street;
            return this;
        }

        EntityBuilder point(String point) {
            this.entity.point = point;
            return this;
        }


        Entity get() {
            return this.entity;
        }
    }

    private static String requiredTextFormat(String text) {
        return text.strip()
                .toLowerCase()
                .replaceAll("\\s+", "-");
    }

    private static String properJsonFormat(String text) {
        return text.strip()
                .replaceAll("\\\\\"", "\"")
                .replaceAll("\\r|\\n", "")
                .replaceAll("\"", "\\\\\"");
    }
}

