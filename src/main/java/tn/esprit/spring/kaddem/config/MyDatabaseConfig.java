package tn.esprit.spring.kaddem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

public class MyDatabaseConfig {
    @Value("${spring.datasource.password}")
    private String root;

}
