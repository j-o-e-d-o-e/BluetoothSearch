package joedoe.net.bluetoothsearch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class DevicesAdapter extends ArrayAdapter<Device> {
    private static final String TAG = "DevicesAdapter";
    private final Context ctx;
    private final int resource;

    public DevicesAdapter(@NonNull Context context, int resource, List<Device> objects) {
        super(context, resource, objects);
        ctx = context;
        this.resource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d(TAG, "get view");
        String id = Objects.requireNonNull(getItem(position)).getId();
        String name = Objects.requireNonNull(getItem(position)).getName();
        String mac = Objects.requireNonNull(getItem(position)).getMac();

        LayoutInflater inflater = LayoutInflater.from(ctx);
        convertView = inflater.inflate(resource, parent, false);

        TextView tvId = convertView.findViewById(R.id.textView1);
        TextView tvName = convertView.findViewById(R.id.textView2);
        TextView tvMac = convertView.findViewById(R.id.textView3);

        tvId.setText(id);
        tvName.setText(name);
        tvMac.setText(mac);

        return convertView;
    }
}
