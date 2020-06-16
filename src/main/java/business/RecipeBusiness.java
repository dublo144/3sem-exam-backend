package business;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dtos.RecipeDTO;
import utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeBusiness {

    private final Gson gson = new Gson();
    private static final List<String> LIST_OF_RECIPE_PROPS = Arrays.asList("id", "name", "prepTime", "directions", "category", "ingredients");

    public List<RecipeDTO> getAllRecipes() throws IOException {
        String recipesJson = HttpUtils.fetchData("https://cphdat.dk/recipes");
        List<RecipeDTO> recipeDTOS = new ArrayList<>();
        JsonObject object = gson.fromJson(recipesJson, JsonObject.class);
        for (String key : object.keySet()) {
            JsonObject recipe = object.getAsJsonObject(key);
            recipeDTOS.add(
                    new RecipeDTO(
                    Long.parseLong(key),
                    recipe.get("name").getAsString(),
                    recipe.get("preparation_time").getAsInt()
                    )
            );
        }
        return recipeDTOS;
    }
}
