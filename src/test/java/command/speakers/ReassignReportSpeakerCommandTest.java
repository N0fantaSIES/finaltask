package command.speakers;

import db.MeetingDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class ReassignReportSpeakerCommandTest {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute() {
//        try {
//            MockedConstruction<MeetingDAO> mocked = Mockito.mockConstruction(MeetingDAO.class,
//                    (mock, context) -> {
//                        // further stubbings ...
//                    });
//            ReassignReportSpeakerCommand reassignReportSpeakerCommand = new ReassignReportSpeakerCommand();
//
//        }
    }
}