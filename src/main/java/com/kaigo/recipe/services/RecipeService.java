package com.kaigo.recipe.services;

import com.kaigo.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
