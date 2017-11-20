package axelbremer.axelbremerpset4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView todoListView;
    TodoDatabase db;
    TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoListView = findViewById(R.id.todoListView);

        db = TodoDatabase.getInstance(getApplicationContext());

        adapter = new TodoAdapter(getApplicationContext(), db.selectAll());

        todoListView.setAdapter(adapter);

        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                CheckBox cb = view.findViewById(R.id.rowCheckBox);
                Log.d("UPDATE", "onItemClick: " + Long.toString(id));
                if(cb.isChecked()) {
                    db.update(id, 0);
                } else {
                    db.update(id, 1);
                }
                updateData();
            }
        });
    }

    public void addItem(View view) {
        EditText et = findViewById(R.id.addText);
        String title = et.getText().toString();

        db.insert(title, 0);
        updateData();
        et.setText("");
    }

    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }
}
