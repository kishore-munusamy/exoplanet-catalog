package org.gendigital.exoplanetcatalog;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExoplanetCatalogApplicationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void contextLoads() {
    assertThat(restTemplate).isNotNull();
  }
}

