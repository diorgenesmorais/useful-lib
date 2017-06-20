package com.dms.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code ToDo} is useful for documentation of left to do.
 * 
 * @author Diorgenes Morais
 * @version 1.0.0
 */
@Documented
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)
public @interface ToDo {

	enum Severity {
		CRITICAL, IMPORTANT, TRIVIAL, DOCUMENTATION
	};

	Severity severity() default Severity.IMPORTANT;

	String item();

	String assignedTo();
}
