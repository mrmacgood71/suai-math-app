package it.macgood.mathanappkt.ui.guidebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.macgood.mathanappkt.R;
import it.macgood.mathanappkt.databinding.ItemGuidebookBinding;
import it.macgood.mathanappkt.ui.MainActivity;
import it.macgood.mathanappkt.ui.guidebook.supportingmaterials.MaterialsURL;

public class GuidebookAdapter extends RecyclerView.Adapter<GuidebookAdapter.ViewHolder>{

    private Context mContext;
    private List<Guide> mGuides;
    private Integer mFragmentId;

    public GuidebookAdapter() {
    }

    public GuidebookAdapter(List<Guide> mGuides) {
        this.mGuides = mGuides;
    }

    public GuidebookAdapter(Context mContext, List<Guide> mGuides) {
        this.mContext = mContext;
        this.mGuides = mGuides;
        notifyDataSetChanged();
    }

    public GuidebookAdapter(Context mContext, List<Guide> mGuides, Integer fragmentId) {
        this.mContext = mContext;
        this.mGuides = mGuides;
        this.mFragmentId = fragmentId;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_guidebook, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(mGuides.get(position).getTitle());
        holder.description.setText(mGuides.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mGuides.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView guidebook;
        TextView title;
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemGuidebookBinding binding = ItemGuidebookBinding.bind(itemView);

            guidebook = binding.guidebook;
            title = binding.guideTitle;
            description = binding.guideDescription;


            MainActivity activity = (MainActivity) itemView.getContext();
            NavController navController = Navigation.findNavController(activity, R.id.app_placeholder);

            guidebook.setOnClickListener(view -> {
                navigateFromGuidebook(itemView, navController);
            });

        }

        private void navigateFromGuidebook(@NonNull View itemView, NavController navController) {
            switch (title.getText().toString()) {
                case "Энциклопедия":
                    navController.navigate(R.id.action_navigation_guidebook_to_encyclopediaFragment);
                    break;
                case "Desmos":
                    navController.navigate(R.id.action_navigation_guidebook_to_desmosFragment);
                    break;
                case "Тесты":
                    navController.navigate(R.id.action_navigation_guidebook_to_testsFragment);
                    break;
                case "Сопроводительные материалы":
                    navController.navigate(R.id.action_navigation_guidebook_to_supportingMaterialsFragment);
                    break;
                case "Конвертер изображений":
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(MaterialsURL.CONVERTER));
                    itemView.getContext().startActivity(browserIntent);
                    break;
                case "Примечания":
                    navController.navigate(R.id.action_navigation_guidebook_to_remarkFragment);
                    break;
                case "Авторы":
                    navController.navigate(R.id.action_navigation_guidebook_to_authorFragment);
                    break;
                case "Контакты":
                    navController.navigate(R.id.action_navigation_guidebook_to_contactsFragment);
                    break;
            }
        }
    }


}
