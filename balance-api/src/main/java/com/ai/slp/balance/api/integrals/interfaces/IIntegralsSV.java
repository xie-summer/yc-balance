package com.ai.slp.balance.api.integrals.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.balance.api.accountmaintain.param.AccountUpdateParam;
import com.ai.slp.balance.api.accountmaintain.param.RegAccReq;
import com.ai.slp.balance.api.integrals.param.IntegralsResponse;
import com.ai.slp.balance.api.integrals.param.UpdateIntegralsParam;
import com.ai.slp.balance.api.integrals.param.UpdateIntegralsResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 积分接口类 <br>
 *
 * Date: 2017年5月15日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author lxk
 */
@Path("/integralsService")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IIntegralsSV {

    /**
     * 积分变更.<br>
     * 
     * @param updateIntegralsParam
     * @return 成功,失败
     * @author lxk
     * @ApiDocMethod
     * @ApiCode ABM_0030
     * @RestRelativeURL integralsService/updateIntegrals
     */
	@POST
	@Path("/updateIntegrals")
    public UpdateIntegralsResponse updateIntegrals(UpdateIntegralsParam updateIntegralsParam) throws BusinessException,SystemException;

    /**
     * 积分查询.<br>
     *
     * @param userID
     * @return 积分报文体
     * @author lxk
     * @ApiDocMethod
     * @ApiCode ABM_0030
     * @RestRelativeURL integralsService/queryIntegrals
     */
    @POST
    @Path("/queryIntegrals")
    public IntegralsResponse queryIntegrals(String userID) throws BusinessException,SystemException;

}