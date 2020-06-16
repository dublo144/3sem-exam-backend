package dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class DayPlanDto {
    private Long id;
    private String weekDay;
    private Date date;
    private RecipeDTO recipe;
    private int servings;

}
