package com.lukas8219.pollbe.data.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "polls")
@Getter
@Setter
public class Poll {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "description", columnDefinition = "MEDIUMTEXT")
    private String description;

    @OneToMany(mappedBy = "poll", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<PollVote> votes;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

}
