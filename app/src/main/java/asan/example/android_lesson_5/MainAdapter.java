package asan.example.android_lesson_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    List<TitleModel> lists;
    Context context;

    public MainAdapter(List<TitleModel> lists, android.content.Context context) {
        this.lists = lists;
        this.context = context;
    }
    public void addApplication(TitleModel titleModel){
    lists.add(titleModel);
    notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.content.Context context;
        ViewGroup root;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainViewHolder holder, int position) {
    holder.onBind(lists.get(position));
    holder.txtMenu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View anchor;
            final PopupMenu popupMenu = new PopupMenu(context, holder.txtMenu);
            popupMenu.inflate(R.menu.menu);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.menu_save:
                            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.menu_delete:
                            lists.remove(position);
                            notifyDataSetChanged();
                            break;
                        default:

                    }
                    return false;
                }
            });
            popupMenu.show();
        }
    });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


    class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textPhone;
        TextView txtMenu;
        ImageView imageView;
        TitleModel titleModel;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.name);
            textPhone = itemView.findViewById(R.id.phone);
            imageView = itemView.findViewById(R.id.imageView);
            txtMenu = itemView.findViewById(R.id.txtOptionMenu);
        }

        public void onBind(TitleModel model) {
            this.titleModel = model;
            textName.setText(model.title);
            textPhone.setText(model.number);
            Glide.with(context)
                    .load(model.imageView)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        }
    }
}
