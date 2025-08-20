package com.habittracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Badges")
public class Badge extends BaseEntity {

    @Column(name = "badge_name", nullable = false)
    public String name;

    @Column(name = "badge_description", nullable = false)
    public String description;

    @Column(name = "image_url", nullable = false)
    public String imageUrl;
}
