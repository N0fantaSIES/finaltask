package command.login;

import db.Entity.MainUser;
import db.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RegisterCommandTest {



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute() {
        try  {
            MockedConstruction<UserDAO> mocked = Mockito.mockConstruction(UserDAO.class,
                (mock, context) -> {
                // further stubbings ...
            });
            RegisterCommand regComm = new RegisterCommand();
            HttpServletRequest mockReq = spy(HttpServletRequest.class);
            HttpServletResponse mockResp = spy(HttpServletResponse.class);
            HttpSession mockSession = spy(HttpSession.class);

            when(mockReq.getParameter("login")).thenReturn("testLogin");
            when(mockReq.getParameter("password")).thenReturn("testPassword");
            when(mockReq.getParameter("name")).thenReturn("testName");
            when(mockReq.getParameter("surname")).thenReturn("testSurname");
            when(mockReq.getSession()).thenReturn(mockSession);

            assertEquals(regComm.execute(mockReq, mockResp), "/main.jsp");
            UserDAO userDAO = mocked.constructed().get(0);

            assertEquals(1, mocked.constructed().size());
            ArgumentCaptor<MainUser> captor = ArgumentCaptor.forClass(MainUser.class);
            verify(userDAO).createNewUser(captor.capture());
            MainUser invocationArg = captor.getValue();

            assertEquals("testLogin", invocationArg.getLogin());
            assertEquals("testPassword", invocationArg.getPassword());
            assertEquals("testName", invocationArg.getName());
            assertEquals("testSurname", invocationArg.getSurname());

            verify(userDAO, times(1)).createNewUser(any(MainUser.class));
            verify(mockSession, times(1)).setAttribute("mainUser", invocationArg);

            mocked.close();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}