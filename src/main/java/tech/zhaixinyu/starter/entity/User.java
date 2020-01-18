package tech.zhaixinyu.starter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Project: spring-boot-rest-starter
 * Package: tech.zhaixinyu.springbootreststarter.entity
 * ClassName: User
 * Description: Description
 * Created by @author Xinyu on 1/9/20 22:02
 * <p>
 * Table - 映射表名
 * Id - 主键
 * GeneratedValue(strategy=GenerationType.IDENTITY) - 自动递增生成
 * Column(name = “”,columnDefinition=”default COMMENT ‘’”) - 字段名、类型、注释
 * UpdateTimestamp - 更新时自动更新时间
 * CreationTimestamp - 创建时自动更新时间
 */

@Entity
@Table(name = "sys_user")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -9191841561915392410L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", columnDefinition = "varchar(64) unique not null comment '用户名'")
    private String username;

    @Column(name = "password", columnDefinition = "varchar not null comment '密码'")
    private String password;

    @Column(name = "roles", columnDefinition = "varchar not null comment '权限'")
    private String roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Post> posts;
}
