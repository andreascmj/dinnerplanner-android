package se.kth.csc.iprog.dinnerplanner.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class DinnerModel extends Observable implements IDinnerModel{
	

	Set<Dish> dishes = new HashSet<Dish>();
    int numberOfGuests;
    private Dish starter;
    private Dish main;
    private Dish desert;

	/**
	 * The constructor of the overall model. Set the default values here HAY
	 */
	public DinnerModel(){
		
		//Adding some example data, you can add more
		Dish dish1 = new Dish("French toast",Dish.STARTER,"toast","In a large mixing bowl, beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. Soak bread slices in the egg mixture until saturated. Heat a lightly oiled griddle or frying pan over medium high heat. Brown slices on both sides, sprinkle with cinnamon and serve hot.");
		Ingredient dish1ing1 = new Ingredient("eggs",0.5,"",1);
		Ingredient dish1ing2 = new Ingredient("milk",30,"ml",6);
		Ingredient dish1ing3 = new Ingredient("brown sugar",7,"g",1);
		Ingredient dish1ing4 = new Ingredient("ground nutmeg",0.5,"g",12);
		Ingredient dish1ing5 = new Ingredient("white bread",2,"slices",2);
		dish1.addIngredient(dish1ing1);
		dish1.addIngredient(dish1ing2);
		dish1.addIngredient(dish1ing3);
		dish1.addIngredient(dish1ing4);
		dish1.addIngredient(dish1ing5);
		dishes.add(dish1);
		
		Dish dish2 = new Dish("Meat balls",Dish.MAIN,"meatballs","Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
		Ingredient dish2ing1 = new Ingredient("extra lean ground beef",115,"g",20);
		Ingredient dish2ing2 = new Ingredient("sea salt",0.7,"g",3);
		Ingredient dish2ing3 = new Ingredient("small onion, diced",0.25,"",2);
		Ingredient dish2ing4 = new Ingredient("garlic salt",0.6,"g",3);
		Ingredient dish2ing5 = new Ingredient("Italian seasoning",0.3,"g",3);
		Ingredient dish2ing6 = new Ingredient("dried oregano",0.3,"g",3);
		Ingredient dish2ing7 = new Ingredient("crushed red pepper flakes",0.6,"g",3);
		Ingredient dish2ing8 = new Ingredient("Worcestershire sauce",16,"ml",7);
		Ingredient dish2ing9 = new Ingredient("milk",20,"ml",4);
		Ingredient dish2ing10 = new Ingredient("grated Parmesan cheese",5,"g",8);
		Ingredient dish2ing11 = new Ingredient("seasoned bread crumbs",115,"g",4);
		dish2.addIngredient(dish2ing1);
		dish2.addIngredient(dish2ing2);
		dish2.addIngredient(dish2ing3);
		dish2.addIngredient(dish2ing4);
		dish2.addIngredient(dish2ing5);
		dish2.addIngredient(dish2ing6);
		dish2.addIngredient(dish2ing7);
		dish2.addIngredient(dish2ing8);
		dish2.addIngredient(dish2ing9);
		dish2.addIngredient(dish2ing10);
		dish2.addIngredient(dish2ing11);
		dishes.add(dish2);

        Dish dish3 = new Dish("Ice Cream", Dish.DESERT, "icecream", "Put whatever ice cream you want in a bowl.");
        Ingredient dish3ing1 = new Ingredient("Ice Cream", 120, "g", 15);
        dish3.addIngredient(dish3ing1);
        dishes.add(dish3);

        Dish dish4 = new Dish("Ice Cream with Chocolate Sauce", Dish.DESERT, "icecream2", "Put whatever ice cream you want. \nAdd Chocolate Sauce");
        Ingredient dish4ing1 = new Ingredient("Ice Cream", 120, "g", 15);
        Ingredient dish4ing2 = new Ingredient("Chocolate Sauce", 20, "ml", 3);
        dish4.addIngredient(dish4ing1);
        dish4.addIngredient(dish4ing2);
        dishes.add(dish4);

        Dish dish5 = new Dish("Tacos", Dish.MAIN, "tacos", "Mix the meat with some taco spice and water while in the pan. \nChop the vegetables and put in bowls. \nHeat the bread in the microwave and serve.");
        Ingredient dish5ing1 = new Ingredient("Minced Meat", 125, "g", 10);
        Ingredient dish5ing2 = new Ingredient("Taco Spice", 0.25, "st", 6);
        Ingredient dish5ing3 = new Ingredient("Tomatoes", 1, "st", 4);
        Ingredient dish5ing4 = new Ingredient("Corn",70, "g", 3);
        dish5.addIngredient(dish5ing1);
        dish5.addIngredient(dish5ing2);
        dish5.addIngredient(dish5ing3);
        dish5.addIngredient(dish5ing4);
        dishes.add(dish5);

        Dish dish6 = new Dish("Halloumi", Dish.STARTER, "halloumi", "Put halloumi on a plate. \nAdd tomatoes.");
        Ingredient dish6ing1 = new Ingredient("Halloumi", 60, "g", 15);
        Ingredient dish6ing2 = new Ingredient("Tomatoes", 1, "st", 4);
        dish6.addIngredient(dish6ing1);
        dish6.addIngredient(dish6ing2);
        dishes.add(dish6);

	}
	/**
	 * Returns the set of dishes
	 */
	public Set<Dish> getDishes(){
		return dishes;
	}
	
	/**
	 * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
	 */
	public Set<Dish> getDishesOfType(int type){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.getType() == type){
				result.add(d);
			}
		}
		return result;
	}
	
	/**
	 * Returns the set of dishes of specific type, that contain filter in their name
	 * or name of any ingredient. 
	 */
	public Set<Dish> filterDishesOfType(int type, String filter){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.getType() == type && d.contains(filter)){
				result.add(d);
			}
		}
		return result;
	}


    @Override
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    @Override
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
        setChanged();
        notifyObservers(numberOfGuests);
    }

    @Override
    public Dish getSelectedDish(int type) {
        switch (type){
            case 1: return starter;
            case 2: return main;
            default: return desert;
        }
    }

    @Override
    public Set<Dish> getFullMenu() {
        HashSet<Dish> fm = new HashSet<Dish>();
        if (starter != null) {
            fm.add(starter);
        }
        if (main != null) {
            fm.add(main);
        }
        if (desert != null) {
            fm.add(desert);
        }
        return fm;
    }

    @Override
    public Set<Ingredient> getAllIngredients() {
        HashSet<Ingredient> ai = new HashSet<Ingredient>();
        if (starter != null) {
            ai.addAll(starter.ingredients);
        }
        if (main != null) {
            ai.addAll(main.ingredients);
        }
        if (desert != null) {
            ai.addAll(desert.ingredients);
        }
        return ai;
    }

    @Override
    public float getTotalMenuPrice() {
        Set<Ingredient> ai = getAllIngredients();
        int price = 0;
        for (Ingredient i : ai) {
            price += i.getPrice();
        }
        price = price * getNumberOfGuests();
        return price;
    }


    public void setDesert(Dish desert) {
        this.desert = desert;
        setChanged();
        notifyObservers(desert);
    }

    public void setMain(Dish main) {
        this.main = main;
        setChanged();
        notifyObservers(main);
    }

    public void setStarter(Dish starter) {
        this.starter = starter;
        setChanged();
        notifyObservers(starter);
    }
}
