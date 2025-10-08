package org.example.turistguide2;

import org.example.turistguide2.model.TouristAttraction;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
public class TouristRepositoryTest {

    @Autowired
    private org.example.turistguide2.repository.TouristRepository repo;

    @Test
    void readAll() {
        List<TouristAttraction> all = repo.getAll();

        assertThat(all).isNotNull();
        assertThat(all.get(0).getName()).isEqualTo("Tivoli");
        assertThat(all.get(1).getName()).isEqualTo("Bibliotek");
    }
}
