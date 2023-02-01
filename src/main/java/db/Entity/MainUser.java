package db.Entity;

import java.sql.Date;
import java.util.Objects;

public class MainUser extends Entity{
    private String login;
    private String password;

    private Date date;
    private String name;
    private String surname;
    private int role_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        MainUser mainUser = (MainUser) o;
        return role_id == mainUser.role_id && Objects.equals(login, mainUser.login) && Objects.equals(password, mainUser.password) && Objects.equals(date, mainUser.date) && Objects.equals(name, mainUser.name) && Objects.equals(surname, mainUser.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, date, name, surname, role_id);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role_id=" + role_id +
                '}';
    }

}
