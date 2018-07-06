import java.util.*;

class Employee {
    private int employeeId;
    private Date dateFrom;
    private Date dateTo;
    private Map<Integer, Long> timeWorkedWithColleagues;
    private Employee workTimeHolder = null;

    Employee(int employeeId, Date dateFrom, Date dateTo) {
        this.employeeId = employeeId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.timeWorkedWithColleagues = new HashMap<>();
    }

    int getEmployeeId() {
        return employeeId;
    }

    Map<Integer, Long> getTimeWorkedWithColleagues() {
        return this.timeWorkedWithColleagues;
    }

    public Employee getWorkTimeHolder() {
        return workTimeHolder;
    }

    public void setWorkTimeHolder(Employee workTimeHolder) {
        this.workTimeHolder = workTimeHolder;
    }

    private long calculateTimeWorkedWithColleague(Employee colleague) {
        if (this.dateFrom.after(colleague.dateFrom)) {
            if (this.dateTo.after(colleague.dateTo)) {
                return colleague.dateTo.getTime() - this.dateFrom.getTime();
            } else {
                return this.dateTo.getTime() - this.dateFrom.getTime();
            }
        } else {
            if (this.dateTo.after(colleague.dateTo)) {
                return colleague.dateTo.getTime() - colleague.dateFrom.getTime();
            } else {
                return this.dateTo.getTime() - colleague.dateFrom.getTime();
            }
        }
    }

    boolean workedWithColleague(Employee colleague) {
        return !(this.dateTo.before(colleague.dateFrom) || this.dateFrom.after(colleague.dateTo));
    }

    boolean isSamePerson(Employee colleague) {
        return this.getEmployeeId() == colleague.getEmployeeId();
    }

    void addColleagueWorkTime(Employee colleague) {
        Long timeWorkingTogether = calculateTimeWorkedWithColleague(colleague);
        int colleagueId = colleague.getEmployeeId();
        if (colleague.getWorkTimeHolder() != null) {
            if (colleague.getWorkTimeHolder().getTimeWorkedWithColleagues().containsKey(this.employeeId)) {
                colleague.getWorkTimeHolder().getTimeWorkedWithColleagues().put(this.employeeId,
                        colleague.getWorkTimeHolder().getTimeWorkedWithColleagues().get(this.employeeId) +
                                timeWorkingTogether);
            } else if (this.timeWorkedWithColleagues.containsKey(colleagueId)) {
                this.timeWorkedWithColleagues.put(colleagueId, this.timeWorkedWithColleagues.get(colleagueId) +
                        timeWorkingTogether);
            } else {
                this.timeWorkedWithColleagues.put(colleagueId, timeWorkingTogether);
            }
        } else if (colleague.getTimeWorkedWithColleagues().containsKey(this.employeeId)) {
            colleague.getTimeWorkedWithColleagues()
                    .put(this.employeeId, colleague.getTimeWorkedWithColleagues().get(this.employeeId) +
                            timeWorkingTogether);
        } else if (this.timeWorkedWithColleagues.containsKey(colleagueId)) {
            this.timeWorkedWithColleagues.put(colleagueId, this.timeWorkedWithColleagues.get(colleagueId) +
                    timeWorkingTogether);
        } else {
            this.timeWorkedWithColleagues.put(colleagueId, timeWorkingTogether);
        }
    }

    void addSamePersonWorkTimes(Employee colleague, List<Employee> employees,
                                int indexOfColleague, int employeeListSize) {
        colleague.setWorkTimeHolder(this);

        for (int i = 0; i < employeeListSize; i++) {
            if (i == indexOfColleague) {
                continue;
            }
            if (colleague.workedWithColleague(employees.get(i))) {
                colleague.addColleagueWorkTime(employees.get(i));
            }
        }
        for (Map.Entry<Integer, Long> entry : colleague.getTimeWorkedWithColleagues().entrySet()) {
            if (this.timeWorkedWithColleagues.containsKey(entry.getKey())) {
                this.timeWorkedWithColleagues.put(entry.getKey(), this.timeWorkedWithColleagues.get(entry.getKey()) +
                        entry.getValue());
            } else {
                this.timeWorkedWithColleagues.put(entry.getKey(), entry.getValue());
            }
        }
        colleague.getTimeWorkedWithColleagues().clear();
    }
}
