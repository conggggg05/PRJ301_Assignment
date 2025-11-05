package dao;

import dal.DBContext;
import java.util.List;
import models.Account;
import java.sql.*;
import java.util.ArrayList;

public class AccountDAO extends DBContext {

    private static AccountDAO instance;
    private final String SQL_GET_ALL_ACCOUNT = "select * from Account";
    private final String SQL_GET_ACCOUNT = "SELECT * FROM Account WHERE username=? AND password=?";

    private AccountDAO() {
        // DBContext đã tự tạo connection trong constructor, không cần làm gì thêm
        super();
    }

    public static AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (Account a : AccountDAO.getInstance().getListAccount()) {
            System.out.println(a);
        }
    }

    // lấy list account
    public List<Account> getListAccount() {
        List<Account> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(SQL_GET_ALL_ACCOUNT);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String user = rs.getString(2);
                String pass = rs.getString(3);
                int role = rs.getInt(4);
                list.add(new Account(user, pass, role));
            }
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    //đăng nhập
    public Account login(String username, String password) {
        try (PreparedStatement ps = connection.prepareStatement(SQL_GET_ACCOUNT)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
