package com.mshah.crowdfunding.dao.repository;

import com.mshah.crowdfunding.dao.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

}