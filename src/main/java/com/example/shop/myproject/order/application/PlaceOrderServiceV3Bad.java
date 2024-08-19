package com.example.shop.myproject.order.application;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import com.example.shop.myproject.common.LockException;
import com.example.shop.myproject.member.command.application.exception.NoMemberException;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.order.application.dto.OrderProduct;
import com.example.shop.myproject.order.application.dto.OrderRequest;
import com.example.shop.myproject.order.domain.Order;
import com.example.shop.myproject.order.domain.OrderLine;
import com.example.shop.myproject.order.domain.OrderRepository;
import com.example.shop.myproject.order.domain.OrderStatus;
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
     * @param request
     */
    @Transactional
    public Long placeOrder(OrderRequest request) {
        Map<Long, RLock> locks = new ConcurrentSkipListMap<>();

        try {
            Member member = memberRepository.findById(request.getOrdererId())
                    .orElseThrow(() -> new NoMemberException(request.getOrdererId()));

            List<OrderLine> orderLines = new ArrayList<>();

            for (OrderProduct op : request.getOrderProducts()) {
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

            Order order = new Order(member, orderLines, OrderStatus.ORDER);
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
