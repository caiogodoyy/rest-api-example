package com.alura.api.degree.domain.user;

import com.alura.api.degree.dto.user.UserRegisterData;

import jakarta.persistence.Entity;
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
@Table(name = "users")
@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean active;
    private String username;
    private String password;
    private String email;
    private String name;

    public User(UserRegisterData data) {
        this.active = true;
        this.username = data.username();
        this.password = data.password();
        this.email = data.email();
        this.name = data.name();
    }

    public void deactivate() {
        this.active = false;
    }

}
