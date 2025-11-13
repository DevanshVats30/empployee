import java.sql.*;

public class Employee {
    public static void main(String[] args) {
        // ✅ Correct JDBC URL
        String url = "jdbc:mysql://localhost:3306/company";  // ❌ remove '.pb'
        String user = "root";
        String password = "root";
        String query = "SELECT employeeid, name, salary FROM employee";

        try {
            // ✅ Load the MySQL JDBC driver (optional in newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // ✅ Establish connection
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Connected to the database successfully!\n");
            System.out.printf("%-15s %-25s %-10s%n", "EmployeeID", "Name", "Salary");
            System.out.println("-----------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("employeeid");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                System.out.printf("%-15d %-25s %-10.2f%n", id, name, salary);
            }

            // ✅ Close resources
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
