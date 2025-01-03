package com.mshah.crowdfunding.dao.repository;

import com.mshah.crowdfunding.dao.entity.Idea;
import com.mshah.crowdfunding.model.enums.IdeaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long>, JpaSpecificationExecutor<Idea> {

    @Query(value = """
            SELECT * FROM ideas WHERE user_id = :userId
            """, nativeQuery = true)
    List<Idea> findByUserId(Integer userId);

    long countByStatus(IdeaStatus status);
}