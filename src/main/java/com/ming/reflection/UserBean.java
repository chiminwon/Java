package com.ming.reflection;

public class UserBean {

    public String userName;

    private int userId;

    public UserBean(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Invoke
    public static void staticMethod(String devName) {
        System.out.printf("Hi %s, I'm a static method\n", devName);
    }
    @Invoke
    public void publicMethod() {
        System.out.println("I'm a public method");
    }
    @Invoke
    private void privateMethod() {
        System.out.println("I'm a private method");
    }
}
