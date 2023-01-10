package com.axing.demo.mapper;

import com.axing.demo.domain.Department;
import com.axing.demo.domain.Employee;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xing
 * @description 针对表【employee】的数据库操作Mapper
 * @createDate 2022-12-17 20:22:09
 * @Entity com.axing.demo.domain.Employee
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    List<Employee> listLeftSon(@Param(Constants.WRAPPER) Wrapper wrapper);
}



