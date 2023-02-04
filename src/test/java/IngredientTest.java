import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final IngredientType type;
    private final String name;
    private final float price;
    Ingredient ingredient;

    public IngredientTest(IngredientType type, String name, float price){
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Test №{index}: type = {0}, name = {1}, price = {2}")
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "Сырный соус", 15},
                {IngredientType.FILLING, "Сыр", 19.99f},
                {IngredientType.FILLING, "Tomato", 20f},
        });
    }

    @Before
    public void createIngredient(){
        ingredient = new Ingredient(type, name, price);
    }

    //Проверяем, что возвращается корректное название
    @Test
    public void checkGetNameOfIngredient(){
        Assert.assertEquals("Ошибка! Значения не совпали.", name, ingredient.getName());
    }

    //Проверяем, что метод возвращает целые и дробные значения
    @Test
    public void checkGetPriceOfIngredient(){
        Assert.assertEquals("Ошибка! Значения не совпали.", price, ingredient.getPrice(),0f);
    }

    //Проверяем, что возвращается корректное значение класса IngredientType
    @Test
    public void checkGetTypeOfIngredient(){
        Assert.assertEquals("Ошибка! Значения не совпали.", type, ingredient.getType());
    }

}
