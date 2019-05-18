package de.uni.stuttgart.ipvs.tm.topics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.tm.auth.UserDetailsImpl;
import de.uni.stuttgart.ipvs.tm.response.ResultsSet;
import de.uni.stuttgart.ipvs.tm.topics.dto.TopicEditDTO;
import de.uni.stuttgart.ipvs.tm.topics.service.TopicService;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/admin/topics")
public class AdminTopicsController {

    private final TopicService topicService;

    @Autowired
    public AdminTopicsController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResultsSet getAllTopics(@AuthenticationPrincipal UserDetailsImpl user) {
        return this.topicService.getAllTopics();
    }

    @GetMapping(path = "/{id}")
    public ResultsSet getTopic(@PathVariable("id") String entityId,
                               @AuthenticationPrincipal UserDetailsImpl user) {
        return this.topicService.getTopicForAdmin(entityId);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void editTopic(@PathVariable("id") String topicId,
                                    @RequestBody TopicEditDTO topicEditDTO,
                                    @AuthenticationPrincipal UserDetailsImpl user) {

        this.topicService.updateTopic(topicEditDTO, topicId);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteTopic(@PathVariable("id") String id) {
        this.topicService.deleteTopic(id);
    }

}
