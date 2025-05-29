package com.fbr.ecommerce.controller.dto;

import com.fbr.ecommerce.entities.TagEntity;

public record TagResponseDto(Long tagId,
                             String name) {

    public static TagResponseDto fromEntity(TagEntity tagEntity) {
        return new TagResponseDto(
                tagEntity.getTagId(),
                tagEntity.getName()
        );
    }
}
