package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dayplans")
public class DayPlan {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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

}
