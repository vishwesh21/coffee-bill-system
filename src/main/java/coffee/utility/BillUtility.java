package coffee.utility;

import coffee.model.Ingredient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillUtility {

    Map<Ingredient, List<Ingredient>> menu = new HashMap<Ingredient,List<Ingredient>>();

    public BillUtility(){
        getSeedData();
    }

    public Map getSeedData(){
        menu.put(new Ingredient("Coffee",5.0), Arrays.asList(new Ingredient("sugar",0.5),new Ingredient("water",0.5),new Ingredient("milk",1.0)));
        menu.put(new Ingredient("Chai",4.0), Arrays.asList(new Ingredient("sugar",0.5),new Ingredient("water",0.5),new Ingredient("milk",1.0)));
        menu.put(new Ingredient("Banana Smoothie",6.0), Arrays.asList(new Ingredient("sugar",0.5),new Ingredient("water",0.5),new Ingredient("milk",1.0)));
        menu.put(new Ingredient("Strawberry Shake",7.0), Arrays.asList(new Ingredient("sugar",0.5),new Ingredient("water",0.5),new Ingredient("milk",1.0)));
        menu.put(new Ingredient("Mojito",7.5), Arrays.asList(new Ingredient("sugar",0.5),new Ingredient("water",0.5),new Ingredient("soda",0.5),new Ingredient("mint",0.5)));
        return menu;
    }

    public Ingredient getMainIngredient(String ingredientName){
        for (Ingredient ingredient : menu.keySet()){
            if(ingredient.getName().equalsIgnoreCase(ingredientName)){
                return ingredient;
            }
        }
        return null;
    }

    public String getBillingAmount(String[] orderList){
        Double billingAmount = Arrays.stream(orderList).map(x -> {
            String[] item = x.split(",");
            Ingredient ind = getMainIngredient(item[0]);
            Double price = 0.0;
            if(ind !=null){
                price = ind.getPrice();
                if(item.length >1){
                    for(int k=1; k<item.length;k++){
                        for(Ingredient ee : menu.get(ind)){
                            String exclusions = item[k].trim().substring(1);
                            if(ee.getName().equalsIgnoreCase(exclusions)){
                                price -= ee.getPrice();
                                break;
                            }
                        }
                        if(price.equals(ind.getPrice())){
                            price =0.0;
                        }
                    }
                }
            }
            return price;
        }).mapToDouble(value -> value).sum();

        if(billingAmount==0.0){
            return "Not Valid Order";
        }else {
            return billingAmount.toString();
        }
    }
}
