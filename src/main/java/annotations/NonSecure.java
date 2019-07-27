package annotations;

import java.lang.annotation.*;

/**
 * @author Yuliia Shcherbakova ON 20.07.2019
 * @project publishing
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NonSecure {
}
