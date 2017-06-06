package com.gudigudigudi.sb.Controller;

import com.gudigudigudi.sb.Model.Topic;
import com.gudigudigudi.sb.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * GET /topics          Gets all topics
 * GET /topics/{id}     Gets the topic
 * POST /topics         Create new topic
 * PUT  /topics/{id}    Update the topic
 * DELETE /topics/{id}  Delete the topic
 */

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topics")
    public List<Topic> getAllTopics(){
        return topicService.getAllTopics();
    }

    @RequestMapping("/topics/{id}")
    public Topic getTopicById(@PathVariable("id") String id){
        return topicService.getTopicById(id);
    }

    @RequestMapping(value = "/topics", method = RequestMethod.POST)
    public void addTopic(@RequestBody Topic topic){
        topicService.addTopic(topic);
    }

    @RequestMapping(value = "/topics/{id}",method = RequestMethod.POST)
    public void updateTopic(@RequestBody Topic topic, @PathVariable String id){
        topicService.updateTopic(id, topic);
    }

    @RequestMapping(value = "/topics/{id}",method = RequestMethod.DELETE)
    public void deleteTopic(@PathVariable String id){
        topicService.deleteTopic(id);
    }
}
