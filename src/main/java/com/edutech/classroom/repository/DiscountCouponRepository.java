package com.edutech.classroom.repository;

import com.edutech.classroom.entity.DiscountCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon, Integer> {
}