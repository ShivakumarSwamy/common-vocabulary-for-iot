package de.uni.stuttgart.ipvs.tm.topics.service;

import lombok.Getter;
import lombok.ToString;

import de.uni.stuttgart.ipvs.tm.topics.dto.TopicCreateDTO;
import de.uni.stuttgart.ipvs.tm.topics.dto.TopicEditDTO;
import de.uni.stuttgart.ipvs.tm.topics.properties.*;

import java.util.UUID;

@Getter
@ToString
public class Topic
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


    private Topic(String owner, String id) {
        this.owner = owner;
        this.id = id;
    }


    static Topic buildFromTopicDTO(TopicCreateDTO topicCreateDTO, String ownerId) {
        return build(TopicBuilder.ownerId(ownerId), topicCreateDTO);
    }

    static Topic buildFromTopicDTO(TopicEditDTO topicEditDTO, String topicId) {
        return build(TopicBuilder.ownerIdAndTopicId(topicEditDTO.getOwner(), topicId), topicEditDTO);
    }

    private static Topic build(TopicBuilder topicBuilder, TopicEditDTO topicEditDTO) {

        topicProperties(topicBuilder, topicEditDTO);
        hardwareProperties(topicBuilder, topicEditDTO);
        messageProperties(topicBuilder, topicEditDTO);
        locationProperties(topicBuilder, topicEditDTO);
        return topicBuilder.get();
    }

    private static Topic build(TopicBuilder topicBuilder, TopicCreateDTO topicCreateDTO) {

        topicProperties(topicBuilder, topicCreateDTO);
        hardwareProperties(topicBuilder, topicCreateDTO);
        messageProperties(topicBuilder, topicCreateDTO);
        locationProperties(topicBuilder, topicCreateDTO);
        return topicBuilder.get();

    }

    private static void topicProperties(TopicBuilder topicBuilder,
                                        TopicProperties topicProperties) {
        topicBuilder
                .path(topicProperties.getPath())
                .protocol(topicProperties.getProtocol().toLowerCase())
                .middlewareEndpoint(topicProperties.getMiddlewareEndpoint())
                .topicType(topicProperties.getTopicType().toLowerCase())
                .dataType(topicProperties.getDataType().toLowerCase())
        ;
    }

    private static void hardwareProperties(TopicBuilder topicBuilder,
                                           HardwareProperties hardwareProperties) {
        topicBuilder
                .hardwareType(requiredTextFormat(hardwareProperties.getHardwareType()))
                .unit(hardwareProperties.getUnit())
        ;
    }

    private static void messageProperties(TopicBuilder topicBuilder,
                                          MessageProperties messageProperties) {
        topicBuilder
                .messageFormat(messageProperties.getMessageFormat().toLowerCase())
                .metaModelType(requiredTextFormat(messageProperties.getMetaModelType()))
                .metaModel(properJsonFormat(messageProperties.getMetaModel()))
        ;
    }

    private static void locationProperties(TopicBuilder topicBuilder,
                                           LocationProperties locationProperties) {
        topicBuilder
                .country(requiredTextFormat(locationProperties.getCountry()))
                .state(requiredTextFormat(locationProperties.getState()))
                .city(requiredTextFormat(locationProperties.getCity()))
                .street(requiredTextFormat(locationProperties.getStreet()))
                .point(locationProperties.getPoint())
        ;
    }


    static class TopicBuilder {

        private Topic topic;

        TopicBuilder(Topic topic) {
            this.topic = topic;
        }

        static TopicBuilder ownerId(String ownerId) {
            return new TopicBuilder(new Topic(ownerId, UUID.randomUUID().toString()));
        }

        static TopicBuilder ownerIdAndTopicId(String ownerId, String topicId) {
            return new TopicBuilder(new Topic(ownerId, topicId));
        }

        TopicBuilder path(String path) {
            this.topic.path = path;
            return this;
        }

        TopicBuilder protocol(String protocol) {
            this.topic.protocol = protocol;
            return this;
        }

        TopicBuilder middlewareEndpoint(String middlewareEndpoint) {
            this.topic.middlewareEndpoint = middlewareEndpoint;
            return this;
        }

        TopicBuilder topicType(String topicType) {
            this.topic.topicType = topicType;
            return this;
        }

        TopicBuilder dataType(String dataType) {
            this.topic.dataType = dataType;
            return this;
        }

        TopicBuilder hardwareType(String hardwareType) {
            this.topic.hardwareType = hardwareType;
            return this;
        }

        TopicBuilder unit(String unit) {
            this.topic.unit = unit;
            return this;
        }

        TopicBuilder messageFormat(String messageFormat) {
            this.topic.messageFormat = messageFormat;
            return this;
        }

        TopicBuilder metaModelType(String metaModelType) {
            this.topic.metaModelType = metaModelType;
            return this;
        }

        TopicBuilder metaModel(String metaModel) {
            this.topic.metaModel = metaModel;
            return this;
        }

        TopicBuilder country(String country) {
            this.topic.country = country;
            return this;
        }

        TopicBuilder state(String state) {
            this.topic.state = state;
            return this;
        }

        TopicBuilder city(String city) {
            this.topic.city = city;
            return this;
        }

        TopicBuilder street(String street) {
            this.topic.street = street;
            return this;
        }

        TopicBuilder point(String point) {
            this.topic.point = point;
            return this;
        }


        Topic get() {
            return this.topic;
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

