package joshie.harvest.cooking.recipe;

import joshie.harvest.HarvestFestival;
import joshie.harvest.animals.HFAnimals;
import joshie.harvest.animals.item.ItemAnimalProduct.Sizeable;
import joshie.harvest.api.HFApi;
import joshie.harvest.api.cooking.Recipe;
import joshie.harvest.api.core.Size;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemIngredients;
import joshie.harvest.cooking.item.ItemMeal;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import joshie.harvest.core.util.annotations.HFLoader;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

@HFLoader
public class HFRecipes {
	public static void init() {
		addFryingPanRecipes();
		addMixerRecipes();
		addNoUtensilRecipes();
		addPotRecipes();
		addOvenRecipes();

		for (Meal ameal : ItemMeal.MEALS) {
			ItemMeal.MEAL_TO_RECIPE.put(ameal, Recipe.REGISTRY.get(HarvestFestival.id(ameal.getName())));
		}

		//Register the base meals as shippable
		HFApi.shipping.registerSellable(HFCooking.MEAL.getStackFromEnum(Meal.BUTTER), 100L);
		HFApi.shipping.registerSellable(HFCooking.MEAL.getStackFromEnum(Meal.EGG_BOILED), 56L);
		HFApi.shipping.registerSellable(HFCooking.MEAL.getStackFromEnum(Meal.SASHIMI), 11L);
		HFApi.shipping.registerSellable(HFCooking.MEAL.getStackFromEnum(Meal.EGG_SCRAMBLED), 112L);
		HFApi.shipping.registerSellable(HFCooking.MEAL.getStackFromEnum(Meal.KETCHUP), 212L);
		HFApi.shipping.registerSellable(HFCooking.MEAL.getStackFromEnum(Meal.NOODLES), 56L);
		HFApi.shipping.registerSellable(HFCooking.MEAL.getStackFromEnum(Meal.JAM_GRAPE), 224L);
		HFApi.shipping.registerSellable(HFCooking.MEAL.getStackFromEnum(Meal.JAM_STRAWBERRY), 44L);
		HFApi.shipping.registerSellable(HFCooking.MEAL.getStackFromEnum(Meal.JAM_APPLE), 112L);
		HFApi.shipping.registerSellable(HFCooking.MEAL.getStackFromEnum(Meal.COOKIES), 224L);
		HFApi.shipping.registerSellable(HFCooking.MEAL.getStackFromEnum(Meal.TEMPURA), 168L);
	}

	public static void complete() {
		for (Recipe recipe : Recipe.REGISTRY.values()) {
			recipe.updateCost();
		}
	}

