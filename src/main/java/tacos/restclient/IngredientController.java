package tacos.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.restclient.domain.Ingredient;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientRequestsService ingredientRequestsService;

    @Autowired
    public IngredientController(IngredientRequestsService ingredientRequestsService) {
        this.ingredientRequestsService = ingredientRequestsService;
    }

    @GetMapping(path = "/{id}")
    public Ingredient getIngredientById(@PathVariable("id") String id) {
        return ingredientRequestsService.getIngredientById(id);
    }

    @GetMapping
    public List<Ingredient> allIngredients() {
        return ingredientRequestsService.getAllIngredients();
    }

    @PostMapping
    public Ingredient postIngredient(@RequestBody Ingredient ingredient) {
        return ingredientRequestsService.addNewIngredient(ingredient);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String id) {
        ingredientRequestsService.deleteIngredient(id);
    }
}
