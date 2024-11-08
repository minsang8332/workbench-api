package com.minsang8332.workbenchapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;

@Table(schema = "workbench")
@Entity
@Getter
@Setter
@EnableJpaAuditing
public class Workbench {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 앱 제품 번호
    @Column(length = 30, unique = true)
    @Size(max = 30, message = "{entity.app.sku.size}")
    @NotNull(message = "{entity.app.sku.notnull}")
    private String sku;

    @Column(name = "is_lock", nullable = false)
    private Boolean isLock = false;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
