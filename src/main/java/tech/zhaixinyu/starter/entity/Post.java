package tech.zhaixinyu.starter.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Project: spring-boot-rest-starter
 * Package: tech.zhaixinyu.springbootreststarter.entity
 * ClassName: Post
 * Description: 帖子实体类
 * Created by @author Xinyu on 1/11/20 20:03
 */
@Entity
@Table(name = "res_todo_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {

    private static final long serialVersionUID = 1232604494056337675L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "varchar not null comment '内容'")
    private String content;

    @CreationTimestamp
    @Column(name = "create_date_time", columnDefinition = "timestamp not null comment '创建时间'")
    private Timestamp createDateTime;

    @UpdateTimestamp
    @Column(name = "update_date_time", columnDefinition = "timestamp not null comment '更新时间'")
    private Timestamp updateDateTime;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private User user;
}
