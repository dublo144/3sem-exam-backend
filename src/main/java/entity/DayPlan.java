package entity;

import dtos.DayPlanDto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dayplans")
@NamedQuery(name = "DayPlan.deleteAllRows", query = "DELETE FROM DayPlan")
public class DayPlan {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @NonNull
    private String weekday;

    @NonNull
    @Column(name = "recipe_id")
    private Long recipeId;

    @NonNull
    @Column(name = "recipe_name")
    private String recipeName;

    @NonNull
    @Column(name = "servings")
    private int servings;

    public DayPlan (DayPlanDto dto) {
        this.weekday = dto.getWeekday();
        this.recipeId = dto.getRecipeId();
        this.recipeName = dto.getRecipeName();
        this.servings = dto.getServings();
    }
}
