package ge.edu.freeuni.DAO;

import ge.edu.freeuni.Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    private Connection con;

    public UsersDAO(String url, String user_name, String password) {
        try {
            this.con = DriverManager.getConnection(url, user_name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(User user) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("Insert into users (username, password, mail, gender, avatar) " +
                    "values (?, ?, ?, ?, ?)");
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getMail());
            st.setString(4, user.getGender());
            st.setString(5, user.getAvatar());
            int res = st.executeUpdate();
            st.close();
            return (res == 1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public User getUser(String username) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("Select * from users where username = ?");
            st.setString(1, username);
            ResultSet res = st.executeQuery();
            if (!res.next()) {
                st.close();
                return null;
            }
            User user = new User(res.getString("username"), res.getString("password")
                    ,res.getString("mail"), res.getString("gender"), res.getString("avatar"));
            st.close();
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean removeUser(String username) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("Delete from users where username = ?");
            st.setString(1, username);
            int res = st.executeUpdate();
            st.close();
            return (res == 1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<User> getAll() {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("Select * from users");
            ResultSet res = st.executeQuery();
            if (!res.next()) {
                st.close();
                return null;
            }
            List<User> users = new ArrayList<>();
            User user = new User(res.getString("username"), res.getString("password")
                    ,res.getString("mail"), res.getString("gender"), res.getString("avatar"));
            users.add(user);
            while (res.next()) {
                user = new User(res.getString("username"), res.getString("password")
                        ,res.getString("mail"), res.getString("gender"), res.getString("avatar"));
                users.add(user);
            }
            st.close();
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}