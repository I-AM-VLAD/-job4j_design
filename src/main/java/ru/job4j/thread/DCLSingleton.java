package ru.job4j.thread;

public class DCLSingleton {
    private static volatile DCLSingleton instance;

    public static DCLSingleton getInstance() {
        DCLSingleton localInstance = instance;
        if (localInstance == null) {
            synchronized (DCLSingleton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = new DCLSingleton();
                    instance = localInstance;
                }
            }
        }
        return localInstance;
    }

    private DCLSingleton() {
    }
}