	private static void addFryingPanRecipes() {
		//Added in 0.5+
		RecipeHelper.addFryingPanRecipe(
				Meal.PANCAKE_SAVOURY, 1.5F, 1.1F,
				HFIngredients.FLOUR,
				HFIngredients.CABBAGE,
				HFIngredients.OIL,
				HFIngredients.EGG).setOptionalIngredients(HFIngredients.ONION); //Jim 10000RP
		RecipeHelper.addFryingPanRecipe(Meal.FRIES_FRENCH, 4F, 1.2F, HFIngredients.POTATO, HFIngredients.OIL).setOptionalIngredients(
				HFIngredients.SALT); //Girafi 10000RP
		RecipeHelper.addFryingPanRecipe(Meal.POPCORN, 3F, 1F, HFIngredients.CORN).setOptionalIngredients(
				HFIngredients.BUTTER,
				HFIngredients.SALT); //Ashlee 10000RP
		RecipeHelper.addFryingPanRecipe(Meal.CORNFLAKES, 1.1F, 0.8F, HFIngredients.CORN, HFIngredients.MILK).setOptionalIngredients(
				HFIngredients.SUGAR); //Shop
		RecipeHelper.addFryingPanRecipe(Meal.EGGPLANT_HAPPY, 5F, 1.2F, HFIngredients.EGGPLANT)
				.setOptionalIngredients(HFIngredients.SUGAR);//Shop
		RecipeHelper.addFryingPanRecipe(Meal.EGG_SCRAMBLED, 1.5F, 1.5F, HFIngredients.EGG, HFIngredients.OIL).setOptionalIngredients(
				HFIngredients.BUTTER,
				HFIngredients.MAYONNAISE,
				HFIngredients.SALT); //Daniel 10000RP
		RecipeHelper.addFryingPanRecipe(Meal.OMELET, 1.2F, 1.1F, HFIngredients.EGG, HFIngredients.OIL, HFIngredients.MILK)
				.setOptionalIngredients(
						HFIngredients.SALT);//Shop
		RecipeHelper.addFryingPanRecipe(
				Meal.OMELET_RICE, 1.15F, 1.1F,
				HFIngredients.EGG,
				HFIngredients.MILK,
				HFIngredients.OIL,
				HFIngredients.RICEBALL).setOptionalIngredients(
				HFIngredients.CABBAGE,
				HFIngredients.ONION,
				HFIngredients.MUSHROOM,
				HFIngredients.GREEN_PEPPER,
				HFIngredients.SALT); //Brandon 10000RP
		RecipeHelper.addFryingPanRecipe(
				Meal.TOAST_FRENCH, 1.2F, 1.2F,
				HFIngredients.EGG,
				HFIngredients.BREAD,
				HFIngredients.OIL,
				HFIngredients.SUGAR).setOptionalIngredients(HFIngredients.BUTTER); //Jade 10000RP
		RecipeHelper.addFryingPanRecipe(
				Meal.DOUGHNUT, 1.2F, 0.8F,
				HFIngredients.EGG,
				HFIngredients.MILK,
				HFIngredients.BUTTER,
				HFIngredients.FLOUR,
				HFIngredients.OIL); //Tiberius 10000RP
		RecipeHelper.addFryingPanRecipe(
				Meal.FISH_GRILLED,
				1.1F,
				1.1F,
				HFIngredients.FISH,
				HFIngredients.OIL,
				HFIngredients.SALT); //Jacob 10000RP
		RecipeHelper.addFryingPanRecipe(
				Meal.PANCAKE, 1.2F, 1.2F,
				HFIngredients.EGG,
				HFIngredients.MILK,
				HFIngredients.FLOUR,
				HFIngredients.OIL).setOptionalIngredients(HFIngredients.SUGAR);//Shop
		RecipeHelper.addFryingPanRecipe(
				Meal.POTSTICKER, 1.5F, 2F,
				HFIngredients.CABBAGE,
				HFIngredients.ONION,
				HFIngredients.FLOUR,
				HFIngredients.OIL); //Jenni 10000RP
		RecipeHelper.addFryingPanRecipe(
				Meal.RISOTTO, 1.5F, 1.5F,
				HFIngredients.TOMATO,
				HFIngredients.ONION,
				HFIngredients.RICEBALL,
				HFIngredients.OIL); //Cloe 10000RP
		//Added in 0.6+
		RecipeHelper.addFryingPanRecipe(Meal.STIR_FRY, 1.2F, 1F, HFIngredients.CABBAGE, HFIngredients.OIL).setOptionalIngredients(
				HFIngredients.ONION,
				HFIngredients.CABBAGE,
				HFIngredients.BAMBOO,
				HFIngredients.MATSUTAKE,
				HFIngredients.EGGPLANT,
				HFIngredients.GREEN_PEPPER); //Jenni 20000RP
		RecipeHelper.addFryingPanRecipe(Meal.RICE_FRIED, 1.5F, 1F, HFIngredients.RICEBALL, HFIngredients.OIL, HFIngredients.EGG)
				.setOptionalIngredients(
						HFIngredients.CARROT,
						HFIngredients.ONION,
						HFIngredients.BAMBOO,
						HFIngredients.GREEN_PEPPER,
						HFIngredients.FISH);//Shops
		RecipeHelper.addFryingPanRecipe(Meal.SOUFFLE_APPLE, 2F, 1F, HFIngredients.APPLE);//Yulif 20000RP
		RecipeHelper.addFryingPanRecipe(
				Meal.BREAD_CURRY,
				1.1F,
				1F,
				HFIngredients.BREAD,
				HFIngredients.CURRY_POWDER,
				HFIngredients.OIL);//Jim 20000RP
		RecipeHelper.addFryingPanRecipe(Meal.NOODLES_THICK_FRIED, 1.2F, 1F, HFIngredients.NOODLES, HFIngredients.OIL)
				.setOptionalIngredients(
						HFIngredients.CABBAGE,
						HFIngredients.ONION,
						HFIngredients.FISH,
						HFIngredients.BAMBOO,
						HFIngredients.CARROT,
						HFIngredients.EGGPLANT); //Cloe 20000RP
		RecipeHelper.addFryingPanRecipe(Meal.TEMPURA, 1.2F, 1F, HFIngredients.EGG, HFIngredients.FLOUR, HFIngredients.OIL)
				.setOptionalIngredients(
						HFIngredients.EGGPLANT,
						HFIngredients.GREEN_PEPPER,
						HFIngredients.CARROT,
						HFIngredients.CABBAGE,
						HFIngredients.ONION); //Liara 17500RP
		RecipeHelper.addFryingPanRecipe(Meal.CURRY_DRY, 1.2F, 1F, HFIngredients.RICEBALL, HFIngredients.CURRY_POWDER)
				.setOptionalIngredients(
						HFIngredients.ONION,
						HFIngredients.GREEN_PEPPER,
						HFIngredients.FISH,
						HFIngredients.POTATO,
						HFIngredients.EGGPLANT,
						HFIngredients.CARROT);//Ashlee 20000RP
	}

