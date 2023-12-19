package it.macgood.mathanappkt.ui.handbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.macgood.mathanappkt.R;
import it.macgood.mathanappkt.databinding.ItemHandbookBinding;
import it.macgood.mathanappkt.ui.MainActivity;

public class HandbookAdapter extends RecyclerView.Adapter<HandbookAdapter.ViewHolder>{

    private List<Handbook> handbooks;

    public HandbookAdapter(List<Handbook> list) {
        this.handbooks = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_handbook, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mPreview.setImageDrawable(ResourcesCompat
                .getDrawable(
                        holder.itemView.getResources(),
                        handbooks.get(position).getDrawable(),
                        null)
        );
        holder.mTitle.setText(handbooks.get(position).getTitle());
        holder.mAuthor.setText(handbooks.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return handbooks != null ? handbooks.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemHandbookBinding binding;
        private LinearLayout mItem;
        private ImageView mPreview;
        private TextView mTitle;
        private TextView mAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ItemHandbookBinding.bind(itemView);

            mItem = binding.item;
            mPreview = binding.imageBook;
            mTitle = binding.textTitle;
            mAuthor = binding.textAuthor;

            MainActivity activity = (MainActivity) itemView.getContext();
            NavController navController = Navigation.findNavController(activity, R.id.app_placeholder);
            mItem.setOnClickListener(view -> {
                if (mAuthor.getText().equals(handbooks.get(0).getAuthor())) {
                    navController.navigate(R.id.action_navigation_handbook_to_chaptersFragment);
                }
            });

        }
    }
}
