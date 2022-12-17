package com.axing.demo.dao;

import com.axing.demo.model.Users;
import org.springframework.data.repository.CrudRepository;

/**
 * CrudRepository接口继承了Repository接口
 *
 * CrudRepository提供了基本的增删改查，不再需要我们自定义。
 */
public interface UsersRepositoryCrudRepository extends CrudRepository<Users,Integer> {
}