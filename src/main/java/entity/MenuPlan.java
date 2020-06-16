package entity;

import dtos.DayPlanDto;
import dtos.MenuPlanDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "menu_plans")
@NamedQuery(name = "MenuPlan.deleteAllRows", query = "DELETE FROM MenuPlan")
public class MenuPlan {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    private Long id;

    @Column(name = "weekNumber")
    private int weekNumber;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "menu_plan_id")
    private List<DayPlan> dayPlans;

    public MenuPlan (MenuPlanDto dto) {
        this.dayPlans = new ArrayList<>();
        for (DayPlanDto dayPlanDto : dto.getDayPlans()) {
            this.dayPlans.add(new DayPlan(dayPlanDto));
        }
    }
}
