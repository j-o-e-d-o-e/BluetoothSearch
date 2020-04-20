package joedoe.net.bluetoothsearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PeopleAdapter extends ArrayAdapter<Person> {
    private static final String TAG = "PeopleAdapter";
    private Context ctx;
    private int resource;

    public PeopleAdapter(@NonNull Context context, int resource, List<Person> objects) {
        super(context, resource, objects);
        ctx = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String birthday = getItem(position).getBirthday();
        String sex = getItem(position).getSex();

        Person person = new Person(name, birthday, sex);

        LayoutInflater inflater = LayoutInflater.from(ctx);
        convertView = inflater.inflate(resource, parent, false);

        TextView tvName = convertView.findViewById(R.id.textView1);
        TextView tvBirthday = convertView.findViewById(R.id.textView2);
        TextView tvSex = convertView.findViewById(R.id.textView3);

        tvName.setText(name);
        tvBirthday.setText(birthday);
        tvSex.setText(sex);

        return convertView;
    }
}
