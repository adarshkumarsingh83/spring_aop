/*
 * Copyright (c) 2015 Espark And ©Adarsh Development Services @copyright All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Espark nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.espark.adarsh;


import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@ContextHierarchy({@ContextConfiguration("classpath:/configuration/applicationContext.xml")})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringAopTest {

    @Autowired(required = true)
    private EmployeeService employeeService;

    @Test
    public void saveEmployee() {
        final Boolean result = this.employeeService.saveEmployee(new Employee(1, "Adarsh kumar", "adarsh@kumar"));
        Assert.isTrue(result, "Employee Not Saved ");
    }


    @Test
    public void updateEmployee() {
        final Employee employee = this.employeeService.updateEmployee(new Employee(1, "Adarsh kumar Singh", "adarsh@kumar"));
        Assert.isTrue((employee != null), "Employee Not Updated ");
    }


    @Test
    public void saveEmployees() {
        final List<Employee> employeeList = new ArrayList<Employee>() {
            {
                add(new Employee(1, "Adarsh kumar", "adarsh@kumar"));
                add(new Employee(2, "Amit kumar", "amit@kumar"));
                add(new Employee(3, "Radha Singh", "radha@singh"));
                add(new Employee(4, "Sonu kumar", "sonu@kumar"));
            }
        };
        final Boolean result = this.employeeService.saveEmployees(employeeList);
        Assert.isTrue(result, "Employees Not Saved ");
    }

    @Test
    public void deleteEmployee() {
        final Boolean result = this.employeeService.saveEmployee(new Employee(1, "Adarsh kumar", "adarsh@kumar"));
        final Employee employee = this.employeeService.deleteEmployee(1);
        Assert.isTrue((employee != null), "Employee Not Deleted ");
    }


    @Test
    public void selectEmployees() {
        final List<Employee> employeeList = new ArrayList<Employee>() {
            {
                add(new Employee(1, "Adarsh kumar", "adarsh@kumar"));
                add(new Employee(2, "Amit kumar", "amit@kumar"));
                add(new Employee(3, "Radha Singh", "radha@singh"));
                add(new Employee(4, "Sonu kumar", "sonu@kumar"));
            }
        };
        this.employeeService.saveEmployees(employeeList);
        final List<Employee> employeeListSelected = this.employeeService.selectEmployee();
        Assert.notNull(employeeListSelected, "Employee  List is Null ");
        Assert.isTrue((employeeListSelected.size() > 0), "Employee Not Selected ");
    }

    @Test
    public void selectEmployee() {
        final Boolean result = this.employeeService.saveEmployee(new Employee(1, "Adarsh kumar", "adarsh@kumar"));
        final Employee employee = this.employeeService.selectEmployee(1);
        Assert.isTrue((employee != null), "Employee Not Selected ");
    }
}