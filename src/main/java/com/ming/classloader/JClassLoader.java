package com.ming.classloader;

public class JClassLoader {

    public static final String CLASS_FULL_PATH = "com.ming.classloader.MusicPlayer";
    public static final String CLASS_FULL_PATH_NETWORK = "MusicPlayer";

    public static void main(String[] args) throws ClassNotFoundException {
//        loadClass();
//        printParent();
        loadClassFromNetworkClassLoader();
    }

    private static void loadClass() throws ClassNotFoundException {
        Class<?> clazz = Class.forName(CLASS_FULL_PATH);
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.printf("The current ClassLoader is %s\n", classLoader.getClass().getSimpleName());
    }

    private static void loadClassFromNetworkClassLoader() throws ClassNotFoundException {
        String className = CLASS_FULL_PATH;
        ClassLoader networkClassLoader = new NetworkClassLoader();
        Class<?> clazz = networkClassLoader.loadClass(className);
        System.out.printf("Load the class: %s\n", clazz.getName());
        System.out.printf("Current ClassLoader is: %s\n", clazz.getClassLoader().getClass().getSimpleName());
        try {
            clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void printParent() throws ClassNotFoundException {
        Class<?> clazz = Class.forName(CLASS_FULL_PATH);
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.printf("Current ClassLoader is %s\n", classLoader.getClass().getSimpleName());
        while (classLoader.getParent() != null) {
            classLoader = classLoader.getParent();
            System.out.printf("Parent ClassLoader is %s\n", classLoader.getClass().getSimpleName());
        }
    }
}
