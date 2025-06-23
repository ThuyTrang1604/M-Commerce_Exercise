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

import Models.CallDetail;

public class CallAdapter extends ArrayAdapter<CallDetail> {
    private final LayoutInflater inflater;
    public CallAdapter(@NonNull Context context, @NonNull List<CallDetail> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_call, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CallDetail detail = getItem(position);
        if (detail != null) {
            holder.tvPhone.setText(detail.getPhone());
            holder.tvName.setText(detail.getName());
            if (!detail.isCalled()) {
                convertView.setBackgroundColor(Color.parseColor("#FFF59D")); // yellow
            } else {
                convertView.setBackgroundColor(Color.TRANSPARENT);
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        final TextView tvPhone;
        final TextView tvName;
        ViewHolder(View v) {
            tvPhone = v.findViewById(R.id.tvPhone);
            tvName = v.findViewById(R.id.tvName);
        }
    }
} 