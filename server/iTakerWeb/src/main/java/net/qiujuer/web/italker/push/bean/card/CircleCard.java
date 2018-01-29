package net.qiujuer.web.italker.push.bean.card;


import com.google.gson.annotations.Expose;
import net.qiujuer.web.italker.push.bean.db.Circle;
import net.qiujuer.web.italker.push.factory.UserFactory;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 朋友圈的Model，对应数据库
 *
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
@Entity
@Table(name = "TB_CIRCLE")
public class CircleCard {

    public CircleCard(Circle circle) {
        this.id = circle.getId();
        this.portrait = circle.getPortrait();
        this.description = circle.getDescription();
        this.imgs = circle.getImgs();
        this.personId = circle.getPersonId();
        this.name = UserFactory.findById(personId).getName();
    }

    @Expose
    public String id;
    @Expose
    public String name;
    @Expose
    public String portrait;

    @Expose
    public String description;

    @Expose
    public String imgs;

    public String personId;

    @Expose
    public LocalDateTime createAt = LocalDateTime.now();
    @Expose
    public LocalDateTime updateAt = LocalDateTime.now();
    @Expose
    public LocalDateTime lastReceivedAt = LocalDateTime.now();


}
