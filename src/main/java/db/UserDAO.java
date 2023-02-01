package db;

import db.Entity.MainUser;

import java.sql.*;

public class UserDAO {
    private static final String SQL__CREATE_NEW_USER = "INSERT INTO users(login, password, data, name, surname, role_id)"
            + "VALUE (?,?,?,?,?,?)";
    private static final String SQL__DELETE_USER_BY_LOGIN_AND_PASSWORD = "DELETE FROM users WHERE login = ? AND password = ?";

    private static final String SQL__FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?";

    private static final String SQL__FIND_USER_BY_USER_ID = "SELECT * FROM users WHERE id = ?";

    public void createNewUser(MainUser mainUser) {
        PreparedStatement prStmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__CREATE_NEW_USER, Statement.RETURN_GENERATED_KEYS);
            int indexParam = 1;
            prStmt.setString(indexParam++, mainUser.getLogin());
            prStmt.setString(indexParam++, mainUser.getPassword());
            prStmt.setDate(indexParam++, mainUser.getDate());
            prStmt.setString(indexParam++, mainUser.getName());
            prStmt.setString(indexParam++, mainUser.getSurname());
            prStmt.setInt(indexParam++, mainUser.getRole_id());
            prStmt.executeUpdate();
            ResultSet generatedKeys = prStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                mainUser.setId(generatedKeys.getLong(1));
            }
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
        }
    }

    public MainUser findUserByUserId(long id) {
        MainUser mainUser = null;
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            UserDAO.UserMapper mapper = new UserDAO.UserMapper();
            prStmt = con.prepareStatement(SQL__FIND_USER_BY_USER_ID);
            prStmt.setLong(1, id);
            rs = prStmt.executeQuery();
            if (rs.next()) {
                mainUser = mapper.mapRow(rs);
            }
            rs.close();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
//            ConnectionToDatabase.getInstance().commitAndClose(con);
        }
        return mainUser;
    }

    public MainUser findUserByLoginAndPassword(String login, String password) {
        MainUser mainUser = null;
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            UserDAO.UserMapper mapper = new UserDAO.UserMapper();
            prStmt = con.prepareStatement(SQL__FIND_USER_BY_LOGIN_AND_PASSWORD);
            prStmt.setString(1, login);
            prStmt.setString(2, password);
            rs = prStmt.executeQuery();
            if (rs.next()) {
                mainUser = mapper.mapRow(rs);
            }
            rs.close();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
//            ConnectionToDatabase.getInstance().commitAndClose(con);
        }
        return mainUser;
    }

    public void deleteUserByLoginAndPassword(String login, String password) {
        PreparedStatement prStmt;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__DELETE_USER_BY_LOGIN_AND_PASSWORD);
            prStmt.setString(1, login);
            prStmt.setString(2, password);
            prStmt.executeUpdate();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
//            ConnectionToDatabase.getInstance().commitAndClose(con);
        }
    }

    private static class UserMapper implements EntityMapper<MainUser> {

        @Override
        public MainUser mapRow(ResultSet rs) {
            try {
                MainUser mainUser = new MainUser();
                mainUser.setId(rs.getLong("id"));
                mainUser.setName(rs.getString("name"));
                mainUser.setSurname(rs.getString("surname"));
                mainUser.setLogin(rs.getString("login"));
                mainUser.setPassword(rs.getString("password"));
                mainUser.setDate(rs.getDate("data"));
                mainUser.setRole_id(rs.getInt("role_id"));
                return mainUser;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
