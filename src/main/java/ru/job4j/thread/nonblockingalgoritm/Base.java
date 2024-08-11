package ru.job4j.thread.nonblockingalgoritm;

import java.util.Objects;

public class Base {

    private int id;

    private int version;

    private String name;

    public Base(int id, String name, int version) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return version == base.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(version);
    }
}