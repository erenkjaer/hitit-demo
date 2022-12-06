package com.example.hitit.demo.controller.implementation;

import com.example.hitit.demo.controller.interfaces.TweetController;
import com.example.hitit.demo.dtos.TweetDto;
import com.example.hitit.demo.service.interfaces.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class TweetControllerImpl implements TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetControllerImpl(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @Override
    @GetMapping(path = "tweets")
    public ModelAndView showTweets(){
        ModelAndView mav = new ModelAndView("list-user");
        List<TweetDto> allTweets = tweetService.getTweets();
        mav.addObject("allTweets",allTweets);
        return mav;
    }

}
