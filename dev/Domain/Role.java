package Domain;

import java.util.Objects;

public class Role {
    private String name;
    private int Role_ID;

    public Role(int role_ID, String name) {
        Role_ID = role_ID;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoleID(int role_ID) {
        Role_ID = role_ID;
    }

    public String getName() {
        return name;
    }

    public int getRoleID() {
        return Role_ID;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Role_ID == role.Role_ID && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Role_ID);
    }

    public String toString(){
        String result_str="";
        result_str+="Role ID: "+this.getRoleID()+", ";
        result_str+="Role name: "+this.getName();
        return result_str;
    }
}
