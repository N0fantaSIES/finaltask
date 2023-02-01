package db;

import db.Entity.Meeting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeetingUserDAO {

//    private static final String SQL__CREATE_NEW_LINK_BETWEEN_USER_ID_MEETING_ID = "INSERT INTO meetings_users(meeting_id,user_id)\n" +
//            "SELECT * FROM (SELECT ? AS meeting_id, ? AS user_id) AS new_value\n" +
//            "WHERE NOT EXISTS (\n" +
//            "    SELECT user_id FROM meetings_users WHERE user_id = ?\n" +
//            ") LIMIT 1;";

    private static final String SQL__CREATE_NEW_LINK_BETWEEN_USER_ID_MEETING_ID = "INSERT INTO meetings_users" +
            " (meeting_id,user_id) VALUE (?,?)";

    private static final String SQL__COUNT_NUMBER_OF_PARTICIPANTS = "SELECT * FROM meetings_users WHERE meeting_id=?";

    private static final String SQL__DELETE_MEETING_USER_BY_MEETING_ID_USER_ID = "DELETE FROM meetings_users" +
            " WHERE user_id = ? AND meeting_id = ?";

    public int countNumberOfUsersPerMeeting (long meeting_id){
        PreparedStatement prStmt;
        ResultSet rs;
        Connection con = null;
        int i = 0;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__COUNT_NUMBER_OF_PARTICIPANTS);
            prStmt.setLong(1, meeting_id);
            rs = prStmt.executeQuery();
            while(rs.next()){
                i++;
            }
            rs.close();
        }catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
        }
        return i;
    }

    public void deleteMeetingUsersByMeetingIdUserId(long meeting_id, long user_id) {
        PreparedStatement prStmt;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__DELETE_MEETING_USER_BY_MEETING_ID_USER_ID);
            prStmt.setLong(1, user_id);
            prStmt.setLong(2, meeting_id);
            prStmt.executeUpdate();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
        }
    }

    public void createNewMeetingUser(long meeting_id, long user_id) {
        PreparedStatement prStmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__CREATE_NEW_LINK_BETWEEN_USER_ID_MEETING_ID);
            prStmt.setLong(1, meeting_id);
            prStmt.setLong(2, user_id);
            prStmt.executeUpdate();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
        }
    }
}
