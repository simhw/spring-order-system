package com.example.shop.myproject.catalog.command.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Option {
    @Column(name = "option_value")
    private String value;

    @Column(name = "option_title")
    private String title;

    protected Option() {
    }
}
