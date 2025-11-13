import java.sql.*;

public class EmployeeDatabase {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/companydb"; // Database name: companydb
        String username = "root"; // your MySQL username
        String password = "yourpassword"; // your MySQL password

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Step 1: Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish Connection
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Connected to the database successfully!\n");

            // Step 3: Create Statement
            stmt = conn.createStatement();

            // Step 4: Execute Query
            String query = "SELECT EmpID, Name, Salary FROM Employee";
            rs = stmt.executeQuery(query);

            // Step 5: Display Results in Tabular Format
            System.out.println("EmpID\tName\t\tSalary");
            System.out.println("--------------------------------");
            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.printf("%d\t%-10s\t%.2f%n", id, name, salary);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ SQL Error: " + e.getMessage());
        } finally {
            // Step 6: Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
