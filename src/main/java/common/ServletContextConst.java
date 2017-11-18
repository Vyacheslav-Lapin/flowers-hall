package common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.core.config.Order;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.function.Consumer;
import java.util.function.Supplier;

@WebListener
@Order(1)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServletContextConst<T> implements Supplier<T>, Consumer<T>, ServletContextListener {

    public static final ServletContextConst<DataSource> DBCP =
            new ServletContextConst<>("dbcp");
    private static ServletContext SERVLET_CONTEXT;
    private final String name;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SERVLET_CONTEXT = sce.getServletContext();
    }

    @Override
    public T get() {
        //noinspection unchecked
        return (T) SERVLET_CONTEXT.getAttribute(name);
    }

    @Override
    public void accept(T t) {
        SERVLET_CONTEXT.setAttribute(name, t);
    }
}
