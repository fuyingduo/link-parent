package com.monitor.dao.account;

import com.monitor.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by fuyd on 2019-07-03
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    UserAccount findUserAccountByUid(Integer uid);
}
