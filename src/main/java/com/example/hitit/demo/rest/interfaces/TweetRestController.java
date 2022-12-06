package com.example.hitit.demo.rest.interfaces;

import com.example.hitit.demo.dtos.TweetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public interface TweetRestController {
    @GetMapping("/tweets")
    ResponseEntity<List<TweetDto>> getTweets();
    @GetMapping("/tweets/{id}")
    ResponseEntity<TweetDto> getTweetById(@PathVariable Long id);
    @PostMapping("/tweets")
    ResponseEntity<TweetDto> createTweet(@RequestBody TweetDto tweet);

    @GetMapping(path = "/tweets/owner/{email}")
    ResponseEntity<List<TweetDto>> getTweetByOwnerId(@PathVariable String email);
}
