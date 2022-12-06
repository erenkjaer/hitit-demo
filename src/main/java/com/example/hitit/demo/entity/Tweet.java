package com.example.hitit.demo.entity;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(
        name = "tbl_tweet"
)
public class Tweet {
    @Id
    @SequenceGenerator(
            name="tweet_sequence",
            sequenceName = "tweet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tweet_sequence"
    )
    private Long id;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tweet tweet = (Tweet) o;
        return id != null && Objects.equals(id, tweet.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
