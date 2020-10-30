package com.perdo.sqliteinventory;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ProductViewer>{


    List<POJO> productDetails;
    Context context;
    OpenHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public RecycleAdapter(List<POJO> productDetails) {
        this.productDetails = productDetails;
    }

    @NonNull
    @Override
    public ProductViewer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {





        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View iteView = inflater.inflate(R.layout.product_item, parent, false);
        ProductViewer viewHolder = new ProductViewer(iteView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewer holder, final int position) {
       final POJO pojo=productDetails.get(position);

       holder.pId.setText("ID: " + pojo.getP_id());
       holder.pCode.setText("Code :"+ pojo.getP_code());
       holder.pDes.setText("Des: "+ pojo.getP_des());
       holder.pName.setText("Name: "+ pojo.getP_name());
       holder.pQuantity.setText("Quantity: "+ pojo.getP_quantity());
       holder.txtDelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final int pId = pojo.getP_id();
               dbHelper = new OpenHelper(context);
               sqLiteDatabase = dbHelper.getWritableDatabase();
               sqLiteDatabase.delete(DatabaseInfo.TABLE_NAME,DatabaseInfo._ID+ " = " + pId,null);
               notifyItemRangeChanged(position,productDetails.size());
               productDetails.remove(position);
               notifyItemRemoved(position);
               sqLiteDatabase.close();
           }
       });

       holder.txtUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent = new Intent(context, UpdatingProduct.class);
               intent.putExtra("prodId", pojo.getP_id());
               context.startActivity(intent);

           }
       });
    }//onBindViewHolder

    @Override
    public int getItemCount() {
        return productDetails.size();
    }

    public class ProductViewer extends RecyclerView.ViewHolder {


        TextView pId;
        TextView pCode;
        TextView pName;
        TextView pDes;
        TextView pQuantity;
        TextView txtDelete;
        TextView txtUpdate;


        public ProductViewer(View itemView) {
            super(itemView);

            pId = itemView.findViewById(R.id.txtId);
            pCode =  itemView.findViewById(R.id.txtCode);
            pName = itemView.findViewById(R.id.txtName);
            pDes = itemView.findViewById(R.id.txtDes);
            pQuantity = itemView.findViewById(R.id.txtQuantity);
            txtDelete=itemView.findViewById(R.id.txtDelete);
            txtUpdate=itemView.findViewById(R.id.txtUpdate);

        }


    }

    public void deleteProduct(){

    }
}
