package Domain;

import java.util.List;

public interface IRoleRepository {
    public Role getRoleByID(int id);
    public List<Role> getAllRoles();
    public void addRole(Role role);
    public void deleteRole(Role role);
    public void updateRole(Role role);
}
