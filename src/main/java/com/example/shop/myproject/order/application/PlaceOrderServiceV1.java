package com.example.shop.myproject.order.application;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import com.example.shop.myproject.member.command.application.exception.NoMemberException;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.order.application.dto.OrderProduct;
import com.example.shop.myproject.order.application.dto.OrderRequest;
import com.example.shop.myproject.order.domain.Order;
import com.example.shop.myproject.order.domain.OrderLine;
import com.example.shop.myproject.order.domain.OrderRepository;
import com.example.shop.myproject.order.domain.OrderStatus;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@AllArgsConstructor
@Service
public class PlaceOrderServiceV1 {

    private final Lock lock = new ReentrantLock();

    private EntityManager entityManager;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    /***
     * ReentrantLock 사용한 비관적락 주문 처리
     * @param request
     */
    @Transactional
    public Long placeOrder(OrderRequest request) {
        lock.lock();
        try {
            Member member = memberRepository.findById(request.getOrdererId())
                    .orElseThrow(() -> new NoMemberException(request.getOrdererId()));

            List<OrderLine> orderLines = new ArrayList<>();

            for (OrderProduct op : request.getOrderProducts()) {
                Product product = productRepository.findById(op.getProductId())
                        .orElseThrow(() -> new NoOrderProductException(op.getProductId()));

                // 재고 감소 로직 실행
                product.decreaseStock(op.getQuantity());
                orderLines.add(new OrderLine(product, product.getPrice(), op.getQuantity()));
            }

            Order order = new Order(member, orderLines, OrderStatus.ORDER);
            Order saved = orderRepository.save(order);
            entityManager.flush();
            return saved.getId();
        } finally {
            lock.unlock();
        }
    }
}
