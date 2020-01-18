package tech.zhaixinyu.starter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.zhaixinyu.starter.entity.User;

import java.util.Optional;

/**
 * Project: backend-restful-starter
 * Package: tech.zhaixinyu.starter.security
 * ClassName: UserRepo
 * Description: Description
 * Created by @author Xinyu on 1/18/20 22:51
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return User
     */
    Optional<User> findByUsername(String username);
}
