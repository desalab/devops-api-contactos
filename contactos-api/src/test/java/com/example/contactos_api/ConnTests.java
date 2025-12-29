package com.example.contactos_api;
import java.sql.Connection;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConnTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void testDatabaseConnection() throws Exception {
        assertNotNull(dataSource, "DataSource no debe ser nulo");

        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "La conexión no debe ser nula");
            assertTrue(connection.isValid(2), "La conexión debe ser válida");
            System.out.println("✓ Conexión a la base de datos exitosa");
        }
    }
}
