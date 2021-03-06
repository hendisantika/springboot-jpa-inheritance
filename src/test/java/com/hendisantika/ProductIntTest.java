package com.hendisantika;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.controller.ProductController;
import com.hendisantika.entity.Product;
import com.hendisantika.repository.ProductRepository;
import com.hendisantika.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    @Transactional
    public void storeProductTest() throws Exception {
        Product product = createProduct();
        MvcResult mvcResult = mockMvc
                .perform(post(API)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsBytes(product))
                )
                .andExpect(status().isCreated())
                .andReturn();

        Product storedProduct = new ObjectMapper().readValue(
                mvcResult.getResponse().getContentAsString(), Product.class
        );
        assertThat(storedProduct.getValidFrom()).isEqualTo(product.getValidFrom());
        assertThat(storedProduct.getValidTo()).isEqualTo(product.getValidTo());
        assertThat(storedProduct.getSourceIdentifier()).isEqualTo(product.getSourceIdentifier());
        assertThat(storedProduct.getActive()).isEqualTo(product.getActive());
    }

    @Test
    @Transactional
    public void getProductByIdTest() throws Exception {
        Product product = createProduct();
        productRepository.save(product);

        mockMvc
                .perform(get(API.concat("/").concat(product.getId().toString()))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.validFrom").value(product.getValidFrom()))
                .andExpect(jsonPath("$.validTo").isEmpty())
                .andExpect(jsonPath("$.sourceIdentifier").value(product.getSourceIdentifier()))
                .andExpect(jsonPath("$.active").value(product.getActive()));
    }

    @Test
    @Transactional
    public void getAllProductsTest() throws Exception {
        Product product = createProduct();
        productRepository.save(product);

        mockMvc
                .perform(get(API)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].validFrom").value(hasItem(product.getValidFrom().getTime())))
                .andExpect(jsonPath("$.[*].validTo").exists())
                .andExpect(jsonPath("$.[*].sourceIdentifier").value(hasItem(product.getSourceIdentifier())))
                .andExpect(jsonPath("$.[*].active").value(hasItem(product.getActive())));
    }

    private Product createProduct() {
        Product product = new Product();
        product.setCatalogName("dummy-catalog-name");
        product.setDescription("dummy-description");
        product.setPrice(BigDecimal.TEN);
        product.setValidFrom(new Date());
        product.setSourceIdentifier("dummy-source-identifier");

        return product;
    }
}
