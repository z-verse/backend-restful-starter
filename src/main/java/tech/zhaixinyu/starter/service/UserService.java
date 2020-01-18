package tech.zhaixinyu.starter.service;

import org.springframework.stereotype.Service;
import tech.zhaixinyu.starter.entity.User;
import tech.zhaixinyu.starter.repo.UserRepo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Project: backend-restful-starter
 * Package: tech.zhaixinyu.starter.service
 * ClassName: UserService
 * Description: Description
 * Created by @author Xinyu on 1/18/20 22:52
 */
@Service
public class UserService {
    @Resource
    private UserRepo userRepo;

    public void create(User user) {
        userRepo.save(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

    public void update(User user) {
        userRepo.save(user);
    }

    public Optional<User> findOne(Long id) {
        return userRepo.findById(id);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
