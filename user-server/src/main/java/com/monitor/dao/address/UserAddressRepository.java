package com.monitor.dao.address;

import com.monitor.common.Log;
import com.monitor.entity.UserAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * created by fuyd on 2019-07-03
 */
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {

    /**
     * 用户下所有收货地址
     *
     * @param uid 用户id
     * @return
     */
    List<UserAddress> findUserAddressesByUid(Integer uid);

    /**
     * 分页查询收货地址
     *
     * @param uid      用户id
     * @param pageable 分页
     * @return
     */
    Page<UserAddress> findUserAddressByUid(Integer uid, Pageable pageable);

    /**
     * 用户默认收获地址
     *
     * @param uid            用户id
     * @param defaultAddress 是否默认
     * @return
     */
    UserAddress findUserAddressByUidAndDefaultAddress(Integer uid, Integer defaultAddress);

    /**
     * 地址详情
     *
     * @param id 地址id
     * @return
     */
    @Log(value = "用户信息")
    UserAddress findUserAddressById(Integer id);
}
