package com.example.hitit.demo.service.implementation;

import com.example.hitit.demo.dtos.TweetDto;
import com.example.hitit.demo.dtos.UserDto;
import com.example.hitit.demo.entity.Tweet;
import com.example.hitit.demo.entity.User;
import com.example.hitit.demo.repository.TweetRepository;
import com.example.hitit.demo.service.interfaces.TweetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final ModelMapper modelMapper;
    private final UserServiceImpl userService;
    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository, ModelMapper modelMapper, UserServiceImpl userService) {
        this.tweetRepository = tweetRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public TweetDto getTweetById(Long id) {
        Optional<Tweet> tweet = tweetRepository.findById(id);
        return tweet.map(value -> modelMapper.map(value,TweetDto.class) ).orElse(null);
    }

    @Override
    public List<TweetDto> getTweets() {
        List<Tweet> tweets = tweetRepository.findAll();
        List<TweetDto> result = new ArrayList<>();
        for (Tweet t : tweets ) {

            TweetDto temp = modelMapper.map(t,TweetDto.class);
            temp.setOwnerEmail(t.getOwner().getEmail());
            result.add(temp);
        }
        return result;
    }

    @Override
    public List<TweetDto> getTweetsByOwnerId(String email) {
        Long id = userService.getUserIdByEmail(email);
        List<Tweet> tweets = tweetRepository.getTweetsByOwnerId(id);

        List<TweetDto> result = new ArrayList<>();
        for (Tweet tw: tweets) {
            TweetDto temp =  modelMapper.map(tw,TweetDto.class);
            temp.setOwnerEmail(tw.getOwner().getEmail());
            result.add(temp);
        }
        return result;
    }

    @Override
    public TweetDto createTweet(TweetDto tweet) {


        Tweet tweet1 = modelMapper.map(tweet,Tweet.class);
        Long ownerId = userService.getUserIdByEmail(tweet.getOwnerEmail());
        UserDto ownerDto = userService.getUser(ownerId);
        User owner = modelMapper.map(ownerDto,User.class);
        owner.setId(ownerId);
        tweet1.setOwner(owner);
        tweet1.setCreatedAt(LocalDateTime.now());
        TweetDto result =modelMapper.map(tweetRepository.save(tweet1),TweetDto.class);
        result.setOwnerEmail(tweet.getOwnerEmail());
        return result;
    }

    @Override
    public TweetDto updateTweet(Long id, TweetDto tweet) {
        Optional<Tweet> result = tweetRepository.findById(id);
        if (result.isPresent()){
            result.get().setContent(tweet.getContent());
            return modelMapper.map(tweetRepository.save(result.get()),TweetDto.class);
        }
        return null;
    }

    @Override
    public Boolean deleteTweet(Long id) {
        Optional<Tweet> result = tweetRepository.findById(id);
        if (result.isPresent()){
            tweetRepository.delete(result.get());
            return true;
        }
        return false;
    }


}
