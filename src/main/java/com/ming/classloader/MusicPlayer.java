package com.ming.classloader;

public class MusicPlayer {
    static {
        System.out.println("I am static block from MusicPlayer");
    }

    public void print() {
        System.out.println("Hi I am Music Player");
    }
}
