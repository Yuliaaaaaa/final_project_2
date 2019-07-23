package filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Yuliia Shcherbakova ON 23.07.2019
 * @project publishing
 */
public class LocalizationFilter implements Filter {
    private String locale;
    private String bundle;
    private static final Logger logger = Logger.getLogger(LocalizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        locale = filterConfig.getInitParameter("locale");
        bundle = filterConfig.getInitParameter("bundle");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String locale = request.getParameter("locale");
        if(locale != null){
            this.locale = locale;
            logger.info("Changed locale to " + locale);
        }
        else {
            locale = (String) request.getSession().getAttribute("locale");
            if(locale != null)
                this.locale = locale;
        }
        String bundle = request.getParameter("bundle");
        if(bundle != null){
            this.bundle = bundle;
            logger.info("Changed bundle to " + bundle);
        }
        else {
            bundle = (String) request.getSession().getAttribute("bundle");
            if(bundle != null)
                this.bundle = bundle;
        }

        request.getSession().setAttribute("locale", this.locale);
        request.getSession().setAttribute("bundle", this.bundle);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
