package com.handson.postgres;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.handson.postgres.Controller.ProductsController;
import com.handson.postgres.utils.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.handson.postgres.Product.ProductRepository.SortField.*;
import static com.handson.postgres.json.SortDirection.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = PostgresApplication.class)
@ActiveProfiles({"test"})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
class ProductControllerTest {
    @Autowired
    private ObjectMapper om;


    @Autowired
    private ProductsController pc;

    @Test
    void get10Patients() throws Exception {
        c.givenProducts(10, pc);
        assertThat(pc.search(c.getUser(),  null, null, 1, 50, productId, asc)
                .getBody().getData().size(), is(10));
    }

    @Test
    void get5PatientsWithCategory1() throws Exception {
        c.givenProducts(10, pc);
        assertThat(pc.search(c.getUser(), null, "category1", 1, 50, productId, asc)
                .getBody().getData().size(), is(5));
    }


    @Test
    void get10PatientsAndLimit() throws Exception {
        c.givenProducts(10, pc);
        assertThat(pc.search(c.getUser(), null, null, 1, 5, productId, asc)
                .getBody().getData().size(), is(5));
    }

    TestContext c;

    @BeforeEach
    void initContext() {
        c = new TestContext(om);
    }
}