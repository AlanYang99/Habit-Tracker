package com.habittracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @CreatedDate
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private Instant createdAt;
//
//    @CreatedBy
//    @Column(name = "created_by", nullable = false, updatable = false)
//    private String createdBy;
//
//    @UpdateTimestamp
//    @LastModifiedBy
//    @Column(name = "updated_at", insertable = false)
//    private Instant updatedAt;
//
//    @LastModifiedBy
//    @Column(name = "updated_by", insertable = false)
//    private String updatedBy;
}
