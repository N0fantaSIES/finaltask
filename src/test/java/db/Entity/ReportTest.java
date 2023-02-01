package db.Entity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class ReportTest {

    private Report report;

    private long user_id;
    private long meeting_id;
    private String title_of_report;

    private String meeting_name;

    private long meeting_creator_id;
    private Date meeting_date;
    private String meeting_topic;
    private String meeting_creator_name;

    @Before
    public void setUp() throws Exception {
        report = new Report();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMeeting_creator_name() {
        report.setMeeting_creator_name("moder");
        String reportCreatorName = report.getMeeting_creator_name();
        Assert.assertEquals(reportCreatorName, "moder");
    }

    @Test
    public void setMeeting_creator_name() {
        report.setMeeting_creator_name("moder");
        Assert.assertEquals(report.getMeeting_creator_name(), "moder");
    }

    @Test
    public void getMeeting_creator_id() {
        report.setMeeting_creator_id(33);
        long reportCreatorName = report.getMeeting_creator_id();
        Assert.assertEquals(reportCreatorName, 33);
    }

}