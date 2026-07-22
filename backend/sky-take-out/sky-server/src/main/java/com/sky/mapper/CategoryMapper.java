package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annoation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @AutoFill(value =OperationType.UPDATE)
    void update(Category category);

    Page<Category> query(CategoryPageQueryDTO cpq);
@Insert("insert into sky_take_out.category(type, name, sort, status, create_time, update_time, create_user, update_user) " +
        "values(#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
@AutoFill(value = OperationType.INSERT)
    void insert(Category category);
@Delete("delete from sky_take_out.category where id=#{id}")
    void deleteById(Long id);

   List<Category> queryByType(Integer type);
}
