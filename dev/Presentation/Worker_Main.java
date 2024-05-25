package Presentation;

import Domain.Worker_Controller;

import java.util.Scanner;

public class Worker_Main {
    private Worker_Controller controller;

    public Worker_Main() {
        this.controller = new Worker_Controller();
    }
    public void displayMyDetails(int worker_id){
        controller.displayMyDetails(worker_id);
    }


}
