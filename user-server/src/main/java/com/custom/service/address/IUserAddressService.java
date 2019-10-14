package com.custom.service.address;

import com.custom.entity.UserAddress;
import org.springframework.data.domain.Page;

/**
 * 用户收货地址
 *
 * @see UserAddress
 * created by fuyd on 2019-07-02
 */
public interface IUserAddressService {

    /**
     * 通过用户id获取地址列表
     *
     * @param loginId 用户id
     *                {@link com.custom.entity.UserLogin}
     * @param page    页数
     *                {@link com.custom.common.Pages}
     * @return
     */
    Page<UserAddress> userAddresss(Integer loginId, Integer page);

    /**
     * 获取默认地址
     *
     * @param loginId 用户id
     *                {@link com.custom.entity.UserLogin}
     * @return
     */
    UserAddress userAddressDefault(Integer loginId);

    /**
     * 地址详情
     *
     * @param id 地址id
     *           {@link UserAddress}
     * @return
     */
    UserAddress userAddressInfo(Integer id);

    /**
     * 保存地址信息
     *
     * @param userAddress 地址信息
     * @return
     */
    UserAddress insertUserAddress(UserAddress userAddress);

    /**
     * 删除地址信息
     *
     * @param id      地址id
     * @param loginId 用户id
     *                {@link com.custom.entity.UserLogin}
     */
    void deleteUserAddress(Integer id, Integer loginId);

    /**
     * 修改收货地址
     *
     * @param userAddress 收货信息
     * @return
     */
    UserAddress updateUserAddress(UserAddress userAddress);
}
