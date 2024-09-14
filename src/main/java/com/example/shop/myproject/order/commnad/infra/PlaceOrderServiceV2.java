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
import com.example.shop.myproject.order.commnad.domain.OrderStatus;
import com.example.shop.myproject.order.commnad.domain.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PlaceOrderServiceV2 {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    /***
     * JPA Version 낙관적 락 주문 처리
     * @param form
     */
    @Transactional
    public Long placeOrder(OrderForm form) {
        Member member = memberRepository.findById(form.getOrdererId())
                .orElseThrow(() -> new NoMemberException(form.getOrdererId()));

        List<OrderLine> orderLines = new ArrayList<>();

        for (OrderProduct op : form.getOrderProducts()) {
            Product product = productRepository.findByIdForUpdate(op.getProductId())
                    .orElseThrow(() -> new NoOrderProductException(op.getProductId()));

            // 재고 감소 로직 실행
            product.decreaseStock(op.getQuantity());
            orderLines.add(new OrderLine(product, product.getPrice(), op.getQuantity()));
        }

        Delivery delivery = new Delivery(form.getAddress(), DeliveryStatus.READY);
        Order order = new Order(member, orderLines, delivery, OrderStatus.PAYMENT_WAITING);
        Order saved = orderRepository.save(order);
        return saved.getId();
    }
}
