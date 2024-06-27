package Data;

import java.util.List;
import Domain.Role;
public interface IRoleDao {
    void addRole(Role role);
    Role getRoleById(int id);
    List<Role> getAllRoles();
    void updateRole(Role role);
    void deleteRole(int id);
}