	private static void addMixerRecipes() {
		//Added in 0.5+
		RecipeHelper.addMixerRecipe(Meal.JUICE_PINEAPPLE, 1.5F, 0.5F, HFIngredients.PINEAPPLE).setOptionalIngredients(
				HFIngredients.SALT,
				HFIngredients.SUGAR); //Yulif 5000RP
		RecipeHelper.addMixerRecipe(Meal.JUICE_TOMATO, 1.5F, 0.5F, HFIngredients.TOMATO).setOptionalIngredients(HFIngredients.SALT); //Shop
		RecipeHelper.addMixerRecipe(Meal.MILK_STRAWBERRY, 1.5F, 0.8F, HFIngredients.STRAWBERRY, HFIngredients.MILK).setOptionalIngredients(
				HFIngredients.SUGAR); //Goddess 10000RP Recipe
		RecipeHelper.addMixerRecipe(Meal.JUICE_VEGETABLE, 1.5F, 0.6F, HFIngredients.VEGETABLE_JUICE_BASE).setOptionalIngredients(
				HFIngredients.CUCUMBER,
				HFIngredients.ONION,
				HFIngredients.CABBAGE,
				HFIngredients.TOMATO,
				HFIngredients.SPINACH,
				HFIngredients.CARROT,
				HFIngredients.GREEN_PEPPER,
				HFIngredients.TURNIP,
				HFIngredients.SALT);//Shop
		RecipeHelper.addMixerRecipe(Meal.LATTE_VEGETABLE, 1.5F, 0.5F, HFIngredients.VEGETABLE_JUICE_BASE, HFIngredients.MILK)
				.setOptionalIngredients(
						HFIngredients.CUCUMBER,
						HFIngredients.ONION,
						HFIngredients.CABBAGE,
						HFIngredients.TOMATO,
						HFIngredients.SPINACH,
						HFIngredients.CARROT,
						HFIngredients.GREEN_PEPPER,
						HFIngredients.TURNIP,
						HFIngredients.SALT); //Thomas 5000RP
		RecipeHelper.addMixerRecipe(Meal.KETCHUP, 0.25F, 0.1F, HFIngredients.TOMATO, HFIngredients.ONION).setOptionalIngredients(
				HFIngredients.SALT,
				HFIngredients.SUGAR); //Shop
		RecipeHelper.addMixerRecipe(Meal.BUTTER, false, 0.9F, 1.5F, HFIngredients.MILK)
				.setOptionalIngredients(HFIngredients.SALT); //Daniel 5000RP
		RecipeHelper.addMixerRecipe(Meal.FISHSTICKS, false, 2F, 1.1F, HFIngredients.FISH)
				.setOptionalIngredients(HFIngredients.SALT);//Jim 5000RP
		//Added in 0.6+
		RecipeHelper.addMixerRecipe(Meal.JUICE_GRAPE, 1.5F, 1.2F, HFIngredients.GRAPE); //Ship grapes
		RecipeHelper.addMixerRecipe(Meal.JUICE_PEACH, 1.5F, 1.2F, HFIngredients.PEACH); //Ship a peach
		RecipeHelper.addMixerRecipe(Meal.JUICE_BANANA, 1.5F, 1.2F, HFIngredients.BANANA); //Ship a banana
		RecipeHelper.addMixerRecipe(Meal.JUICE_ORANGE, 1.5F, 1.2F, HFIngredients.ORANGE); //Ship an orange
		RecipeHelper.addMixerRecipe(Meal.JUICE_APPLE, 1.5F, 1.2F, HFIngredients.APPLE); //Ship an apple
		RecipeHelper.addMixerRecipe(
						Meal.JUICE_FRUIT, 1.05F, 0.5F,
						HFIngredients.FRUIT_JUICE_BASE).setOptionalIngredients(HFIngredients.FRUITS)
				.setMaximumOptionalIngredients(5); //Shops
		RecipeHelper.addMixerRecipe(Meal.LATTE_FRUIT, 1.04F, 0.6F, HFIngredients.FRUIT_JUICE_BASE, HFIngredients.MILK)
				.setOptionalIngredients(
						HFIngredients.FRUITS)
				.setMaximumOptionalIngredients(5); //Tomas 20000RP
		RecipeHelper.addMixerRecipe(Meal.JUICE_MIX, 0.5F, 0.5F, HFIngredients.FRUIT_JUICE_BASE, HFIngredients.VEGETABLE_JUICE_BASE)
				.setOptionalIngredients(
						HFIngredients.FRUITS,
						HFIngredients.VEGETABLE_JUICE_BASE,
						HFIngredients.TURNIP,
						HFIngredients.SALT)
				.setMaximumOptionalIngredients(7); //Shops
		RecipeHelper.addMixerRecipe(
				Meal.LATTE_MIX, 1.05F, 0.6F,
				HFIngredients.FRUIT_JUICE_BASE,
				HFIngredients.VEGETABLE_JUICE_BASE,
				HFIngredients.MILK).setOptionalIngredients(
				HFIngredients.FRUITS,
				HFIngredients.VEGETABLE_JUICE_BASE,
				HFIngredients.SALT).setMaximumOptionalIngredients(7);//Candice 20000RP
		//Vanilla style
		RecipeHelper.addMixerRecipe(
				"beetroot_soup", new ItemStack(Items.BEETROOT_SOUP),
				HFIngredients.BEETROOT,
				HFIngredients.TOMATO,
				HFIngredients.ONION,
				HFIngredients.OIL).setDefault();
		RecipeHelper.addMixerRecipe("flour", HFCooking.INGREDIENTS.getStackFromEnum(ItemIngredients.Ingredient.FLOUR), HFIngredients.WHEAT)
				.setDefault();
	}

