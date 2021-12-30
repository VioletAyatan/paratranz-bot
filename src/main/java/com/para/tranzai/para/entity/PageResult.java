package com.para.tranzai.para.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PageResult<T> {
    private Integer page = 0;
    private Integer pageCount = 0;
    private Integer pageSize = 0;
    private List<T> results = new ArrayList<>();
    private Integer rowCount = 0;
}
