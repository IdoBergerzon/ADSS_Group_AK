package Domain.HR;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.Date;


public class Worker {
    private int id;
    private String name;
    private int hourly_wage;
    private int monthly_wage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date start_date;

    private Integer direct_manager_ID;
    private Role[] roles;
    private int branch_id;
    private Double days_off;
    private String departement;
    private String bank_details;


    public Worker(){

    }
    public Worker(int id, String name, int monthly_wage, int hourly_wage, Date start_date, Integer direct_manager_ID, Role role, int branch_id, String departement, String bank_details) {
        this.id = id;
        this.name = name;
        this.monthly_wage = monthly_wage;
        this.hourly_wage = hourly_wage;
        this.start_date = start_date;
        this.direct_manager_ID = direct_manager_ID;
        this.branch_id = branch_id;
        this.departement = departement;
        this.roles= new Role[1];
        this.roles[0] = role;
        this.days_off = 0.0;
        this.bank_details=bank_details;


    }




    public void addNewRole(Role role) {
        
        for(int i = 0; i< roles.length; i++) {
            if(roles[i]==role) {
                throw new IllegalArgumentException("Role already exists");
            }

        }
        Role[] newRoles = new Role[roles.length+1];
        System.arraycopy(roles, 0, newRoles, 0, roles.length);
        newRoles[newRoles.length-1] = role;
        roles = newRoles;
    }


    public String toString() {
        String result_str="";
        result_str+="Worker ID: "+this.getId()+"\n";
        result_str+="Worker name: "+this.getName()+"\n";
        result_str+="Monthly wage: "+this.getMonthly_wage()+"$"+"\n";
        result_str+="Hourly wage: "+this.getHourly_wage()+"$"+"\n";
        result_str+="Start date: "+this.getStart_date()+"\n";
        result_str+="Direct manager ID: "+this.getDirect_manager_ID()+"\n";
        result_str+="Roles: "+ roles[0];
        Role[] roles = this.getRoles();
        for(int i=1;i<this.roles.length;i++){
            result_str+= ", " + roles[i];
        }
        result_str+="\n";
        result_str+="branch ID: "+this.getBranch_id()+"\n";
        result_str+="Department: "+this.getDepartement()+"\n";
        result_str+="Days off: "+this.getDays_off()+"Days \n";
        result_str+="Bank Details: "+this.getBank_details()+"\n";

        return result_str;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHourly_wage() {
        return hourly_wage;
    }

    public void setHourly_wage(int hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

    public int getMonthly_wage() {
        return monthly_wage;
    }

    public void setMonthly_wage(int monthly_wage) {
        this.monthly_wage = monthly_wage;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Integer getDirect_manager_ID() {
        return direct_manager_ID;
    }

    public void setDirect_manager_ID(Integer direct_manager_ID) {
        this.direct_manager_ID = direct_manager_ID;
    }

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public Double getDays_off() {
        return days_off;
    }

    public void setDays_off(Double days_off) {
        this.days_off = days_off;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getBank_details() {
        return bank_details;
    }

    public void setBank_details(String bank_details) {
        this.bank_details = bank_details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return id == worker.id && name.equals(worker.name) && Arrays.equals(roles,worker.roles)&& branch_id==worker.branch_id && direct_manager_ID.equals(worker.direct_manager_ID) && departement.equals(worker.departement)&&hourly_wage==worker.hourly_wage&&monthly_wage==worker.monthly_wage&&bank_details.equals(worker.bank_details);

    }


}
