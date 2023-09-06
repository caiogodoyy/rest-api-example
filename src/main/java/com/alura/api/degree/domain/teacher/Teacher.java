package com.alura.api.degree.domain.teacher;

import com.alura.api.degree.domain.address.Address;
import com.alura.api.degree.dto.teacher.Gender;
import com.alura.api.degree.dto.teacher.TeacherRegisterData;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "teachers")
@Entity(name = "Teacher")
public class Teacher {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean active;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String department;
    private Float salary;
    private String phone;

    @Embedded
    private Address address;

    public Teacher(TeacherRegisterData data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.gender = Gender.fromValue(data.gender());
        this.department = data.department();
        this.salary = data.salary();
        this.phone = data.phone();
        this.address = new Address(data.address());
    }

    public void deactivate() {
        this.active = false;
    }

}
