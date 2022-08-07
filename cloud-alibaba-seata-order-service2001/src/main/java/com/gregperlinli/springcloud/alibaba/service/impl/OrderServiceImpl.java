package com.gregperlinli.springcloud.alibaba.service.impl;

import com.gregperlinli.springcloud.alibaba.dao.OrderDao;
import com.gregperlinli.springcloud.alibaba.service.AccountService;
import com.gregperlinli.springcloud.alibaba.service.OrderService;
import com.gregperlinli.springcloud.alibaba.service.StorageService;
import com.gregperlinli.springcloud.alibaba.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author gregPerlinLi
 * @since 2022-05-25
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @Override
    public void create(Order order) {
        log.info("====> Begin to create order ... ");
        // 1. Create order
        orderDao.create(order);
        log.info("====> Order server begin to call stock, and make a decrease to stock ... ");
        // 2. Decrease stock
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("====> Order server begin to call stock, and make a decrease to stock, finished ... ");
        log.info("====> Order server begin to call account, and make a decrease to money ... ");
        // 3. Decrease money
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("====> Order server begin to call account, and make a decrease to money, finished ... ");
        log.info("====> Start to modify order status, set to 0 ... ");
        // 4. Modify order status, 1 mentioned finished
        orderDao.update(order.getId(), 0);
        log.info("====> Modify order status finished ... ");
        log.info("====> Order created successfully ...");
    }
}
