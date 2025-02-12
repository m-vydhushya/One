package com.spring.one.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
//@Data
//@Getter
//@Setter
@Builder
@ToString
public class EmployeeDTO {
    private Long id;
    private String name;
    private int age;
    private int salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}


//The BO is used in the Service layer for business logic. 
//It’s an internal representation of the data after validation.
