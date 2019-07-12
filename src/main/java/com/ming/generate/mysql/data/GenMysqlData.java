package com.ming.generate.mysql.data;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class GenMysqlData {

    private static final String EMPLOYEE_FILE_PATH = "C://Tools//Data//employee.txt";
    private static final String DEPARTMENT_FILE_PATH = "C://Tools//Data//department.txt";

    public static void main(String[] args) {
//        generateEmp(EMPLOYEE_FILE_PATH);
        generateDept(DEPARTMENT_FILE_PATH);
    }

    public static void generateEmp(String filePath) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath, true)));
            String emp_name;
            int age;
            int dept_no;
            Random random = new Random();
            for (int i = 0; i < 5000000; i++) {
                emp_name = "Gen" + i;
                age = random.nextInt(30) + 20;
                dept_no = random.nextInt(4) + 1;
                out.write(emp_name + ":" + age + ":" + dept_no + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void generateDept(String filePath) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath, true)));
            String dept_name;
            String source = "Java";
            for (int i = 0; i < 5000000; i++) {
                dept_name = "生产部" + i;
                out.write(dept_name + ":" + source + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
