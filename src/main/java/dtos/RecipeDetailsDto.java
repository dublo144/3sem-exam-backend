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
public class RecipeDetailsDto {
    private Long id;
    private String category;
    private String[] directions;
    private List<IngredientDto> ingredients;
}
