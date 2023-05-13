package com.jb.ItemService.record;


public record ItemListRequestDTO(String title, String description, Long userId, Long parentTaskId) {
}
