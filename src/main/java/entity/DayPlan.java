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
    private String weekDay;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date date;

    @NonNull
    @Column(name = "recipe_id")
    private Long recipeId;

    @NonNull
    @Column(name = "servings")
    private Integer servings;

    public DayPlan (DayPlanDto dto) {
        this.weekDay = dto.getWeekDay();
        this.date = dto.getDate();
        this.recipeId = dto.getRecipeId();
        this.servings = dto.getServings();
    }
}
