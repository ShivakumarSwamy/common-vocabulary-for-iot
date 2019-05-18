package de.uni.stuttgart.ipvs.tm.topics.properties;

public interface TopicProperties {

    String getPath();

    String getProtocol();

    String getMiddlewareEndpoint();

    String getTopicType();

    String getDataType();
}
