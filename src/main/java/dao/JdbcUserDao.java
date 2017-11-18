package dao;

import common.JdbcDao;
import lombok.RequiredArgsConstructor;
import model.Role;
import model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
public class JdbcUserDao {

    private final JdbcDao jdbcDao;

    public User create(String firstName,
                       String lastName,
                       LocalDate dob,
                       String email,
                       String password,
                       String address,
                       String telephone,
                       String roleName) {

        User user = jdbcDao.mapPreparedStatementFlagged(preparedStatement -> {
                    preparedStatement.executeUpdate();
                    try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                        return !rs.next() ? null : new User(
                                rs.getInt(1),
                                firstName,
                                lastName,
                                dob,
                                email,
                                password,
                                address,
                                telephone,
                                Role.valueOf(roleName));
                    }

                },
                "INSERT INTO Person (first_name, last_name, dob, email, password, address, telephone) VALUES (?,?,?,?,?,?,?)",
                firstName, lastName, Date.valueOf(dob), email, password, address, telephone);

        jdbcDao.withPreparedStatement(PreparedStatement::executeUpdate,
                "INSERT INTO User (id, role_id) VALUES (?,?);",
                user.getId(), user.getRole().getId());

        return user;
    }

    public Optional<User> get(int id) {
//        return getAll().stream().filter(user -> user.getId() == id).findAny();
        return Optional.ofNullable(
                jdbcDao.mapResultSet(rs -> !rs.next() ? null : new User(
                                rs.getInt("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getDate("dob").toLocalDate(),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("address"),
                                rs.getString("telephone"),
                                Role.getById(rs.getInt("role_id"))),
                        "SELECT first_name, last_name, dob, email, password, address, telephone, role_id FROM User u, Person p WHERE u.id = ? AND u.id = p.id",
                        id));
    }

    public List<User> getAll() {
//        return Collections.emptyList();
        List<User> users = new ArrayList<>();

        jdbcDao.withResultSet(rs -> {
                    while (rs.next())
                        users.add(new User(
                                rs.getInt("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getDate("dob").toLocalDate(),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("address"),
                                rs.getString("telephone"),
                                Role.getById(rs.getInt("role_id"))));
                },
                "SELECT id, first_name, last_name, dob, email, password, address, telephone, role_id FROM User u, Person p WHERE u.id = p.id");

        return users;
    }

    public Optional<User> update(int id,
                                 String firstName,
                                 String lastName,
                                 LocalDate dob,
                                 String email,
                                 String password,
                                 String address,
                                 String telephone) {

        Map<String, Object> params = new LinkedHashMap<>();
        if (firstName != null)
            params.put("first_name", firstName);

        if (lastName != null)
            params.put("last_name", lastName);

        if (dob != null)
            params.put("dob", dob);

        if (email != null)
            params.put("email", email);

        if (password != null)
            params.put("password", password);

        if (address != null)
            params.put("address", address);

        if (telephone != null)
            params.put("telephone", telephone);

        StringBuilder sql = new StringBuilder("UPDATE Person SET ");
        for (Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Object> param = iterator.next();
            sql.append(param.getKey())
                    .append(" = ?");
            if (iterator.hasNext())
                sql.append(", ");
            else
                sql.append(" ");

        }

        sql.append(" WHERE id = ?");

        List<Object> objects = new ArrayList<>(params.values());
        objects.add(id);

        jdbcDao.withPreparedStatement(PreparedStatement::executeUpdate,
                sql.toString(), objects.toArray());

        return get(id);
    }
}
