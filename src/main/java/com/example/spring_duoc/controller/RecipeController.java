package com.example.spring_duoc.controller;

import com.example.spring_duoc.model.Recipe;
import com.example.spring_duoc.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

  @Autowired
  private RecipeService recipeService;

  // Endpoint para obtener todas las recetas (solo para usuarios autenticados)
  @GetMapping
  public ResponseEntity<List<Recipe>> getAllRecipes(Authentication authentication) {
    if (authentication == null) {
      return ResponseEntity.status(401).build(); // No autenticado
    }
    List<Recipe> recipes = recipeService.getAllRecipes();
    return ResponseEntity.ok(recipes);
  }

  // Endpoint para obtener una receta por ID (solo para usuarios autenticados)
  @GetMapping("/{id}")
  public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id, Authentication authentication) {
    if (authentication == null) {
      return ResponseEntity.status(401).build(); // No autenticado
    }
    Optional<Recipe> recipe = recipeService.getRecipeById(id);
    return recipe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Endpoint para agregar una nueva receta (solo para usuarios autenticados)
  @PostMapping
  public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe, Authentication authentication) {
    if (authentication == null) {
      return ResponseEntity.status(401).build(); // No autenticado
    }
    Recipe savedRecipe = recipeService.addRecipe(recipe);
    return ResponseEntity.status(201).body(savedRecipe);
  }

  // Endpoint para actualizar una receta existente (solo para usuarios
  // autenticados)
  @PutMapping("/{id}")
  public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe,
      Authentication authentication) {
    if (authentication == null) {
      return ResponseEntity.status(401).build(); // No autenticado
    }
    Optional<Recipe> updatedRecipe = recipeService.updateRecipe(id, recipe);
    return updatedRecipe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Endpoint para eliminar una receta por ID (solo para usuarios autenticados)
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRecipe(@PathVariable Long id, Authentication authentication) {
    if (authentication == null) {
      return ResponseEntity.status(401).build(); // No autenticado
    }
    recipeService.deleteRecipe(id);
    return ResponseEntity.noContent().build();
  }
}
