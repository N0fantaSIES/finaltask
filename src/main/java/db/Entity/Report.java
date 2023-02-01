package db.Entity;

import java.sql.Date;
import java.util.Objects;

public class Report extends Entity{
    private long user_id;
    private long meeting_id;
    private String title_of_report;

    private String meeting_name;

    private long meeting_creator_id;
    private Date meeting_date;
    private String meeting_topic;
    private String meeting_creator_name;

    public String getMeeting_creator_name() {
        return meeting_creator_name;
    }

    public void setMeeting_creator_name(String meeting_creator_name) {
        this.meeting_creator_name = meeting_creator_name;
    }

    public long getMeeting_creator_id() {
        return meeting_creator_id;
    }

    public void setMeeting_creator_id(long meeting_creator_id) {
        this.meeting_creator_id = meeting_creator_id;
    }

    public Date getMeeting_date() {
        return meeting_date;
    }

    public void setMeeting_date(Date meeting_date) {
        this.meeting_date = meeting_date;
    }

    public String getMeeting_topic() {
        return meeting_topic;
    }

    public void setMeeting_topic(String meeting_topic) {
        this.meeting_topic = meeting_topic;
    }

    public String getMeeting_name() {
        return meeting_name;
    }

    public void setMeeting_name(String meeting_name) {
        this.meeting_name = meeting_name;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(long meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getTitle_of_report() {
        return title_of_report;
    }

    public void setTitle_of_report(String title_of_report) {
        this.title_of_report = title_of_report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return meeting_id == report.meeting_id && meeting_creator_id == report.meeting_creator_id && Objects.equals(title_of_report, report.title_of_report) && Objects.equals(meeting_name, report.meeting_name) && Objects.equals(meeting_date, report.meeting_date) && Objects.equals(meeting_topic, report.meeting_topic) && Objects.equals(meeting_creator_name, report.meeting_creator_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, meeting_id, title_of_report, meeting_name, meeting_creator_id, meeting_date, meeting_topic, meeting_creator_name);
    }

    @Override
    public String toString() {
        return "Report{" +
                "user_id=" + user_id +
                ", meeting_id=" + meeting_id +
                ", title_of_report='" + title_of_report + '\'' +
                '}';
    }
}
