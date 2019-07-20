package com.ming.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkClassLoader extends ClassLoader {

    private static final String HOST_NAME = "http://localhost:81";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("Find class from NetworkClassLoader");
        // 从远程下载
        byte[] classData = downloadClassData(name);
        if (null == classData) {
            super.findClass(name);
        } else {
            // convert class byte data to Class<?> object
            return defineClass(name, classData, 0, classData.length);
        }
        return null;
    }

    private byte[] downloadClassData(String name) {
        //download .class from the network
        String path = HOST_NAME + "/" + "java" + "/" + name.replace('.', '/') + ".class";
        try {
            URL url = new URL(path);
            InputStream ins = url.openStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bufferReadNum = 0;
            while ((bufferReadNum = ins.read(buffer)) != -1) {
                // 把下载的二进制数据存入 ByteArrayOutputStream
                baos.write(buffer, 0, bufferReadNum);
            }
            return baos.toByteArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName() {
        System.out.printf("Real NetworkClassLoader\n");
        return "networkClassLoader";
    }
}
