package com.dang14.annotations;


import com.dang14.config.RedissionConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 注解开关
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RedissionConfig.class})
@Documented
public @interface EnableRedisson {


}
