package com.mshah.crowdfunding.dao.repository;

import com.mshah.crowdfunding.dao.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByOrderByUpdatedAtDesc();

    @Query("""
            SELECT r FROM Report r JOIN FETCH r.userEntity WHERE r.userEntity.id  = :userId
            """)
    List<Report> findAllByUserId(Long userId);
}
