package net.qiujuer.web.italker.push.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.qiujuer.web.italker.push.LoginBean;
import net.qiujuer.web.italker.push.bean.api.account.RegisterModel;
import net.qiujuer.web.italker.push.bean.api.account.UserModel;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.utils.Hib;
import org.hibernate.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author qiujuer
 */
// 127.0.0.1/api/account/...
@Path("/account")
public class AccountService {

    //GET 127.0.0.1/api/account/login
    @GET
    @Path("/login")
    public String get() {
        Gson gson =new Gson();
        LoginBean bean =new LoginBean();
        bean.code=200;
        bean.info="sucess";
        LoginBean.Data data=new LoginBean.Data();
        data.name="zhangsan";
        data.token="123456";
        bean.data =data;
        String s = gson.toJson(bean);
        return s;
    }


    //POST 127.0.0.1/api/account/login
    @POST
    @Path("/login")
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserModel post(UserModel user) {

        UserModel model = new UserModel();
        model.name ="张三";
        model.password="123455";
        model.phone="18365284760";

        return  model;
    }

    //注册
    @POST
    @Path("/register")
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RegisterModel register(RegisterModel userModel){

        Hib.query(session -> session.save(userModel));

        return  userModel;
    }

}
