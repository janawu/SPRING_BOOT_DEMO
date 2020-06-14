package com.jana.demo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	
	// --- check before insert/update DB 
	boolean isPrimaryKey() default false; // can not duplicate with existed data
	
	// --- GUI type
	boolean isOptionList() default false;
	boolean isModifiable() default true;
	String[] optionList();
	
}
