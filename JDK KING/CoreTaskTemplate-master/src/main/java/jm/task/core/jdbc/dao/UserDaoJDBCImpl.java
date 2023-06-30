package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.lang.model.element.Name;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

private final Connection conn = Util.getConnection();

    public   UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE if not exists users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), last_name VARCHAR(100), age INT)";
        try ( Statement statement = conn.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void dropUsersTable() {
        try (Statement statement = conn.createStatement()){
            statement.executeUpdate("drop table if exists users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prst = conn.prepareStatement("insert into users (name, last_name, age ) values (?, ? ,?)")) {
            prst.setString(1,name);
            prst.setString(2, lastName);
            prst.setByte(3, age);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement prst = conn.prepareStatement("delete from users where id = ?")) {
            prst.setLong(1, id);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try(ResultSet resultSet = conn.createStatement().executeQuery("select * from users")) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Statement statement = conn.createStatement()) {
            statement.executeUpdate("truncate table users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;

    }
}
