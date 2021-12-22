package com.lukas8219.pollbe.data.domain;

import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "poll_votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"poll_id", "voted_by"})})
@Getter
@Setter
public class PollVote {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Poll poll;

    @Column(name = "decision")
    @Enumerated(EnumType.STRING)
    private VoteDecisionEnum decision;

    @Column(name = "voted_at")
    private LocalDateTime votedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voted_by")
    private User votedBy;

}
