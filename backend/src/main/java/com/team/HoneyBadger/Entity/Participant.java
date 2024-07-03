package com.team.HoneyBadger.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participant {
    // 채팅 참가자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Chatroom chatroom;

    @Builder
    public Participant(SiteUser user, Chatroom chatroom) {
        this.user = user;
        this.chatroom = chatroom;
    }
}
