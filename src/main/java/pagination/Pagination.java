package pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public interface Pagination<T> {

    default List<T> getElements(HttpServletRequest req, List<T> elements) {
        int startIndex = (int) req.getAttribute("startIndex");
        if (startIndex == 0)
            req.setAttribute("start", true);
        if (elements.size() - startIndex <= 10)
            req.setAttribute("end", true);
        elements = elements.stream()
                .skip(startIndex)
                .limit(10)
                .collect(Collectors.toList());
        return elements;
    }
}