	private static void addNoUtensilRecipes() {
		//Added in 0.5+
		RecipeHelper.addNoUtensilRecipe(Meal.TURNIP_PICKLED, 2F, 1.5F, HFIngredients.TURNIP)
				.setOptionalIngredients(HFIngredients.SALT); //Cafe Reward
		RecipeHelper.addNoUtensilRecipe(Meal.CUCUMBER_PICKLED, 2F, 1.5F, HFIngredients.CUCUMBER)
				.setOptionalIngredients(HFIngredients.SALT);//Shop
		RecipeHelper.addNoUtensilRecipe(Meal.SALAD, 1.1F, 1.2F, HFIngredients.SALAD_BASE).setOptionalIngredients(
				HFIngredients.MUSHROOM,
				HFIngredients.CUCUMBER,
				HFIngredients.CABBAGE,
				HFIngredients.TOMATO,
				HFIngredients.CARROT,
				HFIngredients.SALT); //Jenni 5000RP
		RecipeHelper.addNoUtensilRecipe(Meal.SANDWICH, 1.2F, 1.1F, HFIngredients.BREAD, HFIngredients.SANDWICH_BASE).setOptionalIngredients(
				HFIngredients.BUTTER,
				HFIngredients.TOMATO,
				HFIngredients.CUCUMBER,
				HFIngredients.SALT,
				HFIngredients.MAYONNAISE,
				HFIngredients.MUSHROOM);//Shop
		RecipeHelper.addNoUtensilRecipe(Meal.SUSHI, 1.2F, 1.2F, HFIngredients.SASHIMI, HFIngredients.RICEBALL);//Shop
		RecipeHelper.addNoUtensilRecipe(Meal.SASHIMI, 2.5F, 2F, HFIngredients.FISH); //Shop
		RecipeHelper.addNoUtensilRecipe(
				Meal.SASHIMI_CHIRASHI, 1.1F, 0.9F,
				HFIngredients.SASHIMI,
				HFIngredients.SCRAMBLED_EGG,
				HFIngredients.RICEBALL,
				HFIngredients.SASHIMI_VEGETABLE);//Shop
		//Added in 0.6+
		RecipeHelper.addNoUtensilRecipe(
				"mayonnaise_small",
				HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MAYONNAISE, Size.SMALL),
				HFIngredients.SMALL_EGG,
				HFIngredients.OIL);//Danieru 20000RP
		RecipeHelper.addNoUtensilRecipe(
				"mayonnaise_medium",
				HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MAYONNAISE, Size.MEDIUM),
				HFIngredients.MEDIUM_EGG,
				HFIngredients.OIL);//Danieru 20000RP
		RecipeHelper.addNoUtensilRecipe(
				"mayonnaise_large",
				HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MAYONNAISE, Size.LARGE),
				HFIngredients.LARGE_EGG,
				HFIngredients.OIL);//Danieru 20000RP
		RecipeHelper.addNoUtensilRecipe(Meal.SANDWICH_FRUIT, 1.05F, 1F, HFIngredients.BREAD, HFIngredients.FRUITS).setOptionalIngredients(
						HFIngredients.FRUITS)
				.setMaximumOptionalIngredients(5);//Shops
		RecipeHelper.addNoUtensilRecipe(Meal.RICE_BAMBOO, 2F, 1.5F, HFIngredients.BAMBOO, HFIngredients.RICEBALL);//Brandon
		RecipeHelper.addNoUtensilRecipe(Meal.RICE_MATSUTAKE, 2F, 1.5F, HFIngredients.MATSUTAKE, HFIngredients.RICEBALL);//Shops
		RecipeHelper.addNoUtensilRecipe(Meal.RICE_MUSHROOM, 2F, 1.5F, HFIngredients.BROWN_MUSHROOM, HFIngredients.RICEBALL);//Shops
		RecipeHelper.addNoUtensilRecipe(Meal.BREAD_RAISIN, 1.2F, 1.2F, HFIngredients.BREAD, HFIngredients.GRAPE);//Tiberius
		RecipeHelper.addNoUtensilRecipe(Meal.ICE_CREAM, 1.8F, 0.8F, HFIngredients.MILK, HFIngredients.EGG).setOptionalIngredients(
				HFIngredients.PINEAPPLE,
				HFIngredients.ORANGE,
				HFIngredients.STRAWBERRY,
				HFIngredients.GRAPE,
				HFIngredients.PEACH,
				HFIngredients.BANANA); //Candice 10000RP#
		RecipeHelper.addNoUtensilRecipe(
				Meal.SALAD_HERB,
				1F,
				1.3F,
				HFIngredients.MINT,
				HFIngredients.CHAMOMILE,
				HFIngredients.LAVENDER); //Shop
		RecipeHelper.addNoUtensilRecipe(
				Meal.SANDWICH_HERB, 1.05F, 1.1F,
				HFIngredients.BREAD,
				HFIngredients.MINT,
				HFIngredients.CHAMOMILE,
				HFIngredients.LAVENDER); //Shop
	}

	private static void addPotRecipes() {
		//Added in 0.5+
		RecipeHelper.addPotRecipe(Meal.MILK_HOT, true, 1.5F, 1.2F, HFIngredients.MILK).setOptionalIngredients(
				HFIngredients.SUGAR); //Candice 5000RP Recipe
		RecipeHelper.addPotRecipe(
				Meal.CHOCOLATE_HOT, true, 1.4F, 1.4F,
				HFIngredients.MILK,
				HFIngredients.CHOCOLATE).setOptionalIngredients(HFIngredients.SUGAR); //Liara 5000RP
		RecipeHelper.addPotRecipe(Meal.EGG_BOILED, 1.5F, 1.4F, HFIngredients.EGG).setOptionalIngredients(
				HFIngredients.SALT);//Shop
		RecipeHelper.addPotRecipe(Meal.SPINACH_BOILED, 2F, 1.2F, HFIngredients.SPINACH);//Shop
		RecipeHelper.addPotRecipe(Meal.POTATO_CANDIED, 3F, 2F, HFIngredients.SWEET_POTATO).setOptionalIngredients(
				HFIngredients.SUGAR); //Girafi 5000RP
		RecipeHelper.addPotRecipe(
				Meal.DUMPLINGS, 1.1F, 1.1F,
				HFIngredients.CABBAGE,
				HFIngredients.ONION,
				HFIngredients.FLOUR,
				HFIngredients.OIL).setOptionalIngredients(HFIngredients.SUGAR); //Thomas 10000RP
		RecipeHelper.addPotRecipe(Meal.NOODLES, 2F, 1.5F, HFIngredients.FLOUR).setOptionalIngredients(
				HFIngredients.SALT); //Cloe 5000RP
		RecipeHelper.addPotRecipe(Meal.SOUP_RICE, 3F, 1.2F, HFIngredients.RICEBALL); //Brandon 5000RP
		RecipeHelper.addPotRecipe(Meal.PORRIDGE, 1.5F, 1.2F, HFIngredients.MILK, HFIngredients.RICEBALL).setOptionalIngredients(
				HFIngredients.SUGAR); //Katlin 5000RP
		RecipeHelper.addPotRecipe(Meal.EGG_OVERRICE, 1.5F, 1F, HFIngredients.EGG, HFIngredients.RICEBALL).setOptionalIngredients(
				HFIngredients.SALT);//Shop
		RecipeHelper.addPotRecipe(Meal.STEW, 1.5F, 1.5F, HFIngredients.MILK, HFIngredients.FLOUR).setOptionalIngredients(
				HFIngredients.EGGPLANT,
				HFIngredients.ONION,
				HFIngredients.POTATO,
				HFIngredients.CARROT,
				HFIngredients.GREEN_PEPPER,
				HFIngredients.FISH,
				HFIngredients.SALT); //Katlin 10000RP
		RecipeHelper.addPotRecipe(Meal.STEW_PUMPKIN, 2F, 1.4F, HFIngredients.PUMPKIN).setOptionalIngredients(
				HFIngredients.SUGAR,
				HFIngredients.SALT);//Shop
		RecipeHelper.addPotRecipe(Meal.STEW_FISH, 3F, 2F, HFIngredients.FISH).setOptionalIngredients(
				HFIngredients.SALT); //Jacob 5000RP
		//Added in 0.6+
		RecipeHelper.addPotRecipe(Meal.JAM_STRAWBERRY, 1.5F, 0.9F, HFIngredients.STRAWBERRY).setOptionalIngredients(
				HFIngredients.WINE); //Goddess 5000RP
		RecipeHelper.addPotRecipe(Meal.JAM_APPLE, 1.5F, 0.9F, HFIngredients.APPLE).setOptionalIngredients(
				HFIngredients.WINE); //Jade 20000RP
		RecipeHelper.addPotRecipe(Meal.JAM_GRAPE, 1.5F, 0.9F, HFIngredients.GRAPE).setOptionalIngredients(
				HFIngredients.WINE);//Jade 20000RP
		RecipeHelper.addPotRecipe(Meal.MARMALADE, 1.5F, 0.9F, HFIngredients.ORANGE).setOptionalIngredients(
				HFIngredients.WINE);//Jade 20000RP
		RecipeHelper.addPotRecipe(
				Meal.NOODLES_TEMPURA, 1.1F, 0.5F,
				HFIngredients.TEMPURA,
				HFIngredients.NOODLES);//Liara 22500RP
		RecipeHelper.addPotRecipe(
				Meal.RICE_TEMPURA, 1.1F, 0.9F,
				HFIngredients.TEMPURA,
				HFIngredients.RICEBALL);//Liara 20000RP
		RecipeHelper.addPotRecipe(Meal.SOUP_HERB, 1.1F, 1.1F, HFIngredients.CHAMOMILE, HFIngredients.ONION).setOptionalIngredients(
				HFIngredients.MINT,
				HFIngredients.LAVENDER);//Shops
		//Vanilla style
		RecipeHelper.addPotRecipe(
				"rabbit_stew", new ItemStack(Items.RABBIT_STEW),
				HFIngredients.BAKED_POTATO,
				HFIngredients.CARROT,
				HFIngredients.RABBIT_COOKED,
				HFIngredients.MUSHROOM).setDefault();
		RecipeHelper.addPotRecipe(
				"brown_mushroom", new ItemStack(Items.MUSHROOM_STEW),
				HFIngredients.RED_MUSHROOM,
				HFIngredients.BROWN_MUSHROOM).setDefault();
	}

	private static void addOvenRecipes() {
		//Added in 0.5+
		RecipeHelper.addOvenRecipe(Meal.CORN_BAKED, 4F, 1.5F, HFIngredients.CORN).setOptionalIngredients(
				HFIngredients.OIL,
				HFIngredients.BUTTER,
				HFIngredients.SALT); //Ashlee 500RP
		RecipeHelper.addOvenRecipe(Meal.RICEBALLS_TOASTED, 3F, 1.5F, HFIngredients.RICEBALL).setOptionalIngredients(
				HFIngredients.SUGAR,
				HFIngredients.SALT);//Shop
		RecipeHelper.addOvenRecipe(Meal.TOAST, 1.5F, 0.8F, HFIngredients.BREAD).setOptionalIngredients(
				HFIngredients.BUTTER); //Jade 5000RP
		RecipeHelper.addOvenRecipe(
				Meal.DINNERROLL, 1.2F, 0.8F,
				HFIngredients.EGG,
				HFIngredients.MILK,
				HFIngredients.BUTTER); //Tiberius 5000RP
		RecipeHelper.addOvenRecipe(
				Meal.DORIA, 1.1F, 0.8F,
				HFIngredients.ONION,
				HFIngredients.BUTTER,
				HFIngredients.MILK,
				HFIngredients.RICEBALL,
				HFIngredients.FLOUR);//Shop
		RecipeHelper.addOvenRecipe(
				Meal.COOKIES, 1.2F, 0.4F,
				HFIngredients.EGG,
				HFIngredients.FLOUR,
				HFIngredients.BUTTER).setOptionalIngredients(HFIngredients.SUGAR); //Liara 7500RP
		RecipeHelper.addOvenRecipe(
				Meal.COOKIES_CHOCOLATE, 1.1F, 0.5F,
				HFIngredients.COOKIES,
				HFIngredients.CHOCOLATE); //Liara 10000RP
		RecipeHelper.addOvenRecipe(
				Meal.CAKE_CHOCOLATE, 1.3F, 1.1F,
				HFIngredients.EGG,
				HFIngredients.FLOUR,
				HFIngredients.BUTTER,
				HFIngredients.CHOCOLATE).setOptionalIngredients(
				HFIngredients.SUGAR,
				HFIngredients.FRUITS); //Yulif RP 100000
		//Added in 0.6+
		RecipeHelper.addOvenRecipe(
				Meal.BUN_JAM, 1.1F, 1.2F,
				HFIngredients.MILK,
				HFIngredients.EGG,
				HFIngredients.JAM); //Jade 25000RP
		RecipeHelper.addOvenRecipe(
				Meal.SWEET_POTATOES, 1.4F, 1.3F,
				HFIngredients.EGG,
				HFIngredients.BUTTER,
				HFIngredients.SWEET_POTATO);//Shop
		RecipeHelper.addOvenRecipe(
				Meal.CAKE, 1.3F, 1.1F,
				HFIngredients.EGG,
				HFIngredients.FLOUR,
				HFIngredients.BUTTER,
				HFIngredients.CAKE_FRUIT).setOptionalIngredients(
				HFIngredients.ORANGE,
				HFIngredients.PINEAPPLE,
				HFIngredients.STRAWBERRY,
				HFIngredients.PEACH,
				HFIngredients.GRAPE); //Liara 12500RP
		RecipeHelper.addOvenRecipe(
				Meal.PIE_APPLE, 1.2F, 1.1F,
				HFIngredients.APPLE,
				HFIngredients.EGG,
				HFIngredients.BUTTER,
				HFIngredients.FLOUR); //Katlin 20000RP
		//Vanilla style
		RecipeHelper.addOvenRecipe(
				"vanilla_cookies", new ItemStack(Items.COOKIE, 4),
				HFIngredients.FLOUR,
				HFIngredients.CHOCOLATE).setDefault();
		RecipeHelper.addOvenRecipe(
				"vanilla_cake", new ItemStack(Items.CAKE),
				HFIngredients.MILK,
				HFIngredients.EGG,
				HFIngredients.SUGAR,
				HFIngredients.FLOUR).setDefault();
		RecipeHelper.addOvenRecipe("bread", new ItemStack(Items.BREAD), HFIngredients.FLOUR).setDefault();
		RecipeHelper.addOvenRecipe("baked_potato", new ItemStack(Items.BAKED_POTATO), HFIngredients.POTATO).setDefault();
		RecipeHelper.addOvenRecipe(
				"cooked_chicken", new ItemStack(Items.COOKED_CHICKEN),
				HFIngredients.CHICKEN).setDefault();
		RecipeHelper.addOvenRecipe("cooked_beef", new ItemStack(Items.COOKED_BEEF), HFIngredients.BEEF).setDefault();
		RecipeHelper.addOvenRecipe("cooked_pork", new ItemStack(Items.COOKED_PORKCHOP), HFIngredients.PORK).setDefault();
		RecipeHelper.addOvenRecipe("cooked_mutton", new ItemStack(Items.COOKED_MUTTON), HFIngredients.MUTTON).setDefault();
		RecipeHelper.addOvenRecipe("cooked_rabbit", new ItemStack(Items.COOKED_RABBIT), HFIngredients.RABBIT).setDefault();
		RecipeHelper.addOvenRecipe("cooked_cod", new ItemStack(Items.COOKED_FISH, 1, 0), HFIngredients.COD).setDefault();
		RecipeHelper.addOvenRecipe(
				"cooked_salmon", new ItemStack(Items.COOKED_FISH, 1, 1),
				HFIngredients.SALMON).setDefault();
		RecipeHelper.addOvenRecipe(
				"pumpkin_pie", new ItemStack(Items.PUMPKIN_PIE),
				HFIngredients.PUMPKIN,
				HFIngredients.SUGAR,
				HFIngredients.EGG).setDefault();
	}
}
