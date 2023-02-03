package db;

import db.Entity.Meeting;
import command.user.AvailableMeetingShowPageUserCommand;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MeetingDAO {
    private static final String SQL__CREATE_NEW_MEETING = "INSERT INTO meetings(name, topic, date, creator_id)"
            + " VALUE (?,?,?,?)";

    private static final String SQL__SHOW_ALL_MEETINGS = "SELECT * FROM meetings";
    private static final String SQL__SHOW_ALL_AVAILABLE_MEETINGS_FOR_USERS = "SELECT meetings.id, meetings.name, " +
            "meetings.creator_id, meetings.date, meetings.topic FROM meetings" +
            " WHERE NOT meetings.id IN (SELECT meeting_id FROM meetings_users WHERE user_id = ?) LIMIT ?, ?";

    private static final String SQL__COUNT_ALL_AVAILABLE_MEETINGS_FOR_USERS = "SELECT meetings.id, meetings.name, " +
            "meetings.creator_id, meetings.date, meetings.topic FROM meetings" +
            " WHERE NOT meetings.id IN (SELECT meeting_id FROM meetings_users WHERE user_id = ?)";

    private static final String SQL__SHOW_ALL_MEETINGS_WHERE_USER_PARTICIPATES = "SELECT meetings.id, meetings.name," +
            " meetings.creator_id, meetings.date, meetings.topic FROM meetings" +
            " LEFT JOIN meetings_users ON meetings.id=meetings_users.meeting_id WHERE meetings_users.user_id=? ";

    private static final String SQL__FIND_MEETING_BY_MEETING_ID = "SELECT * FROM meetings WHERE id = ?";

    private static final String SQL__DELETE_MEETING_BY_MEETING_ID = "DELETE FROM meetings WHERE name = ?";

    public void createNewMeeting(Meeting meeting) {
        PreparedStatement prStmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__CREATE_NEW_MEETING, Statement.RETURN_GENERATED_KEYS);
            prStmt.setString(1, meeting.getName());
            prStmt.setString(2, meeting.getTopic());
            prStmt.setDate(3, meeting.getDate());
            prStmt.setLong(4, meeting.getCreator_id());
            prStmt.executeUpdate();
            ResultSet generatedKeys = prStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                meeting.setId(generatedKeys.getLong(1));
            }
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
        }
    }

    public int numberOfAvailableMeetings(long user_id){
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        int i = 0;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();

            prStmt = con.prepareStatement(SQL__COUNT_ALL_AVAILABLE_MEETINGS_FOR_USERS);
            prStmt.setLong(1, user_id);
            rs = prStmt.executeQuery();
            while (rs.next())
                i++;
            rs.close();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
//            ConnectionToDatabase.getInstance().commitAndClose(con);
        }
        return i;
    }

    public void deleteMeeting(Meeting meeting) {
        PreparedStatement prStmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__DELETE_MEETING_BY_MEETING_ID);
            prStmt.setString(1, meeting.getName());
            prStmt.executeUpdate();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
        }
    }

    public ArrayList<Meeting> showAllMeetings() {
        ArrayList<Meeting> meetingList = new ArrayList<>();
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            MeetingMapper mapper = new MeetingMapper();
            prStmt = con.prepareStatement(SQL__SHOW_ALL_MEETINGS);
            rs = prStmt.executeQuery();
            while (rs.next())
                meetingList.add(mapper.mapRow(rs));
            rs.close();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
//            ConnectionToDatabase.getInstance().commitAndClose(con);
        }
        return meetingList;
    }

    public ArrayList<Meeting> showAllMeetingsWhereUserParticipates(long user_id) {
        ArrayList<Meeting> meetingList = new ArrayList<>();
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            MeetingMapper mapper = new MeetingMapper();
            prStmt = con.prepareStatement(SQL__SHOW_ALL_MEETINGS_WHERE_USER_PARTICIPATES);
            prStmt.setLong(1, user_id);
            rs = prStmt.executeQuery();
            while (rs.next())
                meetingList.add(mapper.mapRow(rs));
            rs.close();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
//            ConnectionToDatabase.getInstance().commitAndClose(con);
        }
        return meetingList;
    }

    public ArrayList<Meeting> showAllAvailableMeetingsForUsers(long user_id, int page_number) {
        ArrayList<Meeting> meetingList = new ArrayList<>();
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            int lowerBorder = (page_number - 1) * AvailableMeetingShowPageUserCommand.SPLIT_SIZE;
            int upperBorder = (page_number * AvailableMeetingShowPageUserCommand.SPLIT_SIZE);
            con = ConnectionToDatabase.getInstance().getConnection();
            MeetingMapper mapper = new MeetingMapper();
            prStmt = con.prepareStatement(SQL__SHOW_ALL_AVAILABLE_MEETINGS_FOR_USERS);
            prStmt.setLong(1, user_id);
            prStmt.setLong(2, lowerBorder);
            prStmt.setLong(3, upperBorder);
            rs = prStmt.executeQuery();
            while (rs.next())
                meetingList.add(mapper.mapRow(rs));
            rs.close();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
//            ConnectionToDatabase.getInstance().commitAndClose(con);
        }
        return meetingList;
    }

    public Meeting findMeetingByMeetingId(long id) {
        Meeting meeting = null;
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            MeetingMapper mapper = new MeetingMapper();
            prStmt = con.prepareStatement(SQL__FIND_MEETING_BY_MEETING_ID);
            prStmt.setLong(1, id);
            rs = prStmt.executeQuery();
            if (rs.next()) {
                meeting = mapper.mapRow(rs);
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
        return meeting;
    }

    private static class MeetingMapper implements EntityMapper<Meeting> {

        @Override
        public Meeting mapRow(ResultSet rs) {
            try {
                UserDAO userDAO = new UserDAO();
                Meeting meeting = new Meeting();
                meeting.setId(rs.getLong("id"));
                meeting.setName(rs.getString("name"));
                meeting.setTopic(rs.getString("topic"));
                meeting.setCreator_id(rs.getLong("creator_id"));
                meeting.setCreator_name(userDAO.findUserByUserId(rs.getLong("creator_id")).getLogin());
                meeting.setDate(rs.getDate("date"));
                return meeting;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
