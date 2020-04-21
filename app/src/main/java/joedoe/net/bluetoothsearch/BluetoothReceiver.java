package joedoe.net.bluetoothsearch;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Objects;

public class BluetoothReceiver extends BroadcastReceiver {
    private static final String TAG = "BluetoothReceiver";
    private IListener listener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "start discovery");
        String action = intent.getAction();
        Device device = null;
        if (Objects.equals(action, BluetoothDevice.ACTION_FOUND)) {
            BluetoothDevice found = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            Log.d(TAG, "Device infos: " + found.getBluetoothClass());
            device = new Device(found.getBluetoothClass().toString(), found.getName(), found.getAddress());
        }
        listener.onMessage(action, device);
    }

    public void setListener(IListener listener) {
        this.listener = listener;
    }
}
