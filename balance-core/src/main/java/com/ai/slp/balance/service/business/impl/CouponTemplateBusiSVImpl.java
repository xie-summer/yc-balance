package com.ai.slp.balance.service.business.impl;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.slp.balance.api.coupontemplate.param.FunCouponDetailQueryRequest;
import com.ai.slp.balance.api.coupontemplate.param.FunCouponDetailResponse;
import com.ai.slp.balance.api.coupontemplate.param.FunCouponTemplateQueryRequest;
import com.ai.slp.balance.api.coupontemplate.param.FunCouponTemplateResponse;
import com.ai.slp.balance.api.coupontemplate.param.SaveFunCouponTemplate;
import com.ai.slp.balance.constants.SeqConstants;
import com.ai.slp.balance.dao.mapper.bo.FunCouponTemplate;
import com.ai.slp.balance.dao.mapper.bo.FunCouponUseRule;
import com.ai.slp.balance.service.atom.interfaces.ICouponTemplateAtomSV;
import com.ai.slp.balance.service.atom.interfaces.ICouponUseRuleAtomSV;
import com.ai.slp.balance.service.business.interfaces.ICouponTemplateBusiSV;
@Service
@Transactional
public class CouponTemplateBusiSVImpl implements ICouponTemplateBusiSV {
	
	@Autowired
	private ICouponTemplateAtomSV couponTemplateAtomSV;
	
	@Autowired
	private ICouponUseRuleAtomSV couponUseRuleAtomSV;
	
	/*@Autowired
	private IDiscountCouponAtomSV discountCouponAtomSV;*/
	
	/**
	/**
	 * 模糊查询优惠券模板列表
	 */
	@Override
	public PageInfo<FunCouponTemplateResponse> queryCouponTemplet(FunCouponTemplateQueryRequest funCouponTemplateQueryRequest) throws BusinessException, SystemException{
        PageInfo<FunCouponTemplateResponse> funCouponTemplateResponsePageInfo = couponTemplateAtomSV.funCouponTemplateQueryRequest(funCouponTemplateQueryRequest);
		return funCouponTemplateResponsePageInfo;
	}
	/**
	 * POI导出优惠券模板列表生成Excel
	 */
	@Override
	public List<FunCouponTemplateResponse> exportCouponTempletList(FunCouponTemplateQueryRequest funCouponTemplateQueryRequest) throws BusinessException, SystemException{
		return couponTemplateAtomSV.exportCouponTempletList(funCouponTemplateQueryRequest);
	}
	/**
	 * 检测名称唯一
	 */
	@Override
	public Integer checkCouponTemplateName(String couponName) throws BusinessException, SystemException{
		return couponTemplateAtomSV.checkCouponTemplateName(couponName);
	}
	/**
	 * 添加优惠券模板
	 */
	@Override
	public Integer saveCouponTempletList(SaveFunCouponTemplate req) throws BusinessException, SystemException {
		FunCouponTemplate funCouponTemplate = new FunCouponTemplate();
		funCouponTemplate.setCouponName(req.getCouponName());
		funCouponTemplate.setCouponDesc(req.getCouponDesc());
        funCouponTemplate.setCreateTime(DateUtil.getSysDate());
        if(req.getEffectiveTime() != null){
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	funCouponTemplate.setEffectiveTime(req.getEffectiveTime());
        	Calendar c = Calendar.getInstance();
        	c.add(Calendar.DAY_OF_MONTH, req.getEffectiveTime());
        	funCouponTemplate.setEffectiveEndTime(Timestamp.valueOf(format.format(c.getTime())));
        	funCouponTemplate.setEffectiveStartTime(DateUtil.getSysDate());
        }else{
        	funCouponTemplate.setEffectiveEndTime(Timestamp.valueOf(req.getEffectiveEndTime()));
        	funCouponTemplate.setEffectiveStartTime(Timestamp.valueOf(req.getEffectiveStartTime()));
        }
        funCouponTemplate.setCreateOperator(req.getCreateOperator());
		funCouponTemplate.setFaceValue(req.getFaceValue()*1000);
		funCouponTemplate.setFaceValueDown(req.getFaceValueDown());
		funCouponTemplate.setFaceValueUp(req.getFaceValueUp());
		funCouponTemplate.setMaxCountIssue(req.getMaxCountIssue());
		funCouponTemplate.setReceiveEndTime(Timestamp.valueOf(req.getReceiveEndTime()));
		funCouponTemplate.setReceiveStartTime(Timestamp.valueOf(req.getReceiveStartTime()));
		funCouponTemplate.setCurrencyUnit(req.getCurrencyUnit());
		funCouponTemplate.setStatus(req.getStatus());
		funCouponTemplate.setUsedScene(req.getUsedScene());
		funCouponTemplate.setUseLimits(req.getUseLimits());
		if(req.getCouponUserId() == "0"){
			funCouponTemplate.setCouponUserId(req.getCouponUserId());
		}else{
			FunCouponUseRule funCouponUseRule = new FunCouponUseRule();
			String couponUserId = SeqUtil.getNewId(SeqConstants.FUN_COUPON_USE_RULE$COUPON_USER_ID).toString();
	    	funCouponUseRule.setCouponUserId(couponUserId);
	    	funCouponUseRule.setFaceValue(req.getFaceValue());
	    	funCouponUseRule.setRequiredMoneyAmount(req.getRequiredMoneyAmount()*1000);
	    	funCouponUseRule.setCurrencyUnit(funCouponTemplate.getCurrencyUnit());
	    	Date date1=new Date();
	        DateFormat format1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        funCouponUseRule.setCreateTime(Timestamp.valueOf(format1.format(date1)));
	        couponUseRuleAtomSV.insertBuildCouponUseRule(funCouponUseRule);
	        funCouponTemplate.setCouponUserId(couponUserId);
		}
		return couponTemplateAtomSV.saveCouponTempletList(funCouponTemplate);
	}
	/**
	 * 根据优惠券模板iD查询优惠券明细
	 */
	@Override
	public PageInfo<FunCouponDetailResponse> queryFunCouponDetail(FunCouponDetailQueryRequest param) throws BusinessException, SystemException  {
		return couponTemplateAtomSV.queryFunCouponDetail(param);
	}
	/**
	 * 删除优惠券模板
	 */
	@Override
	public Integer deleteCouponTemplate(Integer templateId) throws BusinessException, SystemException{
		return couponTemplateAtomSV.deleteCouponTemplate(templateId);
	}
	
}
