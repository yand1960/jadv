package my.dbs;

import java.sql.*;

public class DemoJDBC {

    public static void main(String[] args) throws SQLException {
        try(Connection cn =
                DriverManager.getConnection("jdbc:sqlserver://yand.dyndns.org;databaseName=AdventureWorks",
                                            "northwind", "northwind")
        ) {
            String sql = "SELECT ProductID, Name, ProductNumber, ListPrice " +
                    "FROM Production.Product";

            Statement stat = cn.createStatement();
            try(ResultSet rs = stat.executeQuery(sql)) {
                while (rs.next()) {
                    System.out.println(rs.getString("Name") + " "
                            + rs.getDouble("ListPrice"));
                }
            }
        }
    }
}
