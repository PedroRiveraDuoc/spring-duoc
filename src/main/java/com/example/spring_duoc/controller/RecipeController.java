package com.example.spring_duoc.controller;

import com.example.spring_duoc.model.Photo;
import com.example.spring_duoc.model.Recipe;
import com.example.spring_duoc.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

  @Autowired
  private RecipeService recipeService;

  // Endpoint para obtener todas las recetas (solo para usuarios autenticados)
  @GetMapping
  public String getAllRecipes(Authentication authentication, Model model) {
    if (authentication == null) {
      return "redirect:/login";
    }

    List<Recipe> recipes = recipeService.getAllRecipes();
    model.addAttribute("recipes", recipes);

    return "recipes";
  }

  // Endpoint para obtener una receta por ID (solo para usuarios autenticados)
  @GetMapping("/{id}")
  public String getRecipeById(@PathVariable Long id, Authentication authentication, Model model) {
    if (authentication == null) {
      return "redirect:/login";
    }

    Optional<Recipe> recipe = recipeService.getRecipeById(id);
    if (recipe.isPresent()) {
      model.addAttribute("recipe", recipe.get());
      return "recipe_detail"; // Devolver la vista de detalles de la receta
    } else {
      return "404"; // TODO: crear vista notfound
    }
  }

  // Mostrar formulario para crear nueva receta
  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("recipe", new Recipe()); // Añadir una receta vacía al modelo
    return "recipe_form"; // Devuelve la vista del formulario
  }

  // Manejar la creación de una nueva receta
  @PostMapping
  public String createRecipe(@ModelAttribute("recipe") Recipe recipe,
      @RequestParam("photoUrl") List<String> photoUrls,
      @RequestParam("photoDescription") List<String> photoDescriptions,
      Authentication authentication) {
    if (authentication == null) {
      return "redirect:/login";
    }

    // Procesar ingredientes
    recipe.setIngredients(List.of(recipe.getIngredients().get(0).split(",")));

    // Procesar fotos
    List<Photo> photos = new ArrayList<>();
    for (int i = 0; i < photoUrls.size(); i++) {
      photos.add(new Photo(null, photoUrls.get(i), photoDescriptions.get(i)));
    }
    recipe.setPhotos(photos);

    recipeService.addRecipe(recipe);
    return "redirect:/recipes";
  }
}
