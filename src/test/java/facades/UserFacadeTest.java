package facades;

import dtos.DayPlanDto;
import dtos.MenuPlanDto;
import dtos.UserDto;
import entity.MenuPlan;
import entity.User;
import errorhandling.UserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserFacadeTest {

    private static EntityManagerFactory entityManagerFactory;
    private static UserFacade userFacade;
    private static MenuPlan menuPlan;
    private static MenuPlanDto menuPlanDto;
    private static DayPlanDto dayPlanDto;
    private static User u1, u2;

    @BeforeAll
    public static void setUpClass() {
        entityManagerFactory = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        userFacade = UserFacade.getUserFacade(entityManagerFactory);
        u1 = new User("test1", "test1");
        u2 = new User("jpaLover", "loveJpa");
        dayPlanDto = new DayPlanDto("Monday", new Date(), 1L, 4);
        menuPlanDto = new MenuPlanDto(30, Arrays.asList(dayPlanDto));
        menuPlan = new MenuPlan(menuPlanDto);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("DayPlan.deleteAllRows").executeUpdate();
            em.createNamedQuery("MenuPlan.deleteAllRows").executeUpdate();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Roles.deleteAllRows").executeUpdate();
            em.persist(u1);
            em.persist(u2);
            u1.addMenuPlan(menuPlan);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void test_createUser() throws UserException {
        String expectedUsername = "Mads";
        String expectedPassword = "hejmeddig";
        User newUser = userFacade.createUser(expectedUsername, expectedPassword);

        assertEquals(expectedUsername, newUser.getUserName());
    }

    @Test
    public void test_createExistingUser() throws UserException {
        String expectedUsername = "jpaLover";
        String expectedPassword = "jpaLover";
        Assertions.assertThrows(UserException.class, () -> userFacade.createUser(expectedUsername, expectedPassword));
    }

    @Test
    public void test_createMenuPlan() throws UserException {
        int expectedNumberOfMenuplans = 1;
        UserDto userDto = userFacade.createMenuPlan(u2.getUserName(), menuPlanDto);
        int actual = userDto.getMenuPlans().size();
        assertEquals(expectedNumberOfMenuplans, actual);
    }

    @Test
    public void  test_removeMenuPlan() throws UserException {
        int expectedNumberOfMenuPlans = 0;
        UserDto userDto = userFacade.removeMenuPlan(u1.getUserName(), menuPlan.getId());
        int actual = userDto.getMenuPlans().size();
        assertEquals(expectedNumberOfMenuPlans, actual);
    }
}