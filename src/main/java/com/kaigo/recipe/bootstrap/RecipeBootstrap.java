package com.kaigo.recipe.bootstrap;

import com.kaigo.recipe.domain.*;
import com.kaigo.recipe.repositories.CategoryRepository;
import com.kaigo.recipe.repositories.RecipeRepository;
import com.kaigo.recipe.repositories.UnitOfMeasureRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (eachUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (tablespoonUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (teaspoonUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (dashUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (pintUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (cupUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tablespoonUomOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (americanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected Category not found");
        }

        if (mexicanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected Category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe mQuacRecipe = new Recipe();
        mQuacRecipe.setDescription("Perfect Guacamole");
        mQuacRecipe.setPrepTime(10);
        mQuacRecipe.setCookTime(0);
        mQuacRecipe.setDirections(" Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "Variations\n" +
                "\n");
        Notes quacNotes = new Notes();
        quacNotes.setRecipyNotes(
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");
        quacNotes.setRecipe(mQuacRecipe);
        mQuacRecipe.setNotes(quacNotes);
        mQuacRecipe.setDifficulty(Difficulty.EASY);

        mQuacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2),mQuacRecipe , eachUom ));
        mQuacRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal("0.5"),mQuacRecipe , teaspoonUom));
        mQuacRecipe.getIngredients().add(new Ingredient("lemon juice", new BigDecimal(1),mQuacRecipe , tableSpoonUom));
        mQuacRecipe.getIngredients().add(new Ingredient("thinly sliced green onion", new BigDecimal(2),mQuacRecipe , tableSpoonUom));
        mQuacRecipe.getIngredients().add(new Ingredient("serrano chiles", new BigDecimal(2),mQuacRecipe , eachUom));
        mQuacRecipe.getIngredients().add(new Ingredient("cilantro ", new BigDecimal(2),mQuacRecipe , tableSpoonUom));
        mQuacRecipe.getIngredients().add(new Ingredient("black pepper", new BigDecimal(1),mQuacRecipe , dashUom));
        mQuacRecipe.getIngredients().add(new Ingredient("tomato", new BigDecimal(".5"),mQuacRecipe , eachUom));

        mQuacRecipe.getCategories().add(mexicanCategory);
        mQuacRecipe.getCategories().add(americanCategory);
        recipes.add(mQuacRecipe);


        Recipe mTacosRecipe = new Recipe();
        mTacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        mTacosRecipe.setPrepTime(20);
        mTacosRecipe.setCookTime(15);
        mTacosRecipe.setDirections("3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        mTacosRecipe.setDifficulty(Difficulty.MODERATE);

        mTacosRecipe.getIngredients().add(new Ingredient("ancho chili powder", new BigDecimal(2),mTacosRecipe , tableSpoonUom));
        mTacosRecipe.getIngredients().add(new Ingredient("dried oregano", new BigDecimal(1),mTacosRecipe , teaspoonUom));
        mTacosRecipe.getIngredients().add(new Ingredient("dried cumin", new BigDecimal(1),mTacosRecipe , teaspoonUom));
        mTacosRecipe.getIngredients().add(new Ingredient("sugar", new BigDecimal(1),mTacosRecipe , teaspoonUom));
        mTacosRecipe.getIngredients().add(new Ingredient("salt", new BigDecimal(".5"),mTacosRecipe , teaspoonUom));
        mTacosRecipe.getIngredients().add(new Ingredient("garlic", new BigDecimal(1),mTacosRecipe , eachUom));
        mTacosRecipe.getIngredients().add(new Ingredient("finely grated orange zest", new BigDecimal(1),mTacosRecipe , tableSpoonUom));
        mTacosRecipe.getIngredients().add(new Ingredient("orange juice", new BigDecimal(3),mTacosRecipe , tableSpoonUom));
        mTacosRecipe.getIngredients().add(new Ingredient("olive oil", new BigDecimal(2),mTacosRecipe , tableSpoonUom));
        mTacosRecipe.getIngredients().add(new Ingredient("boneless chicken thighs", new BigDecimal(4),mTacosRecipe , eachUom));
        mTacosRecipe.getIngredients().add(new Ingredient("corn tortillas", new BigDecimal(8),mTacosRecipe , eachUom));
        mTacosRecipe.getIngredients().add(new Ingredient("baby arugula", new BigDecimal(3),mTacosRecipe , cupUom));
        mTacosRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2),mTacosRecipe , eachUom));
        mTacosRecipe.getIngredients().add(new Ingredient("radishes", new BigDecimal(4),mTacosRecipe , eachUom));
        mTacosRecipe.getIngredients().add(new Ingredient("cherry tomatoes", new BigDecimal(".5"),mTacosRecipe , pintUom));
        mTacosRecipe.getIngredients().add(new Ingredient("onion", new BigDecimal(".25"),mTacosRecipe , eachUom));
        mTacosRecipe.getIngredients().add(new Ingredient("cilantro", new BigDecimal(1),mTacosRecipe , eachUom));
        mTacosRecipe.getIngredients().add(new Ingredient("sour cream", new BigDecimal(".5"),mTacosRecipe ,cupUom ));
        mTacosRecipe.getIngredients().add(new Ingredient("lime", new BigDecimal(1),mTacosRecipe , eachUom));

        mTacosRecipe.getCategories().add(americanCategory);
        recipes.add(mTacosRecipe);
        return recipes;
    }

}
