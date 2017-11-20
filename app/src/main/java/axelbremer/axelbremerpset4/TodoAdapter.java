package axelbremer.axelbremerpset4;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ResourceCursorAdapter;

/**
 * Created by axel on 20-11-17.
 */

public class TodoAdapter extends ResourceCursorAdapter {
    public TodoAdapter(Context context, Cursor cursor) {
        super(context, R.layout.row_todo, cursor, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int titleIndex = cursor.getColumnIndex("title");
        int completedIndex = cursor.getColumnIndex("completed");
        String title = cursor.getString(titleIndex);
        Boolean completed;

        if(cursor.getInt(completedIndex) == 1) {
            completed = true;
        } else {
            completed = false;
        }

        Log.d("LOAD", "title: " + title + " completed: " + completed);

        CheckBox rowCheckBox = view.findViewById(R.id.rowCheckBox);

        rowCheckBox.setText(title);
        rowCheckBox.setChecked(completed);
    }
}
