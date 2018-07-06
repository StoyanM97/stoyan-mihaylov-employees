import java.util.List;

public class Main {
    public static void main(String[] args) {
        UI ui = new UI();

        List<Project> projects = InputProcessor.createListOfProjects(InputProcessor.readFile(ui.getFile()));

        for (Project project : projects) {
            int employeeListSize = project.getEmployees().size();
            for (Employee employee : project.getEmployees()) {
                for (int i = project.getEmployees().indexOf(employee) + 1; i < employeeListSize; i++) {
                    if (employee.workedWithColleague(project.getEmployees().get(i))) {
                        employee.addColleagueWorkTime(project.getEmployees().get(i));
                        continue;
                    }
                    if (employee.isSamePerson(project.getEmployees().get(i))) {
                        employee.addSamePersonWorkTimes(project.getEmployees().get(i), project.getEmployees(),
                                i, employeeListSize);
                    }
                }
            }
        }
        ui.setEmployeesInfo(OutputProcessor.tableInfo(projects));
        ui.addTable(ui.getEmployeesInfo());
    }
}
