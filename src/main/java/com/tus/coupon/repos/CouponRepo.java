package com.tus.coupon.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tus.coupon.model.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);

	@Query("SELECT c FROM Coupon c WHERE c.startDate >= :startDate AND c.startDate <= :endDate")
    List<Coupon> findByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);


}
