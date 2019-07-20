package com.ming.reflection;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.lang.reflect.*;

public class JReflection {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Class<?> userBeanClass = UserBean.class;
        System.out.println("打印成员变量");
        Field[] fields = userBeanClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldString = "";
            fieldString += Modifier.toString(field.getModifiers());
            fieldString += " " + field.getType().getSimpleName();
            fieldString += " " + field.getName();
            System.out.println(fieldString);
        }

        System.out.println("打印成员方法");
        Method[] methods = userBeanClass.getDeclaredMethods();
        for (Method method : methods) {
            String methodString = "";
            methodString += Modifier.toString(method.getModifiers());
            methodString += " " + method.getReturnType().getSimpleName();
            methodString += " " + method.getName() + "(";
            Class[] parameters = method.getParameterTypes();
            for (Class parameter : parameters) {
                methodString += parameter.getSimpleName();
            }
            methodString += ")";
            System.out.println(methodString);
        }

        System.out.println("打印构造方法");
        Constructor[] constructors = userBeanClass.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            String constructorString = "";
            constructorString += Modifier.toString(constructor.getModifiers()) + " ";
            constructorString += constructor.getName() + "(";
            Class[] paramenters = constructor.getParameterTypes();
            for (Class parameter : paramenters) {
                constructorString += parameter.getSimpleName() + ",";
            }
            constructorString = constructorString.substring(0, constructorString.length()-1);
            constructorString += ")";
            System.out.println(constructorString);
        }

        System.out.println("调用 Class 内部的用 @Invoke 修饰的方法");
        Method[] invokeMethods = userBeanClass.getDeclaredMethods();
        for (Method method : invokeMethods) {
            if (method.isAnnotationPresent(Invoke.class)) {
                if (Modifier.isStatic(method.getModifiers())) {
                    method.invoke(null, "Allen");
                } else {
                    Class[] params = {String.class, int.class};
                    Constructor constructor = userBeanClass.getDeclaredConstructor(params);
                    Object userBean = constructor.newInstance("Allen", 19);
                    if (Modifier.isPrivate(method.getModifiers())) {
                        method.setAccessible(true);
                    }
                    method.invoke(userBean);
                }
            }
        }
    }
}
