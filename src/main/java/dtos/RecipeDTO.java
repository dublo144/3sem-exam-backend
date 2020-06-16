package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecipeDTO {
    private Long id;
    private String name;
    private int prepTime;
    private String[] directions;
    private String category;
    private List<IngredientDto> ingredients;
}
