package cs.sci.ku.cookyalpha.fragments.recipe.display;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import cs.sci.ku.cookyalpha.R;
import cs.sci.ku.cookyalpha.callbacks.OnResult;
import cs.sci.ku.cookyalpha.dao.Recipe;
import cs.sci.ku.cookyalpha.dao.User;
import cs.sci.ku.cookyalpha.managers.FirebaseRecipeManager;
import cs.sci.ku.cookyalpha.managers.ProfileManager;

/**
 * Created by MegapiesPT on 4/11/2560.
 */

public class PreviewRecipeFragment extends Fragment {

    private Recipe recipe;
    private TextView ownerNameTextView;
    private ImageView ownerImageView;
    private TextView timeTextView;
    private ImageView previewImageView;
    private TextView descriptionTextView;
    private TextView recipeNameTextView;

    public static PreviewRecipeFragment newInstance(String recipeId){
        PreviewRecipeFragment instance = new PreviewRecipeFragment();

        Bundle arg = new Bundle();
        arg.putString("recipe-id", recipeId);
        instance.setArguments(arg);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String recipeId = getArguments().getString("recipe-id");
        recipe = FirebaseRecipeManager.getInstance().getRecipe(recipeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_preview, container, false);
        initInstance(rootView);
        if (recipe != null)
            initData();
        return rootView;
    }

    private void initData() {
        timeTextView.setText(recipe.createdTime);
        descriptionTextView.setText(recipe.description);
        recipeNameTextView.setText(recipe.name);
        Glide.with(getContext())
                .load(recipe.preview.imgUrl)
                .apply(new RequestOptions().centerCrop())
                .into(previewImageView);
        ProfileManager.getInstance().loadUser(recipe.ownerId, new OnResult<User>() {
            @Override
            public void onResult(User obj) {
                Log.d("preview recipe", ownerImageView + " " + ownerNameTextView);
                if (ownerImageView != null)
                    Glide.with(getContext())
                        .load(obj.getImgProfile())
                        .apply(new RequestOptions().circleCrop())
                        .into(ownerImageView);
                if (ownerNameTextView != null)
                    ownerNameTextView.setText(obj.getName());
            }
        });
    }

    private void initInstance(View rootView) {
        ownerNameTextView = rootView.findViewById(R.id.tv_owner_name);
        ownerImageView = rootView.findViewById(R.id.iv_owner_img);
        timeTextView = rootView.findViewById(R.id.tv_time_created);
        previewImageView = rootView.findViewById(R.id.iv_recipe_img);
        recipeNameTextView = rootView.findViewById(R.id.tv_recipe_name);
        descriptionTextView =  rootView.findViewById(R.id.tv_recipe_desc);
        Log.d("init preview recipe", ownerImageView + " " + ownerNameTextView);
    }
}
