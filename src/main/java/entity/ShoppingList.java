package entity;

import dtos.IngredientDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shopping_lists")
@NamedQuery(name = "ShoppingList.deleteAllRows", query = "DELETE FROM ShoppingList ")
public class ShoppingList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @JoinTable(name = "shopping_list_ingredients", joinColumns = {
            @JoinColumn(name = "id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "id", referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Ingredient> ingredientList = new ArrayList();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public ShoppingList (List<IngredientDto> ingredients, int servings) {
        for (IngredientDto ingredient : ingredients) {
            this.ingredientList.add(new Ingredient((ingredient.getAmount() * servings), ingredient.getName()));
        }
    }
}
