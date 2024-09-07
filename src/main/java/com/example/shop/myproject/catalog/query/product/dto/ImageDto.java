package com.example.shop.myproject.catalog.query.product.dto;


import com.example.shop.myproject.catalog.command.domain.product.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Embeddable
@Table(name = "product_image")
public class ImageDto {
    private String url;
    private String filename;

    protected ImageDto() {
    }

    public ImageDto(Image image) {
        this.url = image.getUrl();
        this.filename = image.getFilename();
    }
}
