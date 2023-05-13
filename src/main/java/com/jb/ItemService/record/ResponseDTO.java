package com.jb.ItemService.record;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ResponseDTO<T>
{
    private T item;
    
    public ResponseDTO(T item) {
        this.item = item;
    }
}
