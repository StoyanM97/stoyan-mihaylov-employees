import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Project {
    private int projectId;
    private List<Employee> employees;

    Project(int projectId) {
        this.projectId = projectId;
        this.employees = new ArrayList<>();
    }

    int getProjectId() {
        return projectId;
    }

    List<Employee> getEmployees() {
        return Collections.unmodifiableList(this.employees);
    }

    void addEmployee(Employee employee) {
        this.employees.add(employee);
    }
}
