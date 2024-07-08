package org.beastrein;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBManager {
    public static String url = "jdbc:sqlite:homes.db";
    public static Connection connection;


    static {
        try {
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean deleteHome(String id) throws Exception {
        String combinedId = id;
        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Homes WHERE id = ?")) {
            deleteStatement.setString(1, combinedId);
            int deletedRows = deleteStatement.executeUpdate();
            return deletedRows > 0; // Return true if at least one row was deleted
        }
    }


    public static void initDB() throws Exception {
        Statement statement = connection.createStatement();
        String sql  = "CREATE TABLE IF NOT EXISTS Homes ("
                + "	id text PRIMARY KEY,"
                + "	x REAL,"
                + "	y REAL,"
                + " z REAL,"
                + " rx REAL,"
                + " ry REAL,"
                + " world text"
                + ");";
        statement.execute(sql);
    }
    public static void addHome(String id, Location location) throws Exception {
        deleteHome(id);
        String sql = "INSERT INTO Homes (id, x, y, z, rx, ry, world) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        statement.setDouble(2, location.getX());
        statement.setDouble(3, location.getY());
        statement.setDouble(4, location.getZ());
        statement.setFloat(5, location.getPitch());
        statement.setFloat(6, location.getYaw());
        statement.setString(7, location.getWorld().getName());
        statement.executeUpdate();
    }
    public static Location getHome(String uuid, String name) throws Exception {
        String combinedId = uuid + "|" + name;
        String sql = "SELECT * FROM Homes WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, combinedId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return new Location(Bukkit.getWorld(rs.getString("world")), rs.getDouble("x"), rs.getDouble("y"), rs.getDouble("z"), rs.getFloat("ry"), rs.getFloat("rx"));
        } else {
            // Handle case where no home is found (optional)
            return null;
        }
    }

    public static List<String> getHomesByUUID(String uuid) throws Exception {
        List<String> homes = new ArrayList<>();
        String combinedId = uuid + "%"; // Use LIKE with wildcard for partial match

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homes WHERE id LIKE ?")) {
            statement.setString(1, combinedId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                homes.add(rs.getString("id").split("\\|")[1]); // get home name
            }
        }
        return homes;
    }
}
