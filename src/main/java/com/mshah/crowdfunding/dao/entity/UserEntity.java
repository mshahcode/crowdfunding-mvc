package com.mshah.crowdfunding.dao.entity;


import com.mshah.crowdfunding.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    @Builder.Default
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;

    private String nickname;

    @Column(columnDefinition = "TIMESTAMP", updatable = false)
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    private Instant updatedAt;
}
