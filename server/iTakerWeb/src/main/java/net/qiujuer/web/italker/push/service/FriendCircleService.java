package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.api.account.AccountRspModel;
import net.qiujuer.web.italker.push.bean.api.account.LoginModel;
import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by 戈传光 on 2018/1/26.
 */

// 127.0.0.1/api/circle/...
@Path("/circle")
public class FriendCircleService extends BaseService {

    // 上传朋友圈
    @POST
    @Path("/{accountId}/circle")
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<AccountRspModel> upload(@PathParam("accountId") String id) {



        return null;
    }
}
