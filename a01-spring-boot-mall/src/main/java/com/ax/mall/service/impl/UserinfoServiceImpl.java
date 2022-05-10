package com.ax.mall.service.impl;

import com.ax.mall.entity.Userinfo;
import com.ax.mall.mapper.UserinfoMapper;
import com.ax.mall.service.IRedisService;
import com.ax.mall.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author axing
 */
@Service
public class UserinfoServiceImpl implements IUserinfoService {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Autowired
    private IRedisService redisService;

    @Override
    public Userinfo getUserinfoWithKey(long id) {
        return userinfoMapper.selectByPrimaryKey(id);

    }

    @Override
    public List<Userinfo> getAllUserinfo() {
        System.out.println("userinfoMapper = " + userinfoMapper);
        return userinfoMapper.getAll();
    }

    @Override
    public Userinfo selectUserWithRelo(long id) {
        return userinfoMapper.selectUserWithRelo(id);
    }

    @Override
    public List<Userinfo> getAllUserinfoWithRedis() {

        return redisService.gettWithThread(RedisService.REDIS_VALUE_USERINFO, new Callable() {
            @Override
            public Object call() throws Exception {
                return userinfoMapper.getAll();
            }
        });


//        List<Userinfo> list = (List<Userinfo>)redisTemplate.opsForValue().get("allUserinfo");
//
//        // 双重检测锁
//        if (null== list){
//            synchronized (this){
//                // 在redis中获取
//                list = (List<Userinfo>) redisTemplate.opsForValue().get("allUserinfo");
//                if (null== list){
//                    System.out.println("查询数据库------------");
//                    list = userinfoMapper.getAll();
//                    redisTemplate.opsForValue().set("allUserinfo",list);
//                }else {
//                    System.out.println("查询redis缓存------------");
//                }
//            }
//        }else{
//            System.out.println("查询redis缓存------------");
//        }
//        return list;
    }

    @Override
    public int insert(Userinfo record) {
        return userinfoMapper.insert(record);
    }
}

