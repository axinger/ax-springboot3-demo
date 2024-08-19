package com.github.axinger;

import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class FlowableUserTest {

    @Autowired
    IdentityService identityService; // 用户操作 ，操作表 ACT_ID_USER


    @Test
    void contextLoads() {
//        UserEntityImpl user = new UserEntityImpl();
        User user = identityService.newUser("001");
//        user.setId("001");
        user.setDisplayName("张三");
        user.setPassword("123456");
//        user.setFirstName("张");
//        user.setLastName("三");
//        user.setEmail("张三@qq.com");
//        user.setRevision(0);
        identityService.saveUser(user);
    }


    @Test
    void test_01_02() {
//        UserEntityImpl user = new UserEntityImpl();
        User user = identityService.newUser("101");
        user.setDisplayName("tom");
        user.setPassword("123456");
        user.setFirstName("tom");
//        user.setLastName("三");
        user.setEmail("tom@qq.com");
//        user.setRevision(0);
        identityService.saveUser(user);
    }

    @Test
    void test03() {
        // 更新密码登字段，使用乐观锁，所有需要先查询
        User u = identityService.createUserQuery().userId("zhangsan").singleResult();
        u.setDisplayName("itboy");
        identityService.updateUserPassword(u);
    }

    @Test
    void test05() {
        identityService.deleteUser("zhangsan");
    }


    @Test
    void test09() {
        // 创建组
        GroupEntityImpl g = new GroupEntityImpl();
        g.setName("组长");
        g.setId("leader");
        g.setRevision(0);
        identityService.saveGroup(g);

        List<Group> groupList = identityService.createGroupQuery().list();
        List<User> userList = identityService.createUserQuery().list();
    }

    @Test
    void test10() {
        //这就是设置 zhangsan 和 lisi 是组长（注意用户和组的关联关系表中有外键，所以需要确保两个参数都是真实存在的）。
        //
        //添加了关联关系之后，我们再去查看 ACT_ID_MEMBERSHIP 表，如下：
        identityService.createMembership("zhangsan", "leader");
    }


    @Test
    void test11() {
        // 删除关联关系
        identityService.deleteMembership("zhangsan", "leader");

    }


}