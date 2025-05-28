package com.example.mynewapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import com.example.mynewapplication.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.txtName.setText(product.getProductName());
        holder.txtCode.setText(product.getProductCode());
        holder.txtPrice.setText("$" + product.getUnitPrice());
        String imageLink = product.getImageLink();
        if (imageLink == null || imageLink.isEmpty()) {
            holder.imgProduct.setImageResource(R.mipmap.ic_launcher); // hoặc ảnh mặc định khác
        } else {
            holder.imgProduct.setImageDrawable(null); // clear ảnh cũ trước khi load mới
            new Thread(() -> {
                try {
                    InputStream in = new URL(imageLink).openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    new Handler(Looper.getMainLooper()).post(() -> holder.imgProduct.setImageBitmap(bitmap));
                } catch (Exception e) {
                    e.printStackTrace();
                    new Handler(Looper.getMainLooper()).post(() -> holder.imgProduct.setImageResource(R.mipmap.ic_launcher));
                }
            }).start();
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtName, txtCode, txtPrice;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtName = itemView.findViewById(R.id.txtProductName);
            txtCode = itemView.findViewById(R.id.txtProductCode);
            txtPrice = itemView.findViewById(R.id.txtUnitPrice);
        }
    }
} 