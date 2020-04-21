package joedoe.net.bluetoothsearch;

public class Device {
    private static int count = 1;
    private String id;
    private String name;
    private String mac;

    public Device(String name, String mac) {
        this.id = String.valueOf(count++);
        this.name = name;
        this.mac = mac;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public static void resetCount() {
        count = 1;
    }



    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }

}
