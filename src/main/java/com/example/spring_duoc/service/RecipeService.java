package com.example.spring_duoc.service;

import com.example.spring_duoc.model.Recipe;
import com.example.spring_duoc.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

  @Autowired
  private RecipeRepository recipeRepository;

  // Método para obtener todas las recetas
  public List<Recipe> getAllRecipes() {
    return recipeRepository.findAll();
  }

  // Método para obtener una receta por ID
  public Optional<Recipe> getRecipeById(Long id) {
    return recipeRepository.findById(id);
  }

  // Método para agregar una nueva receta
  public Recipe addRecipe(Recipe recipe) {
    return recipeRepository.save(recipe);
  }

  // Método para actualizar una receta existente
  public Optional<Recipe> updateRecipe(Long id, Recipe newRecipe) {
    return recipeRepository.findById(id).map(recipe -> {
      recipe.setTitle(newRecipe.getTitle());
      recipe.setDescription(newRecipe.getDescription());
      recipe.setIngredients(newRecipe.getIngredients());
      recipe.setInstructions(newRecipe.getInstructions());
      recipe.setCookTime(newRecipe.getCookTime());
      recipe.setDifficulty(newRecipe.getDifficulty());
      recipe.setPhotos(newRecipe.getPhotos());
      return recipeRepository.save(recipe);
    });
  }

  // Método para eliminar una receta por ID
  public void deleteRecipe(Long id) {
    recipeRepository.deleteById(id);
  }
}
