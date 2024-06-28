package Data;

import Domain.Role;
import Domain.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkerDAOImpl implements IDao<Worker, Integer>{

    @Override
    public Worker search(Integer id) {
        String sql = "SELECT * FROM Worker WHERE worker_id = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractWorkerFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Worker worker) {
        String sql = "INSERT INTO Worker (worker_id, name, monthly_wage, hourly_wage, start_date, direct_manager_ID, roles, branchid, department, bank_details) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, worker.getId());
            statement.setString(2, worker.getName());
            statement.setInt(3, worker.getMonthly_wage());
            statement.setInt(4, worker.getHourly_wage());
            statement.setDate(5, new java.sql.Date(worker.getStart_Date().getTime()));
            statement.setInt(6, worker.getDirect_manager());
            statement.setString(7, convertRolesArrayToString(worker.getRoles()));
            statement.setInt(8, worker.getWork_branch());
            statement.setString(9, worker.getDepartement());
            statement.setString(10, worker.getBank_details());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Integer id) {
        String sql = "DELETE FROM Worker WHERE worker_id = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Worker> GetAllWorkers() {
        List<Worker> workers = new ArrayList<>();
        String sql = "SELECT * FROM Worker";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Worker worker = extractWorkerFromResultSet(resultSet);
                workers.add(worker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }


    public void updateWorker(Worker worker) {
        String sql = "UPDATE Worker SET name = ?, monthly_wage = ?, hourly_wage = ?, " +
                "start_date = ?, direct_manager_ID = ?, roles = ?, branchid = ?, " +
                "department = ?, bank_details = ? WHERE worker_id = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, worker.getName());
            statement.setInt(2, worker.getMonthly_wage());
            statement.setInt(3, worker.getHourly_wage());
            statement.setDate(4, new java.sql.Date(worker.getStart_Date().getTime()));
            statement.setInt(5, worker.getDirect_manager());
            statement.setString(6, convertRolesArrayToString(worker.getRoles()));
            statement.setInt(7, worker.getWork_branch());
            statement.setString(8, worker.getDepartement());
            statement.setString(9, worker.getBank_details());
            statement.setInt(10, worker.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Worker extractWorkerFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("worker_id");
        String name = resultSet.getString("name");
        int monthly_wage = resultSet.getInt("monthly_wage");
        int hourly_wage = resultSet.getInt("hourly_wage");
        Date start_date = resultSet.getDate("start_date");
        int direct_manager_ID = resultSet.getInt("direct_manager_ID");
        Role[] roles = convertRolesStringToArray(resultSet.getString("roles"));
        int branchid = resultSet.getInt("branchid");
        String department = resultSet.getString("department");
        String bank_details = resultSet.getString("bank_details");

        return new Worker(id, name, monthly_wage, hourly_wage, start_date, direct_manager_ID, roles[0], branchid, department, bank_details);
    }

    private String convertRolesArrayToString(Role[] roles) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roles.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(roles[i].getRoleID());
        }
        return sb.toString();
    }
    private Role[] convertRolesStringToArray(String rolesString) {
        String[] roleIds = rolesString.split(",");
        Role[] roles = new Role[roleIds.length];
        for (int i = 0; i < roleIds.length; i++) {
            int roleId = Integer.parseInt(roleIds[i]);
            roles[i] = new Role(roleId, "Role"); // Assuming a default role name for now
        }
        return roles;
    }


    public static void main(String[] args){
        WorkerDAOImpl dao = new WorkerDAOImpl();
        Role cashier=new Role(3,"cashiers");
        Role store=new Role(4,"store");
        Worker ido=new Worker(20,"ido",5000,0,new Date(),1,cashier,2,"cashiers","leumi 5555555");
        //dao.remove(20);
        //ido.addNewRole(store);
        //dao.insert(ido);
        System.out.println(dao.search(20));
    }

}