package com.example.shop.myproject.order.commnad.application;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.domain.CouponRepository;
import com.example.shop.myproject.member.exception.NoMemberException;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.order.commnad.dto.OrderProduct;
import com.example.shop.myproject.order.commnad.dto.OrderRequest;
import com.example.shop.myproject.order.commnad.domain.Order;
import com.example.shop.myproject.order.commnad.domain.OrderLine;
import com.example.shop.myproject.order.commnad.domain.OrderRepository;
import com.example.shop.myproject.order.commnad.domain.OrderStatus;
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
    private final CalculateDiscountService calculateDiscountService;

    @Transactional
    public Long createOrder(OrderRequest request) {
        try {
            Member member = memberRepository.findById(request.getOrdererId())
                    .orElseThrow(() -> new NoMemberException(request.getOrdererId()));

            List<OrderLine> orderLines = new ArrayList<>();
            int totalPrice = 0;
            int paymentPrice = 0;

            for (OrderProduct op : request.getOrderProducts()) {
                Product product = productRepository.findById(op.getProductId())
                        .orElseThrow(() -> new NoOrderProductException(op.getProductId()));

                // 재고 수정
                product.decreaseStock(op.getQuantity());

                OrderLine orderLine = new OrderLine(product, product.getPrice(), op.getQuantity());
                orderLines.add(orderLine);
                totalPrice += product.getPrice();
            }

            int paymentAmount = calculateDiscountService.calculateDiscount(request.getCouponId(), totalPrice);

            Order order = new Order(member, orderLines, OrderStatus.ORDER);
            orderRepository.save(order);
            return order.getId();

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
