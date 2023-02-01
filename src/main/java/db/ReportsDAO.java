package db;

import db.Entity.Meeting;
import db.Entity.Report;

import java.sql.*;
import java.util.ArrayList;

public class ReportsDAO {

    private static final String SQL__CREATE_NEW_REPORT = "INSERT INTO reports(meeting_id, title_of_report)"
            + "VALUE (?,?)";
    private static final String SQL__SHOW_ALL_USER_REPORTS_BY_ID = "SELECT * FROM reports WHERE user_id = ?";
    private static final String SQL__SHOW_ALL_USER_REPORTS = "SELECT * FROM reports";

    private static final String SQL__SHOW_ALL_AVAILABLE_REPORTS_FOR_USERS = "SELECT meetings.name," +
            " meetings.creator_id, meetings.date," +
            " meetings.topic, reports.title_of_report, reports.meeting_id," +
            " reports.id FROM meetings JOIN reports ON meetings.id=reports.meeting_id " +
            "WHERE user_id IS NULL ";
    private static final String SQL__REPLACE_USER_ID_BY_REPORTS_ID = "UPDATE reports SET user_id = ? WHERE id = ?";

    private static final String SQL__COUNT_NUMBER_OF_REPORTS = "SELECT * FROM reports WHERE meeting_id=?";

    private static final String SQL__DELETE_REPORT_BY_REPORT_ID = "DELETE FROM reports WHERE id = ?";

    public int countNumberOfReportsPerMeeting(long meeting_id) {
        PreparedStatement prStmt;
        ResultSet rs;
        Connection con = null;
        int i = 0;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__COUNT_NUMBER_OF_REPORTS);
            prStmt.setLong(1, meeting_id);
            rs = prStmt.executeQuery();
            while (rs.next()) {
                i++;
            }
            rs.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
        }
        return i;
    }

    public void createNewReport(Report report) {
        PreparedStatement prStmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__CREATE_NEW_REPORT, Statement.RETURN_GENERATED_KEYS);
            prStmt.setLong(1, report.getMeeting_id());
            prStmt.setString(2, report.getTitle_of_report());
            prStmt.executeUpdate();
            ResultSet generatedKeys = prStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                report.setId(generatedKeys.getLong(1));
            }
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
        }
    }

    public ArrayList<Report> findReportsByUserId(long id) {
        ArrayList<Report> reportArrayList = new ArrayList<>();
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            ReportMapper mapper = new ReportMapper();
            prStmt = con.prepareStatement(SQL__SHOW_ALL_USER_REPORTS_BY_ID);
            prStmt.setLong(1, id);
            rs = prStmt.executeQuery();
            while (rs.next()) {
                reportArrayList.add(mapper.mapRow(rs));
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
        return reportArrayList;
    }

    public ArrayList<Report> showAllAvailableReportsForSpeakers() {
        ArrayList<Report> reportArrayList = new ArrayList<>();
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            ReportMapper mapper = new ReportMapper();
            prStmt = con.prepareStatement(SQL__SHOW_ALL_AVAILABLE_REPORTS_FOR_USERS);
            rs = prStmt.executeQuery();
            while (rs.next())
                reportArrayList.add(mapper.mapRow(rs));
            rs.close();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
//            ConnectionToDatabase.getInstance().commitAndClose(con);
        }
        return reportArrayList;
    }

    public ArrayList<Report> showAllReports() {
        ArrayList<Report> reportArrayList = new ArrayList<>();
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            ReportMapper mapper = new ReportMapper();
            prStmt = con.prepareStatement(SQL__SHOW_ALL_USER_REPORTS);
            rs = prStmt.executeQuery();
            while (rs.next())
                reportArrayList.add(mapper.mapRow(rs));
            rs.close();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
//            ConnectionToDatabase.getInstance().commitAndClose(con);
        }
        return reportArrayList;
    }

    public void updateUserIdByReportId(long reportId, long userId) {
        PreparedStatement prStmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__REPLACE_USER_ID_BY_REPORTS_ID);
            prStmt.setLong(1, userId);
            prStmt.setLong(2, reportId);
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

    public void deleteReportByReportId(Report report) {
        PreparedStatement prStmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = ConnectionToDatabase.getInstance().getConnection();
            prStmt = con.prepareStatement(SQL__DELETE_REPORT_BY_REPORT_ID);
            prStmt.setLong(1, report.getId());
            prStmt.executeUpdate();
            prStmt.close();
        } catch (SQLException ex) {
//            ConnectionToDatabase.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            ConnectionToDatabase.close(con);
        }
    }

    private static class ReportMapper implements EntityMapper<Report> {

        @Override
        public Report mapRow(ResultSet rs) {
            try {
                MeetingDAO meetingDAO = new MeetingDAO();
                Meeting meeting = new Meeting();
                meeting = meetingDAO.findMeetingByMeetingId(rs.getLong("meeting_id"));
                Report report = new Report();
                report.setId(rs.getLong("id"));
                report.setTitle_of_report(rs.getString("title_of_report"));
                report.setMeeting_name(meeting.getName());
                report.setMeeting_date(meeting.getDate());
                report.setMeeting_id(meeting.getId());
                report.setMeeting_topic(meeting.getTopic());
                report.setMeeting_creator_id(meeting.getCreator_id());
                report.setMeeting_creator_name(meeting.getCreator_name());
                return report;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
