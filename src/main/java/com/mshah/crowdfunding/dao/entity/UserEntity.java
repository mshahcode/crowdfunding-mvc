package com.mshah.crowdfunding.dao.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


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

    private String nickname;

    @Column(columnDefinition = "TIMESTAMP", updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @Builder.Default
    @ToString.Exclude
    private Set<RoleEntity> roles = new HashSet<>();

    public void addRole(RoleEntity role) {
        roles.add(role);
    }
}
