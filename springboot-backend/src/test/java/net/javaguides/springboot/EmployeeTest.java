package net.javaguides.springboot;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import com.mindtree.employeemanagerapp.model.Employee;
import com.mindtree.employeemanagerapp.repository.EmployeeRepository;
import com.mindtree.employeemanagerapp.service.EmployeeService;


@ContextConfiguration(classes = Employee.class)
public class EmployeeTest {

    @Autowired
    private EmployeeRepository employeeRepository;
 
    @Autowired
    private EmployeeService employeeService;
    
    @Test
    @Order(5)
    @Rollback(value = false)
    public void saveEmployeeTest(){
        Employee employee =new Employee();
        employee.setEmailId("abc@gmail.com");
        employee.setFirstName("Abc");
        employee.setLastName("abc");
        
        employeeService.createEmployee(employee);
        Assertions.assertThat(employee.getId()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(4)
    public void getEmployeeTest(){
        Employee employee = employeeService.getEmployeeById(1L);
        Assertions.assertThat(employee.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfEmployeesTest(){
        List<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertThat(employees.size()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void updateEmployeeTest(){
        Employee employee = employeeRepository.findById(1L).get();
        employee.setEmailId("abc@gmail.com");
        Employee employeeUpdated =  employeeRepository.save(employee);
        Assertions.assertThat(employeeUpdated.getEmailId()).isEqualTo("abc@gmail.com");
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void deleteEmployeeTest(){
        Employee employee = employeeRepository.findById(1L).get();
        employeeRepository.delete(employee);
        //employeeRepository.deleteById(1L);
        Employee employee1 = null;
        Optional<Employee> optionalEmployee = employeeRepository.findByEmailId("abc@gmail.com");
        if(optionalEmployee.isPresent()){
            employee1 = optionalEmployee.get();
        }
        Assertions.assertThat(employee1).isNull();
    }
}