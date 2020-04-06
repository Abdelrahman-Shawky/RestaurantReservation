package GUI;

public class ManagerTable {

    private String name;
    private String role;


    public ManagerTable() {
        this.name = "";
        this.role = "";
    }

    public ManagerTable(String name,String role){
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
