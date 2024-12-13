package com.mshah.crowdfunding.dao.entity;

import com.mshah.crowdfunding.model.enums.IdeaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

import static com.mshah.crowdfunding.model.enums.IdeaStatus.IN_PROGRESS;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ideas")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Builder.Default
    private BigDecimal goalAmount = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal currentAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private IdeaStatus status = IN_PROGRESS;

    @Column(columnDefinition = "TIMESTAMP", updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private Instant updatedAt;

    @Builder.Default
    private Integer donationsCount = 0;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private UserEntity user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    private String category;
}