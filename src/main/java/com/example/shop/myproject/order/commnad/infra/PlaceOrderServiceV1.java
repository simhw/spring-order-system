package com.example.shop.myproject.order.commnad.infra;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
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
     * @param form
     */
    @Transactional
    public Long placeOrder(OrderForm form) {
        lock.lock();
        try {
            Member member = memberRepository.findById(form.getOrdererId())
                    .orElseThrow(() -> new NoMemberException(form.getOrdererId()));

            List<OrderLine> orderLines = new ArrayList<>();

            for (OrderProduct op : form.getOrderProducts()) {
                Product product = productRepository.findById(op.getProductId())
                        .orElseThrow(() -> new NoOrderProductException(op.getProductId()));

                // 재고 감소 로직 실행
                product.decreaseStock(op.getQuantity());
                orderLines.add(new OrderLine(product, product.getPrice(), op.getQuantity()));
            }

            Delivery delivery = new Delivery(form.getAddress(), DeliveryStatus.READY);
            Order order = new Order(member, orderLines, delivery, OrderStatus.PAYMENT_WAITING);
            Order saved = orderRepository.save(order);
            entityManager.flush();
            return saved.getId();
        } finally {
            lock.unlock();
        }
    }
}
