package entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ingredients")
public class Ingredient {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "ingredientList")
    private List<ShoppingList> shoppingLists;

    @NonNull
    @Column(name = "amount")
    private int amount;

    @NonNull
    @Column(name = "name")
    private String name;
}
