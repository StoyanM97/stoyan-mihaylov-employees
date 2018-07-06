import java.util.*;

class OutputProcessor {
    static String[][] tableInfo(List<Project> projects) {
        long max = 0L;
        int employee1id = 0;
        int employee2id = 0;
        List<Integer> projects1 = new ArrayList<>();
        for (Project project : projects) {
            for (Employee employee : project.getEmployees()) {
                if (employee.getTimeWorkedWithColleagues().isEmpty()) {
                    continue;
                }
                Optional<Map.Entry<Integer, Long>> entry = employee.getTimeWorkedWithColleagues().entrySet().stream()
                        .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue())).findFirst();
                Long longestTimeWorkingWithColleague = entry.get().getValue();
                if (longestTimeWorkingWithColleague > max) {
                    max = longestTimeWorkingWithColleague;
                    employee1id = employee.getEmployeeId();
                    employee2id = entry.get().getKey();
                    if (projects1.size() > 0) {
                        projects1.clear();
                    }
                    projects1.add(project.getProjectId());
                } else if (((employee.getEmployeeId() == employee1id &&
                        entry.get().getKey() == employee2id) || (employee.getEmployeeId() == employee2id &&
                        entry.get().getKey() == employee1id)) && !projects1.contains(project.getProjectId())) {
                    projects1.add(project.getProjectId());
                }
            }
        }
        StringBuilder projectIds = new StringBuilder();
        for (int n : projects1) {
            projectIds.append(n).append(", ");
        }
        projectIds.delete(projectIds.length() - 2, projectIds.length() - 1);
        String[][] tableInfo = new String[1][4];
        tableInfo[0][0] = String.valueOf(employee1id);
        tableInfo[0][1] = String.valueOf(employee2id);
        tableInfo[0][2] = projectIds.toString();
        tableInfo[0][3] = String.valueOf(max / (60 * 60 * 24 * 1000));
        return tableInfo;
    }
}
