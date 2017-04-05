package com.ai.slp.balance.service.atom.interfaces;



import java.util.List;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.balance.api.sendcoupon.param.DeductionCouponRequest;
import com.ai.slp.balance.api.sendcoupon.param.DeductionCouponResponse;
import com.ai.slp.balance.api.sendcoupon.param.FreezeCouponRequest;
import com.ai.slp.balance.api.sendcoupon.param.FunDiscountCouponResponse;
import com.ai.slp.balance.api.sendcoupon.param.QueryCouCountRequest;
import com.ai.slp.balance.api.sendcoupon.param.QueryCouponRequest;
import com.ai.slp.balance.api.sendcoupon.param.SendCouponRequest;

public interface IDiscountCouponAtomSV {

	List<DeductionCouponResponse> deducionCouponCheck(String couponId);
	//查询优惠券明细
	/*public PageInfo<FunCouponDetailResponse> queryFunCouponDetail(FunCouponDetailQueryRequest param);*/

	List<DeductionCouponResponse> queryDisCountCoupon(DeductionCouponRequest param);

	List<FunDiscountCouponResponse> queryCouponByUserId(SendCouponRequest param);

	void updateStateThaw(FreezeCouponRequest param);

	void updateStateFreeze(FreezeCouponRequest param);

	void queryDeducionCoupon(DeductionCouponRequest param);

	Integer findCouponCount(QueryCouCountRequest request);

	PageInfo<DeductionCouponResponse> queryCouponPage(QueryCouponRequest queryCouponRequest);

	List<DeductionCouponResponse> queryDisCountCouponOnly(DeductionCouponRequest param);
	
}
