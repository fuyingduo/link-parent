package com.custom.service.address.impl;

import com.custom.common.Pages;
import com.custom.dao.address.UserAddressRepository;
import com.custom.entity.UserAddress;
import com.custom.enums.WhetherEnum;
import com.custom.service.address.IUserAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * created by fuyd on 2019-07-02
 */
@Service
public class UserAddressService implements IUserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAddressService.class);

    @Override
    public Page<UserAddress> userAddresss(Integer loginId, Integer page) {
        return userAddressRepository.findUserAddressByUid(loginId, Pages.byDesc(page));
    }

    @Override
    public UserAddress userAddressDefault(Integer loginId) {
        return userAddressRepository.findUserAddressByUidAndDefaultAddress(loginId, WhetherEnum.IS.code());
    }

    @Override
    public UserAddress userAddressInfo(Integer id) {
        return userAddressRepository.findUserAddressById(id);
    }

    @Override
    public UserAddress insertUserAddress(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }

    @Override
    public void deleteUserAddress(Integer id, Integer loginId) {
        UserAddress userAddress = userAddressRepository.findUserAddressById(id);
        if (null == userAddress) {
            LOGGER.error("[删除收货地址] 查询数据不存在! id:{}", id);
            return;
        }
        Integer defaultAddress = userAddress.getDefaultAddress();
        userAddressRepository.deleteById(id);
        if (WhetherEnum.IS.code() == defaultAddress) {
            List<UserAddress> userAddresses = userAddressRepository.findUserAddressesByUid(loginId);
            Optional<UserAddress> opl = userAddresses.stream().findAny();
            opl.ifPresent(address -> {
                address.setDefaultAddress(WhetherEnum.IS.code());
                userAddressRepository.save(address);
            });
        }
    }

    @Override
    public UserAddress updateUserAddress(UserAddress userAddress) {
        UserAddress address = userAddressRepository.findUserAddressById(userAddress.getId());
        BeanUtils.copyProperties(userAddress, address);
        return userAddressRepository.save(address);
    }
}
