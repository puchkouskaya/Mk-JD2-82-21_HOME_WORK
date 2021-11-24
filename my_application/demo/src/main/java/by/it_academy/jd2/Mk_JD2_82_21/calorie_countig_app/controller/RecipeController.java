package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.controller;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.RecipeDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Recipe;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IRecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    private final IRecipeService recipeService;

    public RecipeController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRecipe(@PathVariable("id") Long id) {
        try {
            Recipe recipe = recipeService.get(id);
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Recipe> saveRecipe(@RequestBody RecipeDto recipeDto) {
        try {
            Recipe recipe = recipeService.save(recipeDto);
            return new ResponseEntity<>(recipe, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> update(@RequestBody Recipe recipe,
                                    @PathVariable("id") Long id,
                                    @PathVariable("dt_update") Long dtUpdate) {
        try {
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
            Recipe recipe1 = recipeService.update(recipe, id, date);
            return new ResponseEntity<>(recipe1, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> delete(@RequestBody RecipeDto recipeDto,
                                    @PathVariable("id") Long id,
                                    @PathVariable("dt_update") Long dtUpdate) {
        try {
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
            recipeService.delete(id, date);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
