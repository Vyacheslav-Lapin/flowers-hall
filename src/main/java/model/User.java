package model;

import lombok.Value;

import java.time.LocalDate;

@Value
public class User {
    private int id; //    id         INT PRIMARY KEY AUTO_INCREMENT,

    private String firstName; //    first_name VARCHAR(255) NOT NULL,

    private String lastName; //    last_name  VARCHAR(255),

    private LocalDate dob; //    dob        DATE,

    private String email; //    email      VARCHAR(255) NOT NULL,

    private String password; //    password   VARCHAR(255) NOT NULL,

    private String address; //    address    VARCHAR(255),

    private String telephone; //    telephone  VARCHAR(15)

    private Role role;
}
