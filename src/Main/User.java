package Main;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlType(propOrder = { "name","role","username","password"})

//@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    private String name;
    private String role;
    private String username;
    private String password;


    @XmlElement(name="name")
    public String getName() {
        return name;
    }

    @XmlElement(name="role")
    public String getRole() {
        return role;
    }

    @XmlElement(name="username")
    public String getUsername() {
        return username;
    }

    @XmlElement(name="password")
    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
