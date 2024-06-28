package Domain;

import Data.RoleDAOImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleRepository implements IRoleRepository {
    private final Map<Integer, Role> roles;
    private RoleDAOImpl dao;

    private RoleRepository(){
        this.dao = new RoleDAOImpl();
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
        if(roles.containsKey(role.getRoleID()) || dao.search(role.getRoleID())!=null){
            throw new IllegalArgumentException("Role with id " + role.getRoleID() + " already exists");
        } else {
            roles.put(role.getRoleID(), role);
            dao.insert(toJsonNode(role));
        }


    }

    @Override
    public void deleteRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        roles.remove(role.getRoleID());
        dao.remove(role.getRoleID());

    }

    @Override
    public void updateRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if(dao.search(role.getRoleID())==null){
            throw new IllegalArgumentException("Role with id " + role.getRoleID() + " not exists in DB");
        } else {
            roles.put(role.getRoleID(), role);
            dao.updateRole(toJsonNode(role));
        }

    }

    @Override
    public Role getRoleByID(int id) {
        if(roles.get(id)==null){
            Role role = fromJsonNode(dao.search(id));
            if(role!=null){
                return role;
            }
            else{
                return null;
            }
        }
        return roles.get(id);
    }

    @Override
    public List<Role> getAllRoles() {
        List<JsonNode> jsonNodes = dao.getAllRoles();
        List<Role> list_roles = new ArrayList<>();
        for (JsonNode jsonNode : jsonNodes) {
            Role role = fromJsonNode(jsonNode);
            if(!roles.containsKey(role.getRoleID())){
                roles.put(role.getRoleID(), role);
            }
            list_roles.add(role);
        }
        return list_roles;
    }

    private JsonNode toJsonNode(Role role){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(role, JsonNode.class);
    }
    private Role fromJsonNode(JsonNode role){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(role, Role.class);
    }

}
