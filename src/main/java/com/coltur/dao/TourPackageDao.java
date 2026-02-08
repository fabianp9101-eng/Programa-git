package com.coltur.dao;

import com.coltur.config.ConnectionManager;
import com.coltur.model.TourPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourPackageDao {
    private final ConnectionManager connectionManager;

    public TourPackageDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public TourPackage insert(TourPackage tourPackage) throws SQLException {
        String sql = "INSERT INTO tour_package (name, description, price_cop, duration_days) VALUES (?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, tourPackage.getName());
            statement.setString(2, tourPackage.getDescription());
            statement.setDouble(3, tourPackage.getPriceCop());
            statement.setInt(4, tourPackage.getDurationDays());
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    tourPackage.setId(keys.getInt(1));
                }
            }
        }
        return tourPackage;
    }

    public Optional<TourPackage> findById(int id) throws SQLException {
        String sql = "SELECT id, name, description, price_cop, duration_days FROM tour_package WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    public List<TourPackage> findAll() throws SQLException {
        String sql = "SELECT id, name, description, price_cop, duration_days FROM tour_package";
        List<TourPackage> results = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                results.add(mapRow(resultSet));
            }
        }
        return results;
    }

    public boolean update(TourPackage tourPackage) throws SQLException {
        String sql = "UPDATE tour_package SET name = ?, description = ?, price_cop = ?, duration_days = ? WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tourPackage.getName());
            statement.setString(2, tourPackage.getDescription());
            statement.setDouble(3, tourPackage.getPriceCop());
            statement.setInt(4, tourPackage.getDurationDays());
            statement.setInt(5, tourPackage.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM tour_package WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    private TourPackage mapRow(ResultSet resultSet) throws SQLException {
        return new TourPackage(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDouble("price_cop"),
                resultSet.getInt("duration_days")
        );
    }
}
