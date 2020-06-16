package entity;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    private Long id;

    @Column(name = "amount")
    private String amount;

    @Column(name = "name")
    private String name;
}
