SET MODE POSTGRESQL;

CREATE TABLE IF NOT EXISTS Person (
  id         INT PRIMARY KEY AUTO_INCREMENT, --todo 18.11.2017: UUID from Java
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255),
  --   professional BOOLEAN         DEFAULT FALSE,
  dob        DATE,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  address    VARCHAR(255),
  telephone  VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS Roles (
  id          INT PRIMARY KEY, --todo 18.11.2017: UUID from Java
  description VARCHAR(255),
  name        VARCHAR(80)
);

CREATE TABLE IF NOT EXISTS User (
  id      INT PRIMARY KEY, --todo 18.11.2017: UUID from Java
  role_id INT,

  FOREIGN KEY (id) REFERENCES Person (id),
  FOREIGN KEY (role_id) REFERENCES Roles (id)
);

INSERT INTO Roles (id, description, name) VALUES (1, '', 'Admin'); --1
INSERT INTO Roles (id, description, name) VALUES (2, '', 'Moderator'); --2
INSERT INTO Roles (id, description, name) VALUES (3, '', 'Gardener'); --3
INSERT INTO Roles (id, description, name) VALUES (4, '', 'Consult'); --4
INSERT INTO Roles (id, description, name) VALUES (5, '', 'Manager'); --5
INSERT INTO Roles (id, description, name) VALUES (6, '', 'Sale'); --6

INSERT INTO Person (first_name, last_name, dob, email, password, address, telephone)
VALUES ('Jose', 'Eglesias', '1980-06-15', 'Jose_Eglesias@mail.es', 'qwerty', 'Franco square, 5/1, 10',
        '+38007654321');
INSERT INTO User (id, role_id) VALUES (1, 1);

INSERT INTO Person (first_name, last_name, dob, email, password, address, telephone)
VALUES ('John', 'Eglesias', '1980-06-15', 'John_Eglesias@mail.es', 'qwerty', 'Franco square, 5/1, 10',
'+38007654321');
INSERT INTO User (id, role_id) VALUES (2, 2);

INSERT INTO Person (first_name, last_name, dob, email, password, address, telephone)
VALUES ('Pit', 'Eglesias', '1980-06-15', 'Pit_Eglesias@mail.es', 'qwerty', 'Franco square, 5/1, 10',
'+38007654321');
INSERT INTO User (id, role_id) VALUES (3, 3);

INSERT INTO Person (first_name, last_name, dob, email, password, address, telephone)
VALUES ('Aisha', 'Eglesias', '1980-06-15', 'Aisha_Eglesias@mail.es', 'qwerty', 'Franco square, 5/1, 10',
'+38007654321');
INSERT INTO User (id, role_id) VALUES (4, 4);

INSERT INTO Person (first_name, last_name, dob, email, password, address, telephone)
VALUES ('Paul', 'Eglesias', '1980-06-15', 'Aisha_Eglesias@mail.es', 'qwerty', 'Franco square, 5/1, 10',
'+38007654321');
INSERT INTO User (id, role_id) VALUES (5, 5);

INSERT INTO Person (first_name, last_name, dob, email, password, address, telephone)
VALUES ('Anton', 'Eglesias', '1980-06-15', 'Aisha_Eglesias@mail.es', 'qwerty', 'Franco square, 5/1, 10',
'+38007654321');
INSERT INTO User (id, role_id) VALUES (6, 6);