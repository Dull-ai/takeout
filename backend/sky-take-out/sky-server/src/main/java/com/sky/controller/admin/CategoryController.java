package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JwtProperties jwtProperties;
@PutMapping
    public Result update(@RequestBody CategoryDTO categoryDTO){

    categoryService.update(categoryDTO);

    return Result.success();
}
@GetMapping("/page")
    public Result<PageResult> query(CategoryPageQueryDTO cpq){
    log.info("分类分页查询:{}",cpq);
    PageResult pageResult =categoryService.query(cpq);
    return Result.success(pageResult);
}
@PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id){
    log.info("启用或禁用分类:{}{}",status,id);
        categoryService.startOrStop(status,id);
    return Result.success();
}
@PostMapping
    public Result insert(@RequestBody CategoryDTO categoryDTO){
    categoryService.insert(categoryDTO);
    return Result.success();
}
@DeleteMapping
    public Result deleteById(Long id){


    categoryService.deleteById(id);

    return Result.success();
}
@GetMapping("/list")
    public Result<List<Category>> queryByType(Integer type){
    log.info("根据类型查找:{}",type);
   List<Category> category=categoryService.queryByType(type);
    return Result.success(category);
}















}
