package tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class GetDay extends SimpleTagSupport {
    @Override
    public void doTag() throws IOException {
        JspWriter writer = getJspContext().getOut();
        LocalDate date = LocalDate.now(ZoneId.systemDefault());
        String day = getNumericString(date.getDayOfMonth());
        String month = getNumericString(date.getMonth().getValue());
        writer.print(day +"-"+month+"-"+date.getYear());
    }

    public String getNumericString(int number) {
        return number < 10 ? "0" + number : String.valueOf(number);
    }
}
