package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleRepository implements IRoleRepository {
    private final Map<Integer, Role> roles;

    private RoleRepository(){
        this.roles = new HashMap<>();
    }

    private static class InMemoryHolder {
        private final static RoleRepository INSTANCE = new RoleRepository();
    }

    public static RoleRepository getInstance() {
        return RoleRepository.InMemoryHolder.INSTANCE;
    }

    @Override
    public void addRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if(roles.containsKey(role.getRoleID())){
            throw new IllegalArgumentException("Role with id " + role.getRoleID() + " already exists");
        }
        roles.put(role.getRoleID(), role);
    }

    @Override
    public void deleteRole(Role role) {

    }

    @Override
    public void updateRole(Role role) {

    }

    @Override
    public Role getRoleByID(int id) {
        return roles.get(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return new ArrayList<>(roles.values());
    }

}
