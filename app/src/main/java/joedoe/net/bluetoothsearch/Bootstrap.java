package joedoe.net.bluetoothsearch;

import java.util.ArrayList;
import java.util.List;

public class Bootstrap {

    public static List<Device> createDummies() {
        List<Device> devices = new ArrayList<>();
        devices.add(new Device("1", "A", "123"));
        devices.add(new Device("2", "B", "234"));
        devices.add(new Device("3", "C", "345"));
        devices.add(new Device("4", "D", "456"));
        return devices;
    }
}
