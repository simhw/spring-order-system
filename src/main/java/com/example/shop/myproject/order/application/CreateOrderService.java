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
public class CreateOrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createOrder(OrderRequest request) {
        try {
            Member member = memberRepository.findById(request.getOrdererId())
                    .orElseThrow(() -> new NoMemberException(request.getOrdererId()));

            List<OrderLine> orderLines = new ArrayList<>();

            for (OrderProduct op : request.getOrderProducts()) {
                Product product = productRepository.findById(op.getProductId())
                        .orElseThrow(() -> new NoOrderProductException(op.getProductId()));
                product.decreaseStock(op.getQuantity());
                orderLines.add(new OrderLine(product, product.getPrice(), op.getQuantity()));
            }

            Order order = new Order(member, orderLines, OrderStatus.ORDER);
            orderRepository.save(order);
            return order.getId();

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
