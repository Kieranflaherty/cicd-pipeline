package com.tus.coupon.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tus.coupon.model.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);

}
