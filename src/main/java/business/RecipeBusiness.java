package business;

import com.google.gson.Gson;
import dtos.RecipeDTO;
import utils.HttpUtils;

import java.io.IOException;

public class RecipeBusiness {

    private Gson gson = new Gson();

    public RecipeDTO getAllRecipes() throws IOException {
        String recipesJson = HttpUtils.fetchData("https://cphdat.dk/recipes");
        RecipeDTO recipeDTO = gson.fromJson(recipesJson, RecipeDTO.class);
        System.out.println(recipesJson);
        return recipeDTO;
    }


}
