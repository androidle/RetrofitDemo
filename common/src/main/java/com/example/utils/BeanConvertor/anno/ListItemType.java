package com.example.utils.beanconvertor.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * list对象拷贝时需要指定每个用item类型
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ListItemType {

	Class instantiate();
}
