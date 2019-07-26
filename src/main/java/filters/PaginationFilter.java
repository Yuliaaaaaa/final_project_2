package filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Yuliia Shcherbakova ON 26.07.2019
 * @project publishing
 */
public class PaginationFilter implements Filter {
    private int startIndex;
    private static final Logger logger = Logger.getLogger(PaginationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        startIndex = Integer.valueOf(filterConfig.getInitParameter("startIndex"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
/*editions*/
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String startIndex = req.getParameter("startIndex");
        if(startIndex != null)
            this.startIndex = Integer.valueOf(startIndex);
        req.setAttribute("startIndex", this.startIndex);
        logger.info("Pagination filter works");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
