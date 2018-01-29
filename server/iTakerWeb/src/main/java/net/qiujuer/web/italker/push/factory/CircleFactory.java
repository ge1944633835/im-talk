package net.qiujuer.web.italker.push.factory;

import net.qiujuer.web.italker.push.bean.card.CircleCard;
import net.qiujuer.web.italker.push.bean.db.Circle;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.bean.db.UserFollow;
import net.qiujuer.web.italker.push.utils.Hib;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author sunlight Email:qiujuer@live.cn
 * @version 1.0.0
 */
public class CircleFactory {

    // 通过Phone找到User
    public static User findByPhone(String phone) {
        return Hib.query(session -> (User) session
                .createQuery("from User where phone=:inPhone")
                .setParameter("inPhone", phone)
                .uniqueResult());
    }


    /**
     * 获取我的联系人的朋友圈状态
     *
     * @param self User
     * @return List<Circle>
     */
    public static List<CircleCard> list(User self) {
        return Hib.query(session -> {
            // 重新加载一次用户信息到self中，和当前的session绑定
            session.load(self, self.getId());

            // 获取我关注的人
            Set<UserFollow> flows = self.getFollowing();
            List<CircleCard> list = new ArrayList<>();
            for (UserFollow user : flows) {
                List circles = session.createQuery("from Circle where personId=:personId")
                        .setParameter("personId", user.getTargetId())
                        .list();

                for (Object cir:circles) {
                    list.add(((Circle)cir).buildCard((Circle) cir));
                }
            }

            // 还要把我自己的动态也拿出来
            List selfCircles = session.createQuery("from Circle where personId=:personId")
                    .setParameter("personId", self.getId())
                    .list();
            for (Object selfCir:selfCircles) {
                list.add(((Circle)selfCir).buildCard((Circle) selfCir));
            }

            return list;
        });
    }


}
