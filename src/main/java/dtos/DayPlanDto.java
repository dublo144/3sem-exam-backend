package dtos;


import entity.DayPlan;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class DayPlanDto {
    private Long id;
    @NonNull
    private String weekday;

    @NonNull
    private Long recipeId;

    @NonNull
    private String recipeName;

    @NonNull
    private Integer servings;

    public DayPlanDto (DayPlan dayPlan) {
        this.id = dayPlan.getId();
        this.weekday = dayPlan.getWeekday();
        this.recipeId = dayPlan.getRecipeId();
        this.recipeName = dayPlan.getRecipeName();
        this.servings = dayPlan.getServings();
    }
}
