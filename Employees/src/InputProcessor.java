import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class InputProcessor {
    static List<String> readFile(File file) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            System.out.println("Invalid directory");
        }
        return strings;
    }

    static List<Project> createListOfProjects(List<String> employeesInfo) {
        List<Project> projects = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        for (String string : employeesInfo) {
            String[] employeeInfo = string.split(", ");
            if (projects
                    .stream()
                    .anyMatch(p -> p.getProjectId() == Integer.parseInt(employeeInfo[1]))) {
                for (Project p : projects) {
                    if (p.getProjectId() == Integer.parseInt(employeeInfo[1])) {
                        try {
                            Employee employee = new Employee(Integer.parseInt(employeeInfo[0]),
                                    sdf.parse(employeeInfo[2]),
                                    employeeInfo[3].equals("NULL") ?
                                            Date.from(LocalDate.now()
                                                    .atStartOfDay()
                                                    .atZone(ZoneId.systemDefault()).toInstant())
                                            : sdf.parse(employeeInfo[3]));
                            p.addEmployee(employee);

                        } catch (ParseException e) {
                            System.out.println("Invalid date format");
                        }
                        break;
                    }
                }
            } else {
                Project project = new Project(Integer.parseInt(employeeInfo[1]));
                try {
                    Employee employee = new Employee(Integer.parseInt(employeeInfo[0]),
                            sdf.parse(employeeInfo[2]),
                            employeeInfo[3].equals("NULL") ?
                                    Date.from(LocalDate.now()
                                            .atStartOfDay()
                                            .atZone(ZoneId.systemDefault()).toInstant())
                                    : sdf.parse(employeeInfo[3]));
                    project.addEmployee(employee);
                    projects.add(project);
                } catch (ParseException e) {
                    System.out.println("Invalid date format");
                }
            }
        }
        return projects;
    }
}
