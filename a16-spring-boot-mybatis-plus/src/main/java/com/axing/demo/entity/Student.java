package com.axing.demo.entity;

import com.axing.common.mybatis.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @TableName t_student
 */
@TableName(value = "t_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Student extends BaseEntity {

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer age;

    /**
     *
     */
    private Integer sex;

    /**
     *
     */
    private String address;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}