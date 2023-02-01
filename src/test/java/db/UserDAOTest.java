package db;

import db.Entity.MainUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class UserDAOTest {

    @Mock
    private MainUser mainUser;

    @Mock
    private UserDAO userDAO;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void createNewUser() {
        userDAO.createNewUser(mainUser);
        long userId = mainUser.getId();
        Mockito.doReturn(mainUser).when(userDAO).findUserByUserId(userId);
        Assert.assertEquals(mainUser, userDAO.findUserByUserId(userId));
    }


    @Test
    public void findUserByUserId() {
        userDAO.createNewUser(mainUser);
        long userId = mainUser.getId();
        Mockito.doReturn(mainUser).when(userDAO).findUserByUserId(userId);
        Assert.assertEquals(mainUser, userDAO.findUserByUserId(userId));
    }

    @Test
    public void findUserByLoginAndPassword() {
        userDAO.createNewUser(mainUser);
        String userLogin = mainUser.getLogin();
        String userPassword = mainUser.getPassword();
        Mockito.doReturn(mainUser).when(userDAO).findUserByLoginAndPassword(userLogin, userPassword);
        Assert.assertEquals(mainUser, userDAO.findUserByLoginAndPassword(userLogin, userPassword));
    }
}