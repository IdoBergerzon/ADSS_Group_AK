package Domain;

import java.util.List;

public interface IBranchRepository {

    void addBranch(Branch branch);
    Branch getBranchByID(int id);
    void deleteBranch(int id);

}
