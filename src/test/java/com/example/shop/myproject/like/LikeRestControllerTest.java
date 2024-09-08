package com.example.shop.myproject.like;

import com.example.shop.myproject.like.application.LikeService;
import com.example.shop.myproject.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = LikeRestController.class)
class LikeRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LikeRestController likeRestController;


    @Test
    void check() {
    }

    @Test
    void delete() {
    }

    @Test
    void save() {
    }

    @Test
    void testDelete() {
    }
}