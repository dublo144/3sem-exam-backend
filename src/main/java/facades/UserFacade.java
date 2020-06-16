package facades;

import dtos.MenuPlanDto;
import dtos.UserDto;
import entity.MenuPlan;
import entity.Role;
import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import errorhandling.AuthenticationException;
import errorhandling.UserException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {
  
    private static EntityManagerFactory emf;
    private static UserFacade instance;
    
    private UserFacade(){}
    
    /**
     * 
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User createUser(String username, String password) throws UserException {
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role("user"));
        User user = new User(username,password);
        user.setRoleList(roleList);
        EntityManager entityManager = emf.createEntityManager();
        try {
            boolean notInUse = (entityManager.find(User.class, username) == null);
            if(notInUse) {
                entityManager.getTransaction().begin();
                entityManager.persist(user);
                entityManager.getTransaction().commit();
            }
            else throw new UserException(UserException.IN_USE_USERNAME);
        }
        finally {
            entityManager.close();
        }
        return user;
    }

    public User getVerifiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public Role getUserRole(String rolename){
        EntityManager em = emf.createEntityManager();
        Role role;
        try {
            role = em.find(Role.class, rolename);
            if (role == null){
                role = new Role(rolename);
                em.getTransaction().begin();
                em.persist(role);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
        return role;
    }

    public UserDto createMenuPlan(String username, MenuPlanDto menuPlanDto) throws UserException {
        EntityManager em = emf.createEntityManager();
        User user;
        MenuPlan menuPlan;
        try {
            user = em.find(User.class, username);
            if (user == null) {
                // TODO EDIT
                throw new UserException(UserException.IN_USE_USERNAME);
            }
            menuPlan = new MenuPlan(menuPlanDto);
            user.addMenuPlan(menuPlan);
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDto(user);
    }

    public UserDto removeMenuPlan (String username, Long id) throws UserException {
        EntityManager em = emf.createEntityManager();
        User user;
        MenuPlan menuPlan;
        try {
            user = em.find(User.class, username);
            if (user == null) {
                // TODO EDIT
                throw new UserException(UserException.IN_USE_USERNAME);
            }
            menuPlan = em.find(MenuPlan.class, id);
            if (menuPlan == null) {
                // TODO EDIT
                throw new UserException(UserException.IN_USE_USERNAME);
            }
            user.removeMenuPlan(menuPlan);
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDto(user);
    }

}
