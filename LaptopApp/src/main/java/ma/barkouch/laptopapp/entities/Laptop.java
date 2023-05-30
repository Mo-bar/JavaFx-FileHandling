package ma.barkouch.laptopapp.entities;

import java.io.Serializable;
import java.util.Objects;

public class Laptop implements Serializable {
    private int serial ;
    private String marque;
    private String os;
    private Size size;

    public enum  Size{
        SMALL,
        MEDIUM,
        LARGE
    }

    public Laptop() {
    }

    public Laptop(int serial,String marque, String os, Size size) {
        this.serial = serial;
        this.marque = marque;
        this.os = os;
        this.size = size;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getMarque() {
        return this.marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laptop laptop)) return false;
        return serial == laptop.serial && Objects.equals(marque, laptop.marque) && Objects.equals(os, laptop.os) && size == laptop.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serial, marque, os, size);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "serial=" + serial +
                ", marque='" + marque + '\'' +
                ", os='" + os + '\'' +
                ", size=" + size +
                '}';
    }
}
