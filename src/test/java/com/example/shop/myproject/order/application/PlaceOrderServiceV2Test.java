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

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PlaceOrderServiceV2Test {

    @Autowired
    private PlaceOrderServiceV2 placeOrderServiceV2;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MemberRepository memberRepository;

    private Long productId = 1L;
    private Long ordererId = 1L;

    private int stock = 10;

    @BeforeEach
    void init() {
        Product product = new Product(productId, "product", 10000, stock);
        productRepository.save(product);

        Member member = new Member(ordererId, "user1");
        memberRepository.save(member);
    }

    @Test
    void 동시에_10개_주문() throws InterruptedException {
        // given
        int threadCount = 10;
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        OrderRequest orderRequest = new OrderRequest(
                ordererId,
                List.of(new OrderProduct(productId, 1))
        );

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    placeOrderServiceV2.placeOrder(orderRequest);
                    successCount.incrementAndGet();

                } catch (Exception e) {
                    failCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        System.out.println("success count: " + successCount.get());
        System.out.println("fail count: " + failCount.get());

        // then
        assertThat(successCount.get()).isEqualTo(stock);
    }
}