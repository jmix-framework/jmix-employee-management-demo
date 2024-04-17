package com.company.sample.app;

import com.company.sample.entity.Department;
import com.company.sample.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeService {
    private final DataManager dataManager;
    private final CurrentAuthentication currentAuthentication;

    public EmployeeService(DataManager dataManager, CurrentAuthentication currentAuthentication) {
        this.dataManager = dataManager;
        this.currentAuthentication = currentAuthentication;
    }

    public Integer calculateEmployeesByDepartment(Department department) {
        if(department == null) {
            return 0;
        }
        return dataManager.loadValue("select count(e) from Employee e where e.department = :department", Integer.class)
                .parameter("department", department)
                .one();
    }

    public Integer calculateEmployeesByDepartmentForCurrentUser() {
        UserDetails userDetails = currentAuthentication.getUser();
        if (userDetails instanceof User user) {
            final Optional<Department> optionalDepartment = dataManager.load(Department.class)
                    .query("select d from Department d where d.responsibleId = :responsibleId")
                    .parameter("responsibleId", user.getId())
                    .optional();
            return optionalDepartment.map(this::calculateEmployeesByDepartment).orElse(null);
        }
        return null;
    }
}