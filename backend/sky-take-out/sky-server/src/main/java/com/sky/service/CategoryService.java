package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService{
    void update(CategoryDTO categoryDTO);

    PageResult query(CategoryPageQueryDTO cpq);

    void startOrStop(Integer status, Long id);

    void insert(CategoryDTO categoryDTO);

    void deleteById(Long id);

  List<Category> queryByType(Integer type);

}
