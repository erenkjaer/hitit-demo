package com.example.hitit.demo.rest.implementation;

import com.example.hitit.demo.dtos.TweetDto;
import com.example.hitit.demo.rest.interfaces.TweetRestController;
import com.example.hitit.demo.service.implementation.TweetServiceImpl;
import com.example.hitit.demo.service.interfaces.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path ="api/v1/tweets")
public class TweetRestControllerImpl implements TweetRestController {

    private final TweetService tweetService;

    @Autowired
    public TweetRestControllerImpl(TweetServiceImpl tweetService) {
        this.tweetService = tweetService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TweetDto>> getTweets() {
        List<TweetDto> tweets = tweetService.getTweets();
        return ResponseEntity.ok(tweets);
    }

    @Override
    @GetMapping(path = "/{tweet_id}")
    public ResponseEntity<TweetDto> getTweetById(@PathVariable("tweet_id") Long id) {
        TweetDto tweet = tweetService.getTweetById(id);
        return ResponseEntity.ok(tweet);
    }

    @Override
    @PostMapping
    public ResponseEntity<TweetDto> createTweet(@RequestBody TweetDto tweet) {
        TweetDto createdTweet = tweetService.createTweet(tweet);
        return ResponseEntity.ok(createdTweet);
    }

    @Override
    @GetMapping(path = "/owner/{email}")
    public ResponseEntity<List<TweetDto>> getTweetByOwnerId(@PathVariable("email") String email) {

        List<TweetDto>tweets = tweetService.getTweetsByOwnerId(email);
        return ResponseEntity.ok(tweets);
    }
}
