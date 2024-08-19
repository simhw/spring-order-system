package com.example.shop.myproject.order.application;

import com.example.shop.myproject.common.LockException;
import com.example.shop.myproject.order.application.dto.OrderProduct;
import com.example.shop.myproject.order.application.dto.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@Service
public class PlaceOrderServiceV3 {

    private final RedissonClient redissonClient;
    private final CreateOrderService createOrderService;

    /***
     * Redisson 분산 락 주문 처리
     * @param request
     */
    public Long placeOrder(OrderRequest request) {
        Map<Long, RLock> locks = new ConcurrentSkipListMap<>();

        try {
            for (OrderProduct op : request.getOrderProducts()) {
                RLock lock = redissonClient.getLock("product:" + op.getProductId());
                boolean isLockAcquired = lock.tryLock(5, 3, TimeUnit.SECONDS);

                if (!isLockAcquired) {
                    throw new LockException(lock.getName());
                }

                locks.put(op.getProductId(), lock);
            }

            return createOrderService.createOrder(request);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            for (RLock lock : locks.values()) {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }
}
