package com.company.sample.view.employee;

//import com.company.sample.app.EmployeeService;
import com.company.sample.entity.Employee;

import com.company.sample.view.main.MainView;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "employees", layout = MainView.class)
@ViewController("Employee.list")
@ViewDescriptor("employee-list-view.xml")
@LookupComponent("employeesDataGrid")
@DialogMode(width = "64em")
public class EmployeeListView extends StandardListView<Employee> {
//    @Autowired
//    private EmployeeService employeeService;
    @Autowired
    private Dialogs dialogs;

    @Subscribe(id = "countFTEBtn", subject = "clickListener")
    public void onCountFTEBtnClick(final ClickEvent<JmixButton> event) {
//        Integer employeeCount = employeeService.calculateEmployeesBydepartmentForCurrentUser();
//        dialogs.createMessageDialog()
//                .withHeader("Count of Employees")
//                .withText("Count: " + employeeCount)
//                .open();
    }
}