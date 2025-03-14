package com.github.axinger.domain;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.activerecord.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Table(value = "tb_account")
@Accessors(chain = true)
@Data
@Entity(name = "tb_account")
//@AllArgsConstructor
//@NoArgsConstructor
//@Data(staticConstructor = "create") // 会私有无参构造方法
public class Account extends Model<Account> implements Serializable {

    @Id(keyType = KeyType.Auto)
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @jakarta.persistence.Column(name = "id")
    @Comment("id")
    private Integer id;

    @Column(value = "user_name")
    @jakarta.persistence.Column(name = "user_name")
    @Comment("用户名")
    private String userName;

    @Column(value = "age")
    @jakarta.persistence.Column(name = "age")
    @Comment("年龄")
    private Integer age;

    @Column(value = "birthday")
    @jakarta.persistence.Column(name = "birthday")
    @Comment("生日")
    private LocalDateTime birthday;

    //最大年龄
    @Column(ignore = true)
    @Transient
    private int maxAge;

    @Column(ignore = true)
    @Transient
    private int mimAge;

    //平均年龄
    @Column(ignore = true)
    @Transient
    private int avgAge;

    // 分组后多个id逗号分割
    @Column(ignore = true)
    @Transient
    private String groupAfterId;

    @Column(ignore = true)
    @Transient
    private String groupAfterName;


    public static Account create() {
        return new Account();
    }
}
