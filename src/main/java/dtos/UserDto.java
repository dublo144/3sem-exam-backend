package dtos;

import entity.MenuPlan;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String username;
    private List<MenuPlanDto> menuPlans;

    public UserDto (User user) {
        this.username = user.getUserName();
        this.menuPlans = new ArrayList<>();
        for (MenuPlan menuPlan : user.getMenuPlans()) {
            this.menuPlans.add(new MenuPlanDto(menuPlan));
        }
    }
}
