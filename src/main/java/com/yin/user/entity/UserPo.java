package com.yin.user.entity;

import com.yin.common.entity.po.BasePo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户
 *
 * @author yin.weilong
 * @date 2019.03.15
 */
@Entity
@Table(name = "u_user")
@Getter
@Setter
public class UserPo extends BasePo {

    @Column(name = "name")
    private String name;

    @Column(name = "account")
    private String account;

    @Column(name = "passwd")
    private String passwd;

}
