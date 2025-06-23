package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import Models.Category;
import com.example.phanthithuytrang_k224111470_k22411c.R;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {

    private final LayoutInflater inflater;

    public CategoryAdapter(@NonNull Context context, @NonNull List<Category> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_category, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Category category = getItem(position);
        if (category != null) {
            holder.tvName.setText(category.getName());
        }
        return convertView;
    }

    private static class ViewHolder {
        final TextView tvName;
        ViewHolder(View itemView) {
            tvName = itemView.findViewById(R.id.tvCategoryName);
        }
    }
} 