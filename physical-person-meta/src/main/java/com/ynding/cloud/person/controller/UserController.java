package com.ynding.cloud.person.controller;

import com.ynding.cloud.common.model.bo.GQuery;
import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.person.entity.User;
import com.ynding.cloud.person.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author dyn
 * @version 2020/1/20
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "userController", tags = "user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findByUsername")
    @ApiOperation(value = "查询用户", produces = "application/json")
    public ResponseBean findByUsername(@RequestParam String username) {
        return ResponseBean.ok(userService.findByUsername(username));
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加用户", produces = "application/json")
    public ResponseBean save(@RequestBody User user) {
        userService.save(user);
        return ResponseBean.ok(1);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询列表", produces = "application/json")
    public ResponseBean findList(@RequestParam Map<String, Object> params) {
        GQuery query = new GQuery(params);
        List<User> users = userService.findList(query);
        return ResponseBean.ok(users);
    }

}
