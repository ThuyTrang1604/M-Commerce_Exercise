package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.phanthithuytrang_k224111470_k22411c.R;

import java.util.List;

import Models.Task;

public class TaskAdapter extends ArrayAdapter<Task> {

    private final LayoutInflater inflater;

    public TaskAdapter(@NonNull Context context, @NonNull List<Task> tasks) {
        super(context, 0, tasks);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_task, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Task task = getItem(position);
        if (task != null) {
            holder.tvTitle.setText(task.getTitle());
            holder.tvSub.setText(task.getDateAssigned());
            // Set background color if completed
            if (task.isCompleted()) {
                convertView.setBackgroundColor(Color.parseColor("#A5D6A7")); // light green
            } else {
                convertView.setBackgroundColor(Color.TRANSPARENT);
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        final TextView tvTitle;
        final TextView tvSub;
        ViewHolder(View v) {
            tvTitle = v.findViewById(R.id.tvTaskTitle);
            tvSub = v.findViewById(R.id.tvTaskSub);
        }
    }
} 