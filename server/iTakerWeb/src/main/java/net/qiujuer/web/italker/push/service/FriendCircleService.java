package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;
import net.qiujuer.web.italker.push.bean.api.circle.CircleModel;
import net.qiujuer.web.italker.push.bean.card.CircleCard;
import net.qiujuer.web.italker.push.bean.db.Circle;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.factory.CircleFactory;
import net.qiujuer.web.italker.push.factory.UserFactory;
import net.qiujuer.web.italker.push.utils.Hib;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author 戈传光 on 2018/1/26.
 */
// 127.0.0.1/api/circle/...
@Path("/circle")
public class FriendCircleService extends BaseService {

    // 上传朋友圈
    @POST
    @Path("/upload")
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<CircleModel> upload(CircleModel model) {
        // 数据库存储
        return Hib.query(session -> {
            Circle circle = new Circle();
            circle.buildCircle(model, UserFactory.findById(model.personId));
            session.save(circle);
            return ResponseModel.buildOk(model);
        });
    }


    // 拉取朋友圈动态
    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<List<CircleCard>> list() {
        User self = getSelf();
        List<CircleCard> list = CircleFactory.list(self);
        return ResponseModel.buildOk(list);
    }
}
