package com.hendisantika.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jpa-inheritance
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/03/22
 * Time: 21.19
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "customer")
@DynamicInsert
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends CommonHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotNull
    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CustomerType customerType = CustomerType.INDIVIDUAL;

}
