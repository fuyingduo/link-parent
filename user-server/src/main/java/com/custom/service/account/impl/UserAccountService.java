package com.custom.service.account.impl;

import com.custom.common.Querys;
import com.custom.dao.account.UserAccountRepository;
import com.custom.entity.UserAccount;
import com.custom.service.account.IUserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 账户信息
 * created by fuyd on 2019-07-03
 */
@Service
public class UserAccountService implements IUserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountService.class);

    @Override
    public Boolean accountExists(Integer uid) {
        UserAccount account = new UserAccount();
        account.setUid(uid);
        LOGGER.error("[account] 随便写点什么111");
        return userAccountRepository.exists(Querys.builder().build(account));
    }

    @Override
    public Integer createAccount(Integer uid) {
        if (this.accountExists(uid)) {
            LOGGER.warn("[创建账户] 用户id:{} 已经开通账户信息", uid);
            return uid;
        }
        UserAccount account = new UserAccount();
        account.setUid(uid);
        UserAccount save = userAccountRepository.save(account);
        if (null == save.getId()) {
            LOGGER.error("[创建账户] 账户保存失败");
            return null;
        }
        return save.getId();
    }

    @Override
    public Long balance(Integer uid) {
        UserAccount account = userAccountRepository.findUserAccountByUid(uid);
        if (null == account) {
            LOGGER.error("[查询账户余额] 暂未开通账户!");
            return null;
        }
        return account.getBalance();
    }

    @Override
    public Long increaseTheBalance(Integer uid, Long amount) {
        Optional<UserAccount> userAccountOpl = userAccountRepository.findById(uid);
        if (!userAccountOpl.isPresent()) {
            LOGGER.error("[增加账户余额] 账户信息不存在 id:{}", uid);
            return null;
        }
        UserAccount userAccount = userAccountOpl.get();
        userAccount.setBalance(userAccount.getBalance() + amount);
        UserAccount account = userAccountRepository.save(userAccount);
        return account.getBalance();
    }

    @Override
    public Long reduceTheBalance(Integer uid, Long amount) {
        Optional<UserAccount> userAccountOpl = userAccountRepository.findById(uid);
        if (!userAccountOpl.isPresent()) {
            LOGGER.error("[减少账户余额] 账户信息不存在 id:{}", uid);
            return null;
        }
        UserAccount userAccount = userAccountOpl.get();
        if (userAccount.getBalance() < amount) {
            LOGGER.error("[减少账户余额] 可用余额不足 id:{}", uid);
            return null;
        }
        userAccount.setBalance(userAccount.getBalance() - amount);
        UserAccount account = userAccountRepository.save(userAccount);
        return account.getBalance();
    }
}
