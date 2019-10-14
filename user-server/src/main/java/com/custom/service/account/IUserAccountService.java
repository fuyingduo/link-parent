package com.custom.service.account;

/**
 * 用户账户接口层
 *
 * @see com.custom.entity.UserAccount
 * created by fuyd on 2019-07-03
 */
public interface IUserAccountService {

    /**
     * 账户是否存在
     *
     * @param uid 用户id
     *            {@link com.custom.entity.UserLogin}
     */
    Boolean accountExists(Integer uid);

    /**
     * 开通账户
     *
     * @param uid 用户id
     *            {@link com.custom.entity.UserLogin}
     */
    Integer createAccount(Integer uid);

    /**
     * 账户余额
     *
     * @param uid 用户id
     *            {@link com.custom.entity.UserLogin}
     */
    Long balance(Integer uid);

    /**
     * 增加账户金额
     *
     * @param uid    用户id
     *               {@link com.custom.entity.UserLogin}
     * @param amount 金额
     */
    Long increaseTheBalance(Integer uid, Long amount);

    /**
     * 减少账户金额
     *
     * @param uid    用户id
     *               {@link com.custom.entity.UserLogin}
     * @param amount 金额
     */
    Long reduceTheBalance(Integer uid, Long amount);
}
