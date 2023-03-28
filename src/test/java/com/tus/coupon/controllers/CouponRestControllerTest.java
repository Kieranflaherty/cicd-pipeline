package com.tus.coupon.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import com.tus.coupon.model.Coupon;
import com.tus.coupon.repos.CouponRepo;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

@SpringBootTest
class CouponRestControllerTest {
	
	@Autowired
	CouponRestController couponRestController;
	
	@MockBean
	CouponRepo repo;
	
	@Captor
	ArgumentCaptor<Coupon> captor;

	@Test
	void testCreateCoupon() {
		Coupon coupon = makeCoupon();
		Coupon couponSaved=makeCoupon();
		couponSaved.setId(1L);
		when(repo.save(any())).thenReturn(couponSaved);
		Coupon couponReturn = couponRestController.create(coupon);
		assertEquals(couponReturn.getId(),1L);
		assertEquals(couponReturn.getCode(),"AUTUMN_SALE");
		assertEquals(couponReturn.getDiscount(),BigDecimal.valueOf(10.0));
		assertEquals(couponReturn.getExpDate(),"31/12/2024");
		verify(repo, new Times(1)).save(captor.capture());
	}
	
	@Test
	void testGetCouponByCode() {
		Coupon couponSaved=makeCoupon();
		couponSaved.setId(1L);
		when(repo.findByCode("AUTUMN_SALE")).thenReturn(couponSaved);
		Coupon couponReturn = couponRestController.getCoupon("AUTUMN_SALE");
		assertEquals(couponReturn.getId(),1L);
		assertEquals(couponReturn.getCode(),"AUTUMN_SALE");
		assertEquals(couponReturn.getDiscount(),BigDecimal.valueOf(10.0));
		assertEquals(couponReturn.getExpDate(),"31/12/2024");
		verify(repo, new Times(1)).findByCode("AUTUMN_SALE");
	}
	
	@Test
	void testFindAllCoupons() {
		Coupon couponSaved=makeCoupon();
		couponSaved.setId(1L);
		ArrayList<Coupon> coupons = new ArrayList<>();
		coupons.add(couponSaved);
		when(repo.findAll()).thenReturn(coupons);
		List<Coupon> couponsFound = couponRestController.getAllCoupons();
		Coupon couponFound= couponsFound.get(0);
		assertEquals(couponFound.getId(),1L);
		assertEquals(couponFound.getCode(),"AUTUMN_SALE");
		assertEquals(couponFound.getDiscount(),BigDecimal.valueOf(10.0));
		assertEquals(couponFound.getExpDate(),"31/12/2024");
		verify(repo, new Times(1)).findAll();
	}
	private Coupon makeCoupon() {
		Coupon coupon = new Coupon();
		coupon.setCode("AUTUMN_SALE");
		coupon.setDiscount(BigDecimal.valueOf(10.0));
		coupon.setExpDate("31/12/2024");
		return coupon;
		
	}

}
