import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

public class BurgerTest {

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient0;
    @Mock
    Ingredient ingredient1;
    @Mock
    Ingredient ingredient2;
    private Burger burger;
    private final float[]  testIngredientsPrice = {10f, 19.99f, 20.0f, 49.99f}; //стоимость каждого ингридиента + сумма стоимостей
    private final float testBurgerPrice = 2 * 50f + testIngredientsPrice[3]; //стоимсоть бургера

    @Before //создание бургера: булочка и три ингредиента
    public void createBurger() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient0);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
    }

    //Проверяем, что работает добавление ингридиентов
    @Test
    public void checkMethodAddIngredient(){
        int start_size = burger.ingredients.size();
        burger.addIngredient(ingredient0);
        Assert.assertEquals("Добавление ингредиентов не работает", start_size + 1, burger.ingredients.size());
    }

    //Проверяем, что работает удаление ингридиентов
    @Test
    public void checkMethodRemoveIngredient(){
        int start_size = burger.ingredients.size();
        burger.removeIngredient(start_size - 1);
        Assert.assertEquals("Удаление ингредиентов не работает", start_size - 1, burger.ingredients.size());
    }

    //Проверяем, что работает перемещение ингридиентов
    @Test
    public void checkMethodMoveIngredient(){
        int index = burger.ingredients.indexOf(ingredient0);
        int newIndex = burger.ingredients.indexOf(ingredient1);
        burger.moveIngredient(index,newIndex);
        Assert.assertEquals("Перемещение ингредиентов не работает", newIndex, burger.ingredients.indexOf(ingredient0));
}

    //Проверяем корректность возвращаемой суммы
    @Test
    public void checkReturnPrice(){
        Mockito.when(bun.getPrice()).thenReturn(50f);
        Mockito.when(ingredient0.getPrice()).thenReturn(testIngredientsPrice[0]);
        Mockito.when(ingredient1.getPrice()).thenReturn(testIngredientsPrice[1]);
        Mockito.when(ingredient2.getPrice()).thenReturn(testIngredientsPrice[2]);
        Assert.assertEquals("Сумма не совпала", testBurgerPrice, burger.getPrice(),0);
    }

    //Проверяем корректность печати чека
    @Test
    public void checkReceiptOfBurger(){
        //тестовые значения полей "name", "price", "type"
        String[] testIngredientsName = {"ingredient0", "ingredient1", "ingredient2"};
        IngredientType[] testIngredientsType = {IngredientType.SAUCE,IngredientType.SAUCE,IngredientType.FILLING};
        String testBunsName = "Test bun";
        float testBunsPrice = 50f;
        //генерируем значения полей
        Mockito.when(bun.getPrice()).thenReturn(testBunsPrice);
        Mockito.when(bun.getName()).thenReturn(testBunsName);
        Mockito.when(ingredient0.getPrice()).thenReturn(testIngredientsPrice[0]);
        Mockito.when(ingredient0.getName()).thenReturn(testIngredientsName[0]);
        Mockito.when(ingredient0.getType()).thenReturn(testIngredientsType[0]);
        Mockito.when(ingredient1.getPrice()).thenReturn(testIngredientsPrice[1]);
        Mockito.when(ingredient1.getName()).thenReturn(testIngredientsName[1]);
        Mockito.when(ingredient1.getType()).thenReturn(testIngredientsType[1]);
        Mockito.when(ingredient2.getPrice()).thenReturn(testIngredientsPrice[2]);
        Mockito.when(ingredient2.getName()).thenReturn(testIngredientsName[2]);
        Mockito.when(ingredient2.getType()).thenReturn(testIngredientsType[2]);

        // формируем ожидаемый чек
        String expectedResult = String.format("(==== %s ====)%n= %s %s =%n= %s %s =%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n",
                testBunsName, testIngredientsType[0].toString().toLowerCase(), testIngredientsName[0],
                testIngredientsType[1].toString().toLowerCase(), testIngredientsName[1],
                testIngredientsType[2].toString().toLowerCase(), testIngredientsName[2],testBunsName, testBurgerPrice);

        Assert.assertEquals("Произошла ошибка при формировании чека за бургер", expectedResult, burger.getReceipt());
    }

}
