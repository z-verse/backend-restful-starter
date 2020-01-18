package tech.zhaixinyu.starter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.zhaixinyu.starter.entity.User;
import tech.zhaixinyu.starter.service.UserService;

import javax.annotation.Resource;
import java.util.List;

import static tech.zhaixinyu.starter.utils.Constants.RESOURCE_ENDPOINT;

/**
 * Project: backend-restful-starter
 * Package: tech.zhaixinyu.starter.controller
 * ClassName: UserController
 * Description: Description
 * Created by @author Xinyu on 1/18/20 22:53
 */
@RestController
@RequestMapping(RESOURCE_ENDPOINT + "users")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }
}
