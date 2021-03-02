package com.step.model.employee.manager.db;

import com.step.model.employee.Employee;
import com.step.model.employee.Job;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public Connection initConnection() throws SQLException {
        String url = "jdbc:postgresql://127.0.0.1:5432/employee_manager";
        String username = "postgres";
        String password = "admin";

        return DriverManager.getConnection(url, username, password);
    }

    public List<Employee> getAllEmps() {
        String sql = "SELECT id, name, surname, idnp, birthdate," +
                " engagedon, salary, job FROM emp.employees";
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = this.initConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String idnp = resultSet.getString("idnp");
                LocalDate birthDate = LocalDate.parse(resultSet.getString("birthDate"));
                LocalDate engagedOn = LocalDate.parse(resultSet.getString("engagedOn"));
                Double salary = Double.valueOf(resultSet.getString("salary"));
                Job job = Job.valueOf(resultSet.getString("job"));

                employees.add(new Employee(id, name, surname, idnp, birthDate, engagedOn, salary, job));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return employees;
    }

    public void insert(Employee employee) {
        String preparedSql = "INSERT INTO " +
                "emp.employees (name, surname, idnp, birthdate, engagedon, salary, job) " +
                "values (?,?,?,?,?,?,?)";

        try (Connection connection = this.initConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(preparedSql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getIdnp());
            preparedStatement.setString(4, String.valueOf(employee.getBirthDate()));
            preparedStatement.setString(5, String.valueOf(employee.getEngagedOn()));
            preparedStatement.setDouble(6, employee.getSalary());
            preparedStatement.setString(7, String.valueOf(employee.getJob()));

            int rows = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean delete(int id) {
        String preparedSql = "DELETE FROM emp.employees " +
                "where id=?";

        try (Connection connection = this.initConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(preparedSql)) {
            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}
