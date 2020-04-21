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
        Device dev = null;
        if (Objects.equals(action, BluetoothDevice.ACTION_FOUND)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            dev = new Device(device.getName(), device.getAddress());
        }
        listener.onMessage(action, dev);
    }

    public void setListener(IListener listener) {
        this.listener = listener;
    }
}
