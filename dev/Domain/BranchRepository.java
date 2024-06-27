package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BranchRepository implements IBranchRepository {
    private final Map<Integer, Branch> branches;

    private BranchRepository(){
        this.branches = new HashMap<>();
    }

    private static class InMemoryHolder {
        private final static BranchRepository INSTANCE = new BranchRepository();
    }

    public static BranchRepository getInstance() {
        return BranchRepository.InMemoryHolder.INSTANCE;
    }

    @Override
    public void addBranch(Branch branch) {
        if (branch == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if(branches.containsKey(branch.getBranchID())){
            throw new IllegalArgumentException("Role with id " + branch.getBranchID() + " already exists");
        }
        branches.put(branch.getBranchID(), branch);
    }

    @Override
    public Branch getBranchByID(int id) {
        return branches.get(id);
    }

    @Override
    public void deleteBranch(int id) {
        branches.remove(id);
    }


}
