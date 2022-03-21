package com.hendisantika.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jpa-inheritance
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/03/22
 * Time: 21.22
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "product")
@DynamicInsert
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends CommonHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "catalog_name", nullable = false)
    private String catalogName;

    @NotNull
    @Column(nullable = false, precision = 28, scale = 2)
    private BigDecimal price;

    @Column
    private String description;
}
