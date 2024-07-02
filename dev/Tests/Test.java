package Tests;

import Data.*;
import Domain.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Test {
    public void testInsertWorker() {
        WorkerDAOImpl workerDAO = new WorkerDAOImpl();
        RoleDAOImpl roleDAO = new RoleDAOImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        HR_Controller hr=new HR_Controller();

        //creating Worker//
        String newWorkerDetails = "";
        newWorkerDetails="20"+",ido"+",55"+",55"+",11"+",2"+",cashiers"+",2"+",leumi: 5555555";
        hr.Add_New_Worker(newWorkerDetails);

        //search the worker that insert//
        JsonNode JN=workerDAO.search(20);
        ((ObjectNode) JN).remove("active");
        Worker to_check=JsonNodeConverter.fromJsonNode(JN,Worker.class);
        System.out.println(to_check);

        //creating the expected worker//
        Role role=JsonNodeConverter.fromJsonNode(roleDAO.search(11),Role.class);
        Worker ido=new Worker(20,"ido",55,55,new Date(),2,role,2,"cashiers","leumi: 5555555");
        workerDAO.remove(20);
        System.out.println(ido);

        //call to test func//
        assertWorker(ido,to_check);


    }
    public void testUpdateWorker() {
    WorkerRepository workers=WorkerRepository.getInstance();
    RoleRepository roles=RoleRepository.getInstance();
    //create new worker//
    Role role=new Role(999,"cash counter");
    Worker ido=new Worker(21,"ido",55,55,new Date(),2,role,2,"cashiers","leumi: 5555555");
    //insert worker to repository and database//
    workers.add(ido);
    //create worker that represent the updated worker//
    Worker updated=new Worker(21,"moshe",55,55,new Date(),2,role,2,"cashiers","leumi: 5555555");
    //update exist
    workers.updateWorker(updated);

    Worker to_check=workers.get(21);
    workers.remove(21);
    assertWorker(updated,to_check);




    }

    private void assertWorker(Worker worker, Worker expected){
        if (worker == null) {
            throw new AssertionError("Worker is null");
        } else if (expected==null) {
            throw new AssertionError("Worker is null");
        }
        else if(worker.equals(expected)){
            System.out.println("insert worker test passed");
        }
        else System.out.println("insert worker test doesn't passed");
    }







    public static void main(String[] args) {
        Test test = new Test();
        test.testInsertWorker();
        test.testUpdateWorker();
    }
}
