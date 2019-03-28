package com.yin.user.dao;

import com.yin.user.entity.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 用户DAO
 *
 * @author yin.weilong
 * @date 2019.03.15
 */
@Repository
public interface UserDao extends JpaRepository<UserPo, String>, JpaSpecificationExecutor {

    /**
     * 根据账户查询用户
     *
     * @param account
     * @return
     */
    UserPo findByAccount(String account);
}
