package org.example.turistguide2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.example.turistguide2.model.Cities;
import org.example.turistguide2.model.TouristAttraction;
import org.example.turistguide2.repository.TouristRepository;
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
    private TouristRepository repo;

    @Test
    void getall() {
        List<TouristAttraction> all = repo.getAll();

        assertThat(all).isNotNull();
    }

// Unit test
    @Test
    void getAttraction () {
        TouristAttraction att = new TouristAttraction(10, "Gågaden", "Brug alle dine penge", Cities.Copenhagen);
        assertEquals("Gågaden", att.getName());
    }
}
