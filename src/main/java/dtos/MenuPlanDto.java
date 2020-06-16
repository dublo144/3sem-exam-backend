package dtos;

import entity.DayPlan;
import entity.MenuPlan;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class MenuPlanDto {
    private Long id;

    @NonNull
    private int weeknumber;

    @NonNull
    private List<DayPlanDto> dayPlans;

    public MenuPlanDto (MenuPlan menuPlan) {
        this.id = menuPlan.getId();
        this.weeknumber = menuPlan.getWeeknumber();
        this.dayPlans = new ArrayList<>();
        for (DayPlan dayPlan : menuPlan.getDayPlans()) {
            this.dayPlans.add(new DayPlanDto(dayPlan));
        }
    }
}
