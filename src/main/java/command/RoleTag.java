package command;

import db.Entity.MainUser;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class RoleTag extends SimpleTagSupport {

    private MainUser user;

    public void setUser (MainUser user){
        this.user = user;
    }
    @Override
    public void doTag() {
        if (user != null){
            try {
                switch (user.getRole_id()) {
                    case 1:
                        getJspContext().getOut().print(user.getLogin());
                        getJspContext().getOut().print(" (admin)");
                        break;
                    case 2:
                        getJspContext().getOut().print(user.getLogin());
                        getJspContext().getOut().print(" (user)");
                        break;
                    case 3:
                        getJspContext().getOut().print(user.getLogin());
                        getJspContext().getOut().print(" (speaker)");
                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
