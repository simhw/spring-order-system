package com.example.shop.myproject.order.application;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.order.application.dto.OrderProduct;
import com.example.shop.myproject.order.application.dto.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PlaceOrderServiceV3Test {

    @Autowired
    private PlaceOrderServiceV3 placeOrderServiceV3;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MemberRepository memberRepository;

    private Long productId1 = 1L;
    private Long productId2 = 2L;

    private int stock1 = 100;
    private int stock2 = 200;
    private Long ordererId = 1L;

    @BeforeEach
    void init() {
        Product product1 = new Product(productId1, "product", 10000, stock1);
        Product product2 = new Product(productId2, "product", 15000, stock2);
        productRepository.saveAll(List.of(product1, product2));

        Member member = new Member(ordererId, "member", "");
        memberRepository.save(member);
    }

    @Test
    void 동시에_100개_주문() throws InterruptedException {
        // given
        int threadCount = 100;
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        OrderProduct orderProduct1 = new OrderProduct(productId1, 1);
        OrderProduct orderProduct2 = new OrderProduct(productId2, 1);
        OrderRequest orderRequest = new OrderRequest(ordererId, List.of(orderProduct1, orderProduct2));

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    placeOrderServiceV3.placeOrder(orderRequest);
                    successCount.incrementAndGet();

                } catch (Exception e) {
                    System.out.println(Arrays.toString(e.getStackTrace()));
                    failCount.incrementAndGet();

                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        Optional<Product> op1 = productRepository.findById(productId1);
        Optional<Product> op2 = productRepository.findById(productId2);

        System.out.println("상품1 재고: " + op1.get().getStock());
        System.out.println("상품2 재고: " + op2.get().getStock());

        // then
        assertThat(successCount.get()).isEqualTo(threadCount);
    }
}