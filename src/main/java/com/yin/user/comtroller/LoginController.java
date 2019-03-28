package com.yin.user.comtroller;

import com.yin.common.controller.BaseJson;
import com.yin.common.entity.bo.UserSessionBo;
import com.yin.common.entity.vo.LoginUserVo;
import com.yin.common.exceptions.MessageException;
import com.yin.user.dao.UserDao;
import com.yin.user.entity.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 登录
 *
 * @author yin.weilong
 * @date 2019.03.15
 */
@RestController
@RequestMapping(value = "api/user/login")
public class LoginController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping(value = "login", consumes = "application/json")
    public BaseJson login(@Validated @RequestBody LoginUserVo loginUserVo, HttpSession session) throws Exception {
        UserPo user = userDao.findByAccount(loginUserVo.getUsername());
        if (user == null) {
            return BaseJson.getError("账号错误！");
        }
        if (!user.getPasswd().equals(loginUserVo.getPassword())) {
            return BaseJson.getError("密码错误！");
        }
        UserSessionBo bo = new UserSessionBo();
        String uuid = UUID.randomUUID().toString();
        bo.setAccount(user.getAccount());
        bo.setToken(uuid);
        bo.setId(user.getId());
        bo.setName(user.getName());
        redisTemplate.opsForValue().set(uuid, bo, 30L, TimeUnit.MINUTES);
        session.setAttribute("user", bo);
        return BaseJson.getSuccess("登录成功", bo);
    }
}
