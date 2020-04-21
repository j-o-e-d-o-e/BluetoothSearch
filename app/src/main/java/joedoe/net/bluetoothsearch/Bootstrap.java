package joedoe.net.bluetoothsearch;

import java.util.ArrayList;
import java.util.List;

public class Bootstrap {

    public static List<Device> createDummies() {
        List<Device> devices = new ArrayList<>();
        devices.add(new Device("A", "123"));
        devices.add(new Device("B", "123"));
        devices.add(new Device("C", "123"));
        devices.add(new Device("D", "123"));
        return devices;
    }
}
