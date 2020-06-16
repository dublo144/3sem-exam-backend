import entity.DayPlan;
import entity.MenuPlan;
import entity.User;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;

public class main {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);

    public static void main (String[] args) {
        DayPlan dayPlan = new DayPlan(1L, 4);
        MenuPlan menuPlan = new MenuPlan(Arrays.asList(dayPlan));
        User user = new User("SomeUser", "userpass", Arrays.asList(menuPlan));

        EntityManager em = EMF.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
