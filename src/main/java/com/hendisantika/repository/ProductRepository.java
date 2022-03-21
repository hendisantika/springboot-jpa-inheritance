package com.hendisantika.repository;

import com.hendisantika.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jpa-inheritance
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/03/22
 * Time: 21.24
 * To change this template use File | Settings | File Templates.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
