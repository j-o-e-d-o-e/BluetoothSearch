package joedoe.net.bluetoothsearch;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    List<Person> people = createPeople();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Started");
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        PeopleAdapter adapter = new PeopleAdapter(this, R.layout.adapter_view_layout, people);
        listView.setAdapter(adapter);
    }

    private List<Person> createPeople() {
        return Arrays.asList(
                new Person("Joe", "01-03-1982", "male"),
                new Person("Mary", "02-03-1982", "female"),
                new Person("John", "03-03-1982", "male"),
                new Person("Olga1", "04-01-1982", "female"),
                new Person("Olga2", "04-02-1982", "female"),
                new Person("Olga3", "04-03-1982", "female"),
                new Person("Olga4", "04-04-1982", "female"),
                new Person("Olga5", "04-05-1982", "female")
        );
    }
}
