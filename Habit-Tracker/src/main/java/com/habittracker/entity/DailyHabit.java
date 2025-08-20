package com.habittracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Habits")
public class DailyHabit extends AbstractHabit {}
