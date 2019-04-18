package de.uni.stuttgart.ipvs.tm.topics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils;
import de.uni.stuttgart.ipvs.tm.auth.UserDetailsImpl;
import de.uni.stuttgart.ipvs.tm.response.ResultsSet;
import de.uni.stuttgart.ipvs.tm.topics.dto.TopicCreateDTO;
import de.uni.stuttgart.ipvs.tm.topics.dto.TopicEditDTO;
import de.uni.stuttgart.ipvs.tm.topics.service.TopicService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/manager/topics")
public class ManagerTopicsController {

    private final TopicService topicService;

    @Autowired
    public ManagerTopicsController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResultsSet getAllTopics(@AuthenticationPrincipal UserDetailsImpl user) {
        return this.topicService.getAllTopics(user.getId());
    }

    @PostMapping
    public ResponseEntity createTopic(@RequestBody TopicCreateDTO topicCreateDTO,
                                      @AuthenticationPrincipal UserDetailsImpl user) {

        var topicId = topicService.createTopic(topicCreateDTO, user.getId());
        return ResponseEntityUtils
                .getResponseEntityWithMessageKey(CREATED, "Created topic with ID '" + topicId + "'");
    }

    @GetMapping(path = "/{id}")
    public ResultsSet getTopic(@PathVariable("id") String entityId,
                               @AuthenticationPrincipal UserDetailsImpl user) {
        return this.topicService.getTopic(user.getId(), entityId);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity editTopic(@PathVariable("id") String topicId,
                          @RequestBody TopicEditDTO topicEditDTO,
                          @AuthenticationPrincipal UserDetailsImpl user) {


        if (!user.getId().equals(topicEditDTO.getOwner()))
            return ResponseEntity.status(FORBIDDEN).build();

        this.topicService.updateTopic(topicEditDTO, topicId);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteTopic(@PathVariable("id") String id,
                            @AuthenticationPrincipal UserDetailsImpl user) {

        this.topicService.deleteTopic(user.getId(), id);
    }


    @PostMapping("/search")
    public ResultsSet searchTopicsUsingTerms(@RequestParam("search_query") String termsText,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return this.topicService.getAllTopicsUsingTermsTextForOwner(userDetails.getId(), termsText);
    }

}
