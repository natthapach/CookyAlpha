package cs.sci.ku.cookyalpha.views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cs.sci.ku.cookyalpha.R;
import cs.sci.ku.cookyalpha.dao.Recipe;
import cs.sci.ku.cookyalpha.utils.Contextor;

/**
 * Created by MegapiesPT on 31/10/2560.
 */

public class RecipeItemView extends FrameLayout {
    private Recipe recipe;
    private ImageView ownerImageView;
    private TextView ownerNameTextView;
    private TextView timeTextView;
    private ImageView recipeImageView;
    private TextView recipeNameTextView;
    private TextView likesTextView;

    public RecipeItemView(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public RecipeItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public RecipeItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RecipeItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    private void initInflate(){
        inflate(getContext(), R.layout.view_recipe_item, this);
    }

    private void initInstance(){
        ownerImageView = findViewById(R.id.iv_owner_img);
        ownerNameTextView = findViewById(R.id.tv_owner_name);
        timeTextView = findViewById(R.id.tv_time_created);
        recipeImageView = findViewById(R.id.iv_recipe_img);
        likesTextView = findViewById(R.id.tv_likes);
        recipeNameTextView = findViewById(R.id.tv_recipe_name);
    }

    public void setRecipe(Recipe recipe) {
        Log.d("Cooky", "recipe view : setRecipe " + recipe);
        this.recipe = recipe;
        timeTextView.setText(recipe.createdTime);
        recipeNameTextView.setText(recipe.name);
        int like = recipe.like.size();
        likesTextView.setText(like + " likes");
        Glide.with(getContext())
                .load(recipe.preview.imgUrl)
                .into(recipeImageView);
    }
    public Recipe getRecipe(){
        return recipe;
    }

    public void enableTopMargin(){
        RelativeLayout rootLayout = findViewById(R.id.root);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(0, Contextor.getInstance().getContext().getResources().getDimensionPixelSize(R.dimen.spacing), 0, 0);
        rootLayout.setLayoutParams(layoutParams);
    }
}
