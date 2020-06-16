package dtos;


import entity.DayPlan;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class DayPlanDto {
    private Long id;
    @NonNull
    private String weekDay;

    @NonNull
    private Date date;

    @NonNull
    private Long recipeId;

    @NonNull
    private int servings;

    public DayPlanDto (DayPlan dayPlan) {
        this.id = dayPlan.getId();
        this.weekDay = dayPlan.getWeekDay();
        this.date = dayPlan.getDate();
        this.recipeId = dayPlan.getRecipeId();
        this.servings = dayPlan.getServings();
    }
}
