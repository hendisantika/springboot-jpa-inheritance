package com.hendisantika;

import com.hendisantika.controller.ProductController;
import com.hendisantika.repository.ProductRepository;
import com.hendisantika.service.ProductService;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jpa-inheritance
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 22/03/22
 * Time: 19.30
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(classes = SpringbootJpaInheritanceApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "default")
public class ProductIntTest {

    private static final String API = "/api/product";

    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProductController productController = new ProductController(productService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(productController)
                .build();
    }
}
