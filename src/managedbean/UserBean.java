package managedbean;

import backingbean.User;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
public class UserBean {
    private User user;

    public UserBean() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String loginUser(){
        if(user.getEmail().equals("admin")&&user.getPassword().equals("admin"))
        {
            HttpSession session =
        (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("username", user.getEmail());
            session.setMaxInactiveInterval(10*60);
            return "welcome.xhtml?faces-redirect=true";
        }
        return "login";
    }
    public boolean isLoggedIn(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session!=null){
            String uname = (String) session.getAttribute("username");
            if(uname!=null){
                return true;
            }
        }
        return false;
    }
    public String logoutUser(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "login.xhtml?faces-redirect=true";
    }
}
