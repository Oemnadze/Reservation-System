package ge.edu.freeuni.DAO;

import ge.edu.freeuni.Models.User;

import java.sql.*;

public class OrdersDAO {
    private Connection con;

    public OrdersDAO(String url, String user_name, String password) throws SQLException {
        this.con = DriverManager.getConnection(url, user_name, password);
    }

    public boolean addUser(User user) throws SQLException {
        String query = "insert into orders (username, orders_num, bonus_num) " +
                       "values (?, ?, ?)";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,user.getUsername());
        st.setInt(2, 0);
        st.setInt(3, 0);
        int res = st.executeUpdate();
        return res == 1;

    }

    public int getUserOrders(User user) throws SQLException {
        String query = "select orders_num from orders where username = ?";
        ResultSet rs = getResultSet(user.getUsername(), query);
        if(!rs.next()){
            rs.close();
            return -1;
        }
        return rs.getInt(1);
    }

    public int getUserBonus(User user) throws SQLException {
        String query = "select bonus_num from orders where username = ?";
        ResultSet rs = getResultSet(user.getUsername(), query);
        if(!rs.next()){
            return -1;
        }
        return rs.getInt(1);
    }

    public void updateUserBonus(User user) throws SQLException {
        String query = "update orders set bonus_num = ? where username = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, user.getBonus());
        st.setString(2, user.getUsername());
        st.executeUpdate();
        st.close();
    }

    public void updateUserOrders(User user) throws SQLException {
        String query = "update orders set orders_num = ? where username = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, user.getOrders());
        st.setString(2, user.getUsername());
        st.executeUpdate();
        st.close();
    }

    private ResultSet getResultSet(String username,String query) throws SQLException { ;
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,username);
        ResultSet rs = st.executeQuery();
        return rs;
    }

}
