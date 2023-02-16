package db;

import command.moderator.MeetingShowPageModeratorsCommand;
import command.user.UserParticipationMeetingPageCommand;
import db.Entity.Meeting;
import command.user.AvailableMeetingShowPageUserCommand;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class MeetingDAO {
    private static final String SQL__CREATE_NEW_MEETING = "INSERT INTO meetings(name, topic, date, creator_id, number_of_participants, number_of_reports)"
            + " VALUE (?,?,?,?,?,?)";

    private static final String SQL__SHOW_ALL_MEETINGS = "SELECT * FROM meetings LIMIT ?, ?";

    private static final String SQL__UPDATE_NUMBER_OF_REPORTS = "UPDATE meetings SET number_of_reports = number_of_reports+1 WHERE id = ?";

    private static final String SQL__SHOW_ALL_MEETINGS_SORTED_BY_DATE = "SELECT * FROM meetings ORDER BY date LIMIT ?, ?";
    private static final String SQL__SHOW_ALL_MEETINGS_SORTED_BY_NUMBER_OF_PARTICIPANTS = "SELECT * FROM meetings ORDER BY number_of_participants LIMIT ?, ?";
    private static final String SQL__SHOW_ALL_MEETINGS_SORTED_BY_NUMBER_OF_REPORTS = "SELECT * FROM meetings ORDER BY number_of_reports LIMIT ?, ?";
    private static final String SQL__COUNT_ALL_MEETINGS = "SELECT * FROM meetings";
    private static final String SQL__SHOW_ALL_AVAILABLE_MEETINGS_FOR_USERS = "SELECT * FROM meetings" +
            " WHERE NOT meetings.id IN (SELECT meeting_id FROM meetings_users WHERE user_id = ?) LIMIT ?, ?";

    private static final String SQL__COUNT_ALL_AVAILABLE_MEETINGS_FOR_USERS = "SELECT meetings.id, meetings.name, " +
            "meetings.creator_id, meetings.date, meetings.topic FROM meetings" +
            " WHERE NOT meetings.id IN (SELECT meeting_id FROM meetings_users WHERE user_id = ?)";

    private static final String SQL__SHOW_ALL_MEETINGS_WHERE_USER_PARTICIPATES = "SELECT meetings.* FROM meetings" +
            " LEFT JOIN meetings_users ON meetings.id=meetings_users.meeting_id WHERE meetings_users.user_id=? LIMIT ?, ?";

    private static final String SQL__COUNT_ALL_MEETINGS_WHERE_USER_PARTICIPATES = "SELECT meetings.id, meetings.name," +
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
            prStmt.setInt(5, meeting.getNumber_of_participants_who_came());
            prStmt.setInt(6, 0);
            prStmt.executeUpdate();
            ResultSet generatedKeys = prStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                meeting.setId(generatedKeys.getLong(1));
            }
            prStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
        }
    }

    public void updateNumberOfReports(long meeting_id) {
        PreparedStatement prStmt = null;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__UPDATE_NUMBER_OF_REPORTS);
            prStmt.setLong(1, meeting_id);
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

    public int numberOfAvailableMeetings(long user_id) {
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

    public int numberOfMeetingsWhereUserParticipates(long user_id) {
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        int i = 0;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__COUNT_ALL_MEETINGS_WHERE_USER_PARTICIPATES);
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

    public int numberOfAllMeetings() {
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        int i = 0;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__COUNT_ALL_MEETINGS);
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

    public ArrayList<Meeting> showAllMeetings(int page_number) {
        ArrayList<Meeting> meetingList = new ArrayList<>();
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            int lowerBorder = (page_number - 1) * MeetingShowPageModeratorsCommand.MEETING_SHOW_PAGE_MODERATORS_SPLIT_SIZE;
            int upperBorder = (page_number * MeetingShowPageModeratorsCommand.MEETING_SHOW_PAGE_MODERATORS_SPLIT_SIZE);
            con = ConnectionToDatabase.getInstance().getConnection();
            MeetingMapper mapper = new MeetingMapper();
            prStmt = con.prepareStatement(SQL__SHOW_ALL_MEETINGS);
            prStmt.setLong(1, lowerBorder);
            prStmt.setLong(2, upperBorder);
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

    public ArrayList<Meeting> showAllMeetings(int page_number, String sortingIndex) {
        ArrayList<Meeting> meetingList = new ArrayList<>();
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            int lowerBorder = (page_number - 1) * MeetingShowPageModeratorsCommand.MEETING_SHOW_PAGE_MODERATORS_SPLIT_SIZE;
            int upperBorder = (page_number * MeetingShowPageModeratorsCommand.MEETING_SHOW_PAGE_MODERATORS_SPLIT_SIZE);
            con = ConnectionToDatabase.getInstance().getConnection();
            MeetingMapper mapper = new MeetingMapper();
            switch (sortingIndex){
                case "date":
                    prStmt = con.prepareStatement(SQL__SHOW_ALL_MEETINGS_SORTED_BY_DATE);
                    break;
                case "number_of_participants":
                    prStmt = con.prepareStatement(SQL__SHOW_ALL_MEETINGS_SORTED_BY_NUMBER_OF_PARTICIPANTS);
                    break;
                case "number_of_reports":
                    prStmt = con.prepareStatement(SQL__SHOW_ALL_MEETINGS_SORTED_BY_NUMBER_OF_REPORTS);
                    break;
            }
            prStmt.setLong(1, lowerBorder);
            prStmt.setLong(2, upperBorder);
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

    public ArrayList<Meeting> showAllMeetingsWhereUserParticipates(long user_id, int page_number) {
        ArrayList<Meeting> meetingList = new ArrayList<>();
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            int lowerBorder = (page_number - 1) * UserParticipationMeetingPageCommand.USER_MEETING_PARTICIPATION_SPLIT_SIZE;
            int upperBorder = (page_number * UserParticipationMeetingPageCommand.USER_MEETING_PARTICIPATION_SPLIT_SIZE);
            con = ConnectionToDatabase.getInstance().getConnection();
            MeetingMapper mapper = new MeetingMapper();
            prStmt = con.prepareStatement(SQL__SHOW_ALL_MEETINGS_WHERE_USER_PARTICIPATES);
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

    public ArrayList<Meeting> showAllAvailableMeetingsForUsers(long user_id, int page_number) {
        ArrayList<Meeting> meetingList = new ArrayList<>();
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            int lowerBorder = (page_number - 1) * AvailableMeetingShowPageUserCommand.AVAILABLE_MEETING_USER_SPLIT_SIZE;
            int upperBorder = (page_number * AvailableMeetingShowPageUserCommand.AVAILABLE_MEETING_USER_SPLIT_SIZE);
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
                meeting.setNumber_of_participants_who_came(rs.getInt("number_of_participants"));
                meeting.setNumber_of_reports(rs.getInt("number_of_reports"));
                meeting.setDate(rs.getDate("date"));
                return meeting;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
