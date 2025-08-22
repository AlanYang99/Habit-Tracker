package com.habittracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "current_streak")
public class CurrentStreak extends AbstractStreak {
}
