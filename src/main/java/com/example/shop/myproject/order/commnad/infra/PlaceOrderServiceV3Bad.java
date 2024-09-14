package com.example.shop.myproject.order.commnad.infra;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import com.example.shop.myproject.common.LockException;
import com.example.shop.myproject.delivery.domain.Delivery;
import com.example.shop.myproject.delivery.domain.DeliveryStatus;
import com.example.shop.myproject.member.exception.NoMemberException;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.order.commnad.application.NoOrderProductException;
import com.example.shop.myproject.order.commnad.dto.OrderProduct;
import com.example.shop.myproject.order.commnad.dto.OrderForm;
import com.example.shop.myproject.order.commnad.domain.Order;
import com.example.shop.myproject.order.commnad.domain.OrderLine;
import com.example.shop.myproject.order.commnad.domain.OrderRepository;
import com.example.shop.myproject.order.commnad.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@Service
public class PlaceOrderServiceV3Bad {

    private final RedissonClient redissonClient;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    /***
     * RedissonClient 분산 락 주문 처리
     * @param form
     */
    @Transactional
    public Long placeOrder(OrderForm form) {
        Map<Long, RLock> locks = new ConcurrentSkipListMap<>();

        try {
            Member member = memberRepository.findById(form.getOrdererId())
                    .orElseThrow(() -> new NoMemberException(form.getOrdererId()));

            List<OrderLine> orderLines = new ArrayList<>();

            for (OrderProduct op : form.getOrderProducts()) {
                RLock lock = redissonClient.getLock("product:" + op.getProductId());
                boolean isLockAcquired = lock.tryLock(5, 3, TimeUnit.SECONDS);

                if (!isLockAcquired) {
                    throw new LockException(lock.getName());
                }

                Product product = productRepository.findById(op.getProductId())
                        .orElseThrow(() -> new NoOrderProductException(op.getProductId()));
                product.decreaseStock(op.getQuantity());

                orderLines.add(new OrderLine(product, product.getPrice(), op.getQuantity()));
                locks.put(op.getProductId(), lock);
            }

            Delivery delivery = new Delivery(form.getAddress(), DeliveryStatus.READY);
            Order order = new Order(member, orderLines, delivery, OrderStatus.PAYMENT_WAITING);
            orderRepository.save(order);
            return order.getId();

        } catch (InterruptedException e) {
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
