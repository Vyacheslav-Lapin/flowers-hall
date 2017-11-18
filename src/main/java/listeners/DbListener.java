package listeners;

import common.ServletContextConst;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.logging.log4j.core.config.Order;

import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Order(2)
@WebListener
public class DbListener implements ServletContextListener {

    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent sce) {
        val initialContext = new InitialContext();
        val dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/h2");

        assert dataSource != null: "Data source not found!";

        try (val connection = dataSource.getConnection();
             val statement = connection.createStatement()) {
            statement.executeUpdate(getInitSql("/h2.sql"));
        }

        ServletContextConst.DBCP.accept(dataSource);
    }

    @SneakyThrows
    private String getInitSql(String name) {
        try (val scanner = new Scanner(getClass().getResourceAsStream(name))
                .useDelimiter(System.lineSeparator());
             Stream<String> lines = StreamSupport.stream(
                     Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED), false)) {

            return lines.collect(Collectors.joining());
        }
    }
}
