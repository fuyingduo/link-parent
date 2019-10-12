package com.monitor.dao.loginout;

import com.monitor.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by fuyd on 2019-07-03
 */
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {

    UserLogin findUserLoginByUserName(String userName);
}
