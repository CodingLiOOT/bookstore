package com.bjtu.bookstore.service.impl;

import com.bjtu.bookstore.entity.Shipping;
import com.bjtu.bookstore.mapper.ShippingMapper;
import com.bjtu.bookstore.service.ShippingService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShippingServiceimpl implements ShippingService {

    @Autowired
    ShippingMapper shippingMapper;


    @Override
    public List<Shipping> getAddress(String uid) {

        List<Shipping> address = shippingMapper.getAddress(uid);
        Preconditions.checkNotNull(address);

        return address;
    }

    @Override
    public void addAddress(Shipping user) {

        List<Shipping> address = shippingMapper.getAddress(user.getUserId());
        shippingMapper.addAddress(user);

    }

    @Override
    public void modifyAddress(Shipping shipping) {
         shippingMapper.modifyAddress(shipping);
    }



    @Override
    public void deleteAddress(Shipping shipping) {
       shippingMapper.deleteAddress(shipping);


    }
}
