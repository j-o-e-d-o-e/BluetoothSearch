package joedoe.net.bluetoothsearch;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final int REQUEST_ACCESS_COARSE_LOCATION = 1;
    public static final int REQUEST_ENABLE_BLUETOOTH = 11;
    private final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
    private final BluetoothReceiver bReceiver = new BluetoothReceiver();
    @SuppressWarnings("FieldMayBeFinal")
    private List<Device> devices = Bootstrap.createDummies();
    private DevicesAdapter adapter;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate - started");
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        adapter = new DevicesAdapter(this, R.layout.adapter_view_layout, devices);
        listView.setAdapter(adapter);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bAdapter != null && bAdapter.isEnabled()) {
                    if (checkCoarseLocationPermission()) {
//                        Device.resetCount();
                        adapter.clear();
                        bAdapter.startDiscovery();
                        checkBluetoothState();
                    }
                } else {
                    checkBluetoothState();
                }
            }
        });
        bReceiver.setListener(new IListener() {
            @Override
            public void onMessage(String action, Device device) {
                Log.d(TAG, "onMessage: " + action);
                switch (action) {
                    case BluetoothDevice.ACTION_FOUND:
                        if (!devices.contains(device)) {
                            adapter.add(device);
                        }
                        break;
                    case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                        btn.setText("Search");
                        break;
                    case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                        btn.setText("Searching ...");
                        break;
                }
            }
        });
        checkBluetoothState();
        checkCoarseLocationPermission();
    }

    private boolean checkCoarseLocationPermission() {
        Log.d(TAG, "checkCoarseLocationPermission");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_ACCESS_COARSE_LOCATION);
            return false;
        } else {
            return true;
        }
    }

    private void checkBluetoothState() {
        Log.d(TAG, "checkBluetoothState");
        if (bAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported for your device",
                    Toast.LENGTH_SHORT).show();
        } else {
            if (bAdapter.isEnabled()) {
                if (bAdapter.isDiscovering()) {
                    Toast.makeText(this, "Device discovering process ...",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Bluetooth is enabled", Toast.LENGTH_SHORT).show();
                    btn.setEnabled(true);
                }
            } else {
                Toast.makeText(this, "You need to enable Bluetooth",
                        Toast.LENGTH_SHORT).show();
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent, REQUEST_ENABLE_BLUETOOTH);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        registerReceiver(bReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        registerReceiver(bReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED));
        registerReceiver(bReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        unregisterReceiver(bReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult");
        if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
            checkBluetoothState();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult");
        if (requestCode == REQUEST_ACCESS_COARSE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Access coarse location allowed. You can search for devices",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Access coarse location forbidden. You can't search for devices",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
