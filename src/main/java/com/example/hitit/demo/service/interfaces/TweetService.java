package com.example.hitit.demo.service.interfaces;

import com.example.hitit.demo.dtos.TweetDto;

import java.util.List;

public interface TweetService {

    TweetDto getTweetById(Long id);
    List<TweetDto> getTweets();
    List<TweetDto> getTweetsByOwnerId(String email);
    TweetDto updateTweet(Long id,TweetDto tweet);
    TweetDto createTweet(TweetDto tweet);
    Boolean deleteTweet(Long id);
}
