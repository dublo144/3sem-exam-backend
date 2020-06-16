package rest;

import business.RecipeBusiness;
import dtos.RecipeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("recipes")
public class RecipeResource {

    RecipeBusiness recipeBusiness = new RecipeBusiness();

    @Operation(summary = "Fetches recipes form external api",
              tags = {"recipes"},
    responses = {
        @ApiResponse(
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecipeDTO.class))),
        @ApiResponse(responseCode = "200", description = "The requested recipe"),
        @ApiResponse(responseCode = "403", description = "Not authenticated - do login")})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRecipes() throws IOException {
        return Response.ok(recipeBusiness.getAllRecipes()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipeDetails (@PathParam("id") Long id) throws IOException {
        return Response.ok(recipeBusiness.getRecipeDetails(id)).build();
    }
}
