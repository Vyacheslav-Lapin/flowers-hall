package listeners;

import common.JdbcDao;
import common.ServletContextConst;
import io.vavr.CheckedFunction0;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.logging.log4j.core.config.Order;

import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.function.Supplier;

@Order(2)
@WebListener
public class DbListener implements ServletContextListener {

    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent sce) {
        val initialContext = new InitialContext();
        val dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/h2");

        assert dataSource != null: "Data source not found!";

        CheckedFunction0<Connection> getConnection = dataSource::getConnection;
        Supplier<Connection> unchecked = getConnection.unchecked();
        JdbcDao connectionConsumer = unchecked::get;

        connectionConsumer.executeSql("/h2.sql");

        ServletContextConst.DBCP.accept(connectionConsumer);
    }
}
