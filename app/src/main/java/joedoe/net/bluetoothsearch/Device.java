package joedoe.net.bluetoothsearch;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Device {
    private static int count = 1;
    private String id;
    private final String name;
    private final String mac;

    public Device(String name, String mac) {
        this.name = name;
        this.mac = mac;
    }

    public Device(String id, String name, String mac) {
        this.id = id;
        this.name = name;
        this.mac = mac;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = String.valueOf(count++);
    }

    public String getName() {
        return name;
    }

    public String getMac() {
        return mac;
    }

    public static void resetCount() {
        count = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(getName(), device.getName()) &&
                Objects.equals(getMac(), device.getMac());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMac());
    }

    @NonNull
    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
