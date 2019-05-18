package de.uni.stuttgart.ipvs.tm.topics.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.uni.stuttgart.ipvs.tm.response.ResultsSet;
import de.uni.stuttgart.ipvs.tm.topics.service.TopicService;

@RestController
@RequestMapping("/api/consumer/topics")
public class ConsumerTopicsController {

    private final TopicService topicService;

    @Autowired
    public ConsumerTopicsController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping("search")
    public ResultsSet searchTopicsUsingTerms(@RequestParam("search_query") String termsText) {
        return this.topicService.getAllTopicsUsingTermsText(termsText);
    }

}
