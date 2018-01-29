package net.qiujuer.web.italker.push.bean.db;


import net.qiujuer.web.italker.push.bean.api.circle.CircleModel;
import net.qiujuer.web.italker.push.bean.card.CircleCard;
import net.qiujuer.web.italker.push.factory.UserFactory;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 朋友圈的Model，对应数据库
 *
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
@Entity
@Table(name = "TB_CIRCLE")
public class Circle {

    // 这是一个主键
    @Id
    @PrimaryKeyJoinColumn
    // 主键生成存储的类型为UUID
    @GeneratedValue(generator = "uuid")
    // 把uuid的生成器定义为uuid2，uuid2是常规的UUID toString
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    // 不允许更改，不允许为null
    @Column(updatable = false, nullable = false)
    private String id;


    // 头像允许为null
    @Column
    private String portrait;

    // 描述不允许更改，不允许为null
    @Column(updatable = false, nullable = false)
    private String description;

    // 图片附件
    @Column(updatable = false, nullable = false)
    private String imgs;


    // 也是多对1，你可以发很多动态，每次动态都是一条记录
    // 所有就是 多个Circle 对应 一个 User 的情况
    @ManyToOne(optional = false)
    // 定义关联的表字段名为personId，对应的是User.id
    // 定义的是数据库中的存储字段
    @JoinColumn(name = "personId")
    private User person;
    // 把这个列提取到我们的Model中，不允许为null，不允许更新，插入
    @Column(nullable = false, updatable = false, insertable = false)
    private String personId;

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    // 定义为创建时间戳，在创建时就已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    // 定义为更新时间戳，在创建时就已经写入
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    // 最后一次收到消息的时间
    @Column
    private LocalDateTime lastReceivedAt = LocalDateTime.now();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgs() {
        return imgs;
    }

    public String getPersonId() {
        return personId;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getLastReceivedAt() {
        return lastReceivedAt;
    }

    public void setLastReceivedAt(LocalDateTime lastReceivedAt) {
        this.lastReceivedAt = lastReceivedAt;
    }


    public void buildCircle(CircleModel model, User person) {

        this.setDescription(model.description);
        this.setImgs(model.imgs);
        this.person = person;
        if (!model.portrait.equals("")) {
            this.setPortrait(model.portrait);
        } else {
            User user = UserFactory.findById(model.personId);
            this.setPortrait(user.getPortrait());
        }
    }


    public CircleCard buildCard(Circle model) {
        CircleCard card = new CircleCard(model);

        return card;
    }
}
