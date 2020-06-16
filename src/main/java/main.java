import business.RecipeBusiness;
import dtos.RecipeDTO;
import errorhandling.UserException;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class main {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
    public static final UserFacade USER_FACADE = UserFacade.getUserFacade(EMF);

    public static void main (String[] args) throws UserException, IOException {
        RecipeBusiness recipeBusiness = new RecipeBusiness();
        recipeBusiness.getAllRecipes();
    }
}
