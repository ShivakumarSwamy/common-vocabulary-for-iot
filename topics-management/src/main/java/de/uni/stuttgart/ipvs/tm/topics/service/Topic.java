package de.uni.stuttgart.ipvs.tm.topics.service;

import lombok.Getter;
import lombok.ToString;

import de.uni.stuttgart.ipvs.tm.topics.dto.TopicCreateDTO;
import de.uni.stuttgart.ipvs.tm.topics.dto.TopicEditDTO;
import de.uni.stuttgart.ipvs.tm.topics.properties.EntityProperties;
import de.uni.stuttgart.ipvs.tm.topics.properties.HardwareProperties;
import de.uni.stuttgart.ipvs.tm.topics.properties.MessageProperties;
import de.uni.stuttgart.ipvs.tm.topics.properties.TopicProperties;

import java.util.UUID;

@Getter
@ToString
public class Topic implements EntityProperties, TopicProperties, HardwareProperties, MessageProperties {

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
        return topicBuilder
                .path(topicEditDTO.getPath())
                .protocol(topicEditDTO.getProtocol().toLowerCase())
                .middlewareEndpoint(topicEditDTO.getMiddlewareEndpoint())
                .topicType(topicEditDTO.getTopicType().toLowerCase())
                .dataType(topicEditDTO.getDataType().toLowerCase())
                .hardwareType(requiredTermsTextFormat(topicEditDTO.getHardwareType()))
                .unit(topicEditDTO.getUnit())
                .messageFormat(topicEditDTO.getMessageFormat().toLowerCase())
                .metaModelType(requiredTermsTextFormat(topicEditDTO.getMetaModelType()))
                .metaModel(properJsonFormat(topicEditDTO.getMetaModel()))
                .get();
    }

    private static Topic build(TopicBuilder topicBuilder, TopicCreateDTO topicCreateDTO) {
        return topicBuilder
                .path(topicCreateDTO.getPath())
                .protocol(topicCreateDTO.getProtocol().toLowerCase())
                .middlewareEndpoint(topicCreateDTO.getMiddlewareEndpoint())
                .topicType(topicCreateDTO.getTopicType().toLowerCase())
                .dataType(topicCreateDTO.getDataType().toLowerCase())
                .hardwareType(requiredTermsTextFormat(topicCreateDTO.getHardwareType()))
                .unit(topicCreateDTO.getUnit())
                .messageFormat(topicCreateDTO.getMessageFormat().toLowerCase())
                .metaModelType(requiredTermsTextFormat(topicCreateDTO.getMetaModelType()))
                .metaModel(properJsonFormat(topicCreateDTO.getMetaModel()))
                .get();
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


        Topic get() {
            return this.topic;
        }
    }

    static String requiredTermsTextFormat(String termsText) {
        return termsText.toLowerCase().replaceAll("\\s+", "-");
    }

    static String properJsonFormat(String text) {
        return text.strip().replaceAll("\\\\\"", "\"").replaceAll("\\r|\\n", "").replaceAll("\"", "\\\\\"");
    }
}

