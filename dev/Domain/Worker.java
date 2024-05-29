package Domain;
import java.util.Date;


public class Worker {
    private int id;
    private String name;
    private int hourly_wage;
    private int monthly_wage;
    private Date start_date;
    private Integer direct_manager_ID;
    private Role[] roles;
    private Branch work_branch;
    private int days_off;
    private String departement;

    public Worker(int id, String name, int monthly_wage, int hourly_wage, Date start_date, Integer direct_manager_ID,Role role, Branch work_branch, String departement) {
        this.id = id;
        this.name = name;
        this.monthly_wage = monthly_wage;
        this.hourly_wage = hourly_wage;
        this.start_date = start_date;
        this.direct_manager_ID = direct_manager_ID;
        this.work_branch = work_branch;
        this.departement = departement;
        this.roles= new Role[1];
        this.roles[0] = role;
        this.days_off = 0;


    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHourly_wage() {
        return hourly_wage;
    }

    public int getMonthly_wage() {
        return monthly_wage;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }


    public Date getStart_Date() {
        return start_date;
    }

    public Integer getDirect_manager() {
        return direct_manager_ID;
    }

    public Role[] getRoles() {
        return roles;
    }

    public Branch getWork_branch() {
        return work_branch;
    }

    public int getDays_off() {
        return days_off;
    }

    public String getDepartement() {
        return departement;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHourly_wage(int hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

    public void setMonthly_wage(int monthly_wage) {
        this.monthly_wage = monthly_wage;
    }

    public void setDirect_manager(int direct_manager_ID) {
        this.direct_manager_ID = direct_manager_ID;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
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

    public void setWork_branch(Branch work_branch) {
        this.work_branch = work_branch;
    }

    public String toString() {
        String result_str="";
        result_str+="Worker ID: "+this.getId()+"\n";
        result_str+="Worker name: "+this.getName()+"\n";
        result_str+="Monthly wage: "+this.getMonthly_wage()+"$"+"\n";
        result_str+="Hourly wage: "+this.getHourly_wage()+"$"+"\n";
        result_str+="Start date: "+this.getStart_Date()+"\n";
        result_str+="Direct manager ID: "+this.getDirect_manager()+"\n";
        result_str+="Roles: "+ roles[0];
        Role[] roles = this.getRoles();
        for(int i=1;i<this.roles.length;i++){
            result_str+= ", " + roles[i];
        }
        result_str+="\n";
        result_str+=this.getWork_branch()+"\n";
        result_str+="Department: "+this.getDepartement()+"\n";

        return result_str;
    }

}
