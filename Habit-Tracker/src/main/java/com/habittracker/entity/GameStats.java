package com.habittracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Game Stats")
public class GameStats extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "user_level", nullable = false)
    private int userLevel;

    @Column(name = "user_exp", nullable = false)
    private int userExp;
}
