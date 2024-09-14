package com.example.shop.myproject.order.commnad.infra;

import com.example.shop.myproject.common.LockException;
import com.example.shop.myproject.order.commnad.application.CreateOrderService;
import com.example.shop.myproject.order.commnad.application.PlaceOrderService;
import com.example.shop.myproject.order.commnad.dto.OrderProduct;
import com.example.shop.myproject.order.commnad.dto.OrderForm;
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
public class PlaceOrderServiceV3 implements PlaceOrderService {

    private final RedissonClient redissonClient;
    private final CreateOrderService createOrderService;

    /***
     * Redisson 분산 락 주문 처리
     * @param form
     */
    @Override
    public Long placeOrder(OrderForm form) {
        Map<Long, RLock> locks = new ConcurrentSkipListMap<>();

        try {
            for (OrderProduct op : form.getOrderProducts()) {
                RLock lock = redissonClient.getLock(String.valueOf(op.getProductId()));
                boolean isLockAcquired = lock.tryLock(5, 3, TimeUnit.SECONDS);

                if (!isLockAcquired) {
                    throw new LockException(lock.getName());
                }

                locks.put(op.getProductId(), lock);
            }
            // 모든 락을 획득한 경우 주문 실행
            return createOrderService.createOrder(form);

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
