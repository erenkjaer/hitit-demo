package com.example.hitit.demo.repository;

import com.example.hitit.demo.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {
    @Query("SELECT t FROM Tweet t WHERE t.owner.id = :id")
    List<Tweet> getTweetsByOwnerId(@Param("id") Long id);
}
