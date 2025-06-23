package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import Models.Product;
import com.example.phanthithuytrang_k224111470_k22411c.R;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private final LayoutInflater inflater;

    public ProductAdapter(@NonNull Context context, @NonNull List<Product> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_product, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product product = getItem(position);
        if (product != null) {
            holder.tvName.setText(product.getName());
            holder.tvPrice.setText(String.format("%,.0f", product.getPrice()));
            loadImage(product.getImageLink(), holder.img);
        }
        return convertView;
    }

    private static class ViewHolder {
        final TextView tvName;
        final TextView tvPrice;
        final android.widget.ImageView img;
        ViewHolder(View itemView) {
            tvName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvProductPrice);
            img = itemView.findViewById(R.id.imgProduct);
        }
    }

    // --- Image loading with simple caching ---
    private static final android.util.LruCache<String, android.graphics.Bitmap> CACHE = new android.util.LruCache<>(50);

    private void loadImage(String url, android.widget.ImageView target) {
        if (url == null || url.isEmpty()) {
            target.setImageResource(android.R.drawable.ic_menu_report_image);
            return;
        }
        android.graphics.Bitmap cached = CACHE.get(url);
        if (cached != null) {
            target.setImageBitmap(cached);
            return;
        }

        // Download in background thread
        new android.os.AsyncTask<String, Void, android.graphics.Bitmap>() {
            @Override
            protected android.graphics.Bitmap doInBackground(String... strings) {
                try {
                    java.net.URL u = new java.net.URL(strings[0]);
                    java.net.HttpURLConnection conn = (java.net.HttpURLConnection) u.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    java.io.InputStream is = conn.getInputStream();
                    android.graphics.Bitmap bmp = android.graphics.BitmapFactory.decodeStream(is);
                    is.close();
                    return bmp;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(android.graphics.Bitmap bitmap) {
                if (bitmap != null) {
                    CACHE.put(url, bitmap);
                    target.setImageBitmap(bitmap);
                } else {
                    target.setImageResource(android.R.drawable.ic_menu_report_image);
                }
            }
        }.execute(url);
    }
} 