package Domain;

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

    public void setRole_ID(int role_ID) {
        Role_ID = role_ID;
    }

    public String getName() {
        return name;
    }

    public int getRole_ID() {
        return Role_ID;
    }
}
