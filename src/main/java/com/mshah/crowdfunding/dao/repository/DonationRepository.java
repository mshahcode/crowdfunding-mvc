package com.mshah.crowdfunding.dao.repository;

import com.mshah.crowdfunding.dao.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("""
            SELECT d FROM Donation d JOIN FETCH d.idea WHERE d.idea.id  = :ideaId
            """)
    List<Donation> findAllByIdeaId(Long ideaId);

    @Query("""
            SELECT d FROM Donation d JOIN FETCH d.idea WHERE d.user.id  = :userId
            """)
    List<Donation> findAllByUserId(Long userId);
}