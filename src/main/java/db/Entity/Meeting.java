package db.Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Meeting extends Entity{
    private String name;
    private String topic;
    private Date date;
    private long creator_id;
    private String creator_name;

    private int number_of_participants;

    private int number_of_participants_who_came;

    private int number_of_reports;

    public int getNumber_of_participants_who_came() {
        return number_of_participants_who_came;
    }

    public void setNumber_of_participants_who_came(int number_of_participants_who_came) {
        this.number_of_participants_who_came = number_of_participants_who_came;
    }

    public int getNumber_of_reports() {
        return number_of_reports;
    }

    public void setNumber_of_reports(int number_of_reports) {
        this.number_of_reports = number_of_reports;
    }

    public int getNumber_of_participants() {
        return number_of_participants;
    }

    public void setNumber_of_participants(int number_of_participants) {
        this.number_of_participants = number_of_participants;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(long creator_id) {
        this.creator_id = creator_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return creator_id == meeting.creator_id && number_of_participants == meeting.number_of_participants && number_of_reports == meeting.number_of_reports && Objects.equals(name, meeting.name) && Objects.equals(topic, meeting.topic) && Objects.equals(date, meeting.date) && Objects.equals(creator_name, meeting.creator_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, topic, date, creator_id, creator_name, number_of_participants, number_of_reports);
    }

    @Override
    public String toString() {
        return "Report{" +
                "name='" + name + '\'' +
                ", topic='" + topic + '\'' +
                ", date=" + date +
                '}';
    }
}
