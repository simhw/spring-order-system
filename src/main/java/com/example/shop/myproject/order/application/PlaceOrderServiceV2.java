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
     * @param request
     */
    @Transactional
    public Long placeOrder(OrderRequest request) {
        Member member = memberRepository.findById(request.getOrdererId())
                .orElseThrow(() -> new NoMemberException(request.getOrdererId()));

        List<OrderLine> orderLines = new ArrayList<>();

        for (OrderProduct op : request.getOrderProducts()) {
            Product product = productRepository.findByIdForUpdate(op.getProductId())
                    .orElseThrow(() -> new NoOrderProductException(op.getProductId()));

            log.info("product.id: {}", product.getId());
            log.info("product.version: {}", product.getVersion());

            // 재고 감소 로직 실행
            product.decreaseStock(op.getQuantity());
            orderLines.add(new OrderLine(product, product.getPrice(), op.getQuantity()));
        }

        Order order = new Order(member, orderLines, OrderStatus.ORDER);
        Order saved = orderRepository.save(order);
        return saved.getId();
    }
}
