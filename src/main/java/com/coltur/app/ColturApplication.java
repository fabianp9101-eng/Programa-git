package com.coltur.app;

import com.coltur.config.ConnectionManager;
import com.coltur.config.DatabaseInitializer;
import com.coltur.dao.TourPackageDao;
import com.coltur.model.TourPackage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ColturApplication {
    private static final String JDBC_URL = "jdbc:sqlite:coltur.db";

    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager(JDBC_URL);
        DatabaseInitializer initializer = new DatabaseInitializer(connectionManager);
        TourPackageDao tourPackageDao = new TourPackageDao(connectionManager);

        try {
            initializer.initializeSchema();

            TourPackage inserted = tourPackageDao.insert(
                    new TourPackage("Aventura Andes", "Recorrido por senderos y pueblos mágicos.", 350000.0, 3)
            );
            System.out.println("Insertado: " + inserted);

            List<TourPackage> packages = tourPackageDao.findAll();
            System.out.println("Listado inicial:");
            packages.forEach(System.out::println);

            inserted.setPriceCop(370000.0);
            tourPackageDao.update(inserted);
            System.out.println("Actualizado: " + tourPackageDao.findById(inserted.getId()).orElse(null));

            tourPackageDao.delete(inserted.getId());
            System.out.println("Después de eliminar: " + tourPackageDao.findAll());
        } catch (SQLException | IOException e) {
            System.err.println("Error ejecutando operaciones JDBC: " + e.getMessage());
        }
    }
}
