package com.majorproject.StackOverflowClone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Long reputation;
    private Long votes;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "user_id",name = "user_id")
    private Set<Question> questions;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "user_id",name = "user_id")
    private Set<Answer> answers;
    @ManyToMany(mappedBy = "votedUpByUsers")
    private Set<Answer> votedUpAnswers;
    @ManyToMany(mappedBy = "votedDownByUsers")
    private Set<Answer> votedDownAnswers;
    @ManyToMany(mappedBy = "votedUpByUsers")
    private Set<Question> votedUpQuestions;
    @ManyToMany(mappedBy = "votedDownByUsers")
    private Set<Question> votedDownQuestions;
    @ManyToMany
    @JoinTable(name = "user_badges",
    joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "badge_id"))
    private Set<Badge> badges;
}
