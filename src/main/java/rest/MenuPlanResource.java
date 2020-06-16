package rest;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import dtos.DayPlanDto;
import dtos.MenuPlanDto;
import dtos.UserDto;
import errorhandling.UserException;
import facades.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("menuplans")
public class MenuPlanResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    public static final UserFacade USER_FACADE = UserFacade.getUserFacade(EMF);
    private Gson gson = new Gson();

    @Operation(summary = "Creates a meal plan",
            tags = {"meal plan"},
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuPlanDto.class))),
                    @ApiResponse(responseCode = "200", description = "Menuplan successfully submitted"),
                    @ApiResponse(responseCode = "403", description = "Not authenticated - do login")})
    @POST
    @Path("/create")
    @RolesAllowed({"user","admin"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMenuPlan(String jsonString) throws IOException, UserException {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        List<DayPlanDto> dayPlans = gson.fromJson(json.get("menuPlans"), new TypeToken<List<DayPlanDto>>(){}.getType());
        MenuPlanDto menuPlanDto = new MenuPlanDto(json.get("weeknumber").getAsInt(), dayPlans);
        String username = json.get("username").getAsString();
        UserDto userDto = USER_FACADE.createMenuPlan(username, menuPlanDto);
        return Response.ok(userDto).build();
    }

    @GET
    @Path("/{username}")
    @RolesAllowed({"user","admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMenuPlansForUser(@PathParam("username") String username) throws UserException {
        List<MenuPlanDto> menuPlanDtos = USER_FACADE.getMenuPlansForUser(username);
        return Response.ok(menuPlanDtos).build();
    }

    @GET
    @Path("/remove/{username}/{id}")
    @RolesAllowed({"user","admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeMenuPlan(@PathParam("username") String username, @PathParam("id") Long id) throws UserException {
        return Response.ok(USER_FACADE.removeMenuPlan(username, id)).build();
    }

}
