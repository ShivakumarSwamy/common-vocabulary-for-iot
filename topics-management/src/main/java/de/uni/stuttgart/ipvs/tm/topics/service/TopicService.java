package de.uni.stuttgart.ipvs.tm.topics.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import de.uni.stuttgart.ipvs.tm.response.ResultsSet;
import de.uni.stuttgart.ipvs.tm.topics.dto.TopicCreateDTO;
import de.uni.stuttgart.ipvs.tm.topics.dto.TopicEditDTO;
import de.uni.stuttgart.ipvs.tm.topics.form.TopicFormModelValidation;
import de.uni.stuttgart.ipvs.tm.topics.persistence.TopicRepository;

import java.util.*;

@Service
@Slf4j
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicFormModelValidation tFMV;

    @Autowired
    public TopicService(TopicRepository topicRepository,
                        TopicFormModelValidation tFMV) {

        this.topicRepository = topicRepository;
        this.tFMV = tFMV;
    }

    public ResultsSet getAllTopics(String ownerId) {
        return new ResultsSet<>(this.topicRepository.findAllTopics(ownerId));
    }

    public ResultsSet getAllTopics() {
        return new ResultsSet<>(this.topicRepository.findAllTopics());
    }

    public ResultsSet getTopic(String ownerId, String entityId) {
        return new ResultsSet<>(this.topicRepository.findTopic(ownerId, entityId));
    }

    public ResultsSet getTopicForAdmin(String entityId) {
        return new ResultsSet<>(this.topicRepository.findTopic(entityId));
    }


    public ResultsSet getAllTopicsUsingTermsText(String termsText) {
        if (!StringUtils.hasLength(termsText)) return ResultsSet.EMPTY;
        return new ResultsSet<>(this.topicRepository.findAllTopics(termsTextToTerms(termsText)));
    }

    //
//    private TopicsResponse getAllTopicsUsingTerms(String[] terms) {
//        String[] termsWithTopicTermAdded = addTopicTermToTerms(terms);
//        var allTopics = this.topicRepository.findAllTopics(termsWithTopicTermAdded);
//        return allTopics.isEmpty() ? TopicsResponse.EMPTY : new TopicsResponse(allTopics);
//    }
//
    public ResultsSet getAllTopicsUsingTermsTextForOwner(String ownerId, String termsText) {

        if (!StringUtils.hasLength(termsText)) return ResultsSet.EMPTY;
        return new ResultsSet<>(this.topicRepository.findAllTopics(ownerId, termsTextToTerms(termsText)));
    }

    //
//    private TopicsResponse getAllTopicsUsingTermsForOwner(String ownerId, String[] terms) {
//        String[] termsWithTopicTermAdded = addTopicTermToTerms(terms);
//        var allTopics = this.topicRepository.findAllTopics(ownerId, termsWithTopicTermAdded);
//        return allTopics.isEmpty() ? TopicsResponse.EMPTY : new TopicsResponse(allTopics);
//    }
//
    public String createTopic(TopicCreateDTO topicCreateDTO, String ownerID) {

        this.tFMV.validate(topicCreateDTO);

        var topic = Topic.buildFromTopicDTO(topicCreateDTO, ownerID);
        topicRepository.save(topic);
        return topic.getId();
    }

    public void updateTopic(TopicEditDTO topicEditDTO, String topicId) {
        this.tFMV.validate(topicEditDTO);

        var topic = Topic.buildFromTopicDTO(topicEditDTO, topicId);
        this.topicRepository.update(topic);
    }

    public void deleteTopic(String id) {
        this.topicRepository.delete(id);
    }

    public void deleteTopic(String ownerID, String id) {
        this.topicRepository.delete(ownerID, id);
    }

    static Collection<String> termsTextToTerms(String termsText) {
        String[] terms = termsText.toLowerCase().split("\\s+");

        List<String> finalTerms = new ArrayList<>(Arrays.asList(terms));
        finalTerms.add("topic");

        return new HashSet<>(finalTerms);
    }


//
//    private void checkTopicEntityExists(String path, String protocol, String hardwareType) {
//
//        var replaceWhitespacesWithDashAndLowercaseText =
//                TextUtils.replaceWhitespacesWithDashAndLowercaseText(hardwareType);
//
//        var pathWithProtocol = "'" + path + "' path with protocol '" + protocol + "' ";
//        var andHardwareType = "and hardware type '" + hardwareType + "' ";
//
//        if (topicRepository.isTopicEntityExists(path, protocol, replaceWhitespacesWithDashAndLowercaseText)) {
//            var errorMessage = pathWithProtocol + andHardwareType + "already exists";
//
//            log.error(errorMessage);
//            throw new TopicServiceException(errorMessage);
//        }
//
//        log.debug(pathWithProtocol + andHardwareType + "does not exist");
//    }
//
//    private void checkTopicEntityHardwareTypeAndTopicTypeIsPresent(String hardwarePresented,
//                                                                   String topicTypePresented) {
//
//        String hardware = TopicEntityConstants.ACTUATOR;
//        if (topicTypePresented.equals(TopicEntityConstants.SUBSCRIBE)) hardware = TopicEntityConstants.SENSOR;
//
//        var htLowercaseAndReplaceWhitespacesWithDash =
//                TextUtils.replaceWhitespacesWithDashAndLowercaseText(hardwarePresented);
//        var hardwareMessagePrefix = "'" + hardwarePresented + "' hardware type ";
//
//        if (!topicRepository.isTopicEntityHardwareExists(htLowercaseAndReplaceWhitespacesWithDash)) {
//            var errorMessage = hardwareMessagePrefix + "not present";
//            log.error(errorMessage);
//            throw new TopicServiceException(errorMessage);
//        }
//        log.debug(hardwareMessagePrefix);
//
//        if (!topicRepository.isTopicEntitySubClassOfHardware(htLowercaseAndReplaceWhitespacesWithDash, hardware)) {
//            String actualTopicType = topicTypePresented.equals(TopicEntityConstants.SUBSCRIBE) ? TopicEntityConstants.PUBLISH : TopicEntityConstants.SUBSCRIBE;
//            throw new TopicServiceException("'" +
//                    hardwarePresented + "' hardware type expects topics type of '" + actualTopicType +
//                    "' but given topics type '" + topicTypePresented + "'");
//        }
//    }
}

