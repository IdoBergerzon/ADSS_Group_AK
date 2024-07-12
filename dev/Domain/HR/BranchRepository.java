package Domain.HR;

import DAL.HR.BranchDAOImpl;

import java.util.HashMap;
import java.util.Map;

public class BranchRepository implements IRepository<Branch,Integer> {
    private final Map<Integer, Branch> branches;
    private BranchDAOImpl dao;

    private BranchRepository(){
        this.branches = new HashMap<>();
        this.dao = new BranchDAOImpl();
    }

    private static class InMemoryHolder {
        private final static BranchRepository INSTANCE = new BranchRepository();
    }

    public static BranchRepository getInstance() {
        return BranchRepository.InMemoryHolder.INSTANCE;
    }

    @Override
    public void add(Branch branch) {
        if (branch == null) {
            throw new IllegalArgumentException("Branch cannot be null");
        }
        if(dao.search(branch.getBranchID()) != null){
            throw new IllegalArgumentException("Branch with id " + branch.getBranchID() + " already exists");
        }
        branches.put(branch.getBranchID(), branch);
        dao.insert(JsonNodeConverter.toJsonNode(branch));
    }

    @Override
    public Branch get(Integer id) {
        if(branches.containsKey(id))
            return branches.get(id);
        return JsonNodeConverter.fromJsonNode(dao.search(id), Branch.class);
    }

    @Override
    public void remove(Integer id) {
        if(id != null){
            try{
                dao.remove(id);
                branches.remove(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
