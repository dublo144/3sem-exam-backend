package business;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import dtos.IngredientDto;
import dtos.RecipeDTO;
import dtos.RecipeDetailsDto;
import utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeBusiness {

    private final Gson gson = new Gson();

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

    public RecipeDetailsDto getRecipeDetails(Long _id) throws IOException {
        String recipeJson = HttpUtils.fetchData("https://cphdat.dk/recipe/" + _id);
        JsonObject jsonObject = gson.fromJson(recipeJson, JsonObject.class);
        String category = jsonObject.get("category").getAsString();
        Long id = jsonObject.get("id").getAsLong();
        List<IngredientDto> ingredients = gson.fromJson(jsonObject.get("ingredient_list"), new TypeToken<List<IngredientDto>>(){}.getType());
        String[] directions = gson.fromJson(jsonObject.get("directions"), new TypeToken<String[]>(){}.getType());
        String name = jsonObject.get("name").getAsString();
        int prepTime = jsonObject.get("preparation_time").getAsInt();
        return new RecipeDetailsDto(id, category, directions, ingredients, name, prepTime);
    }
}
