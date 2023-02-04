import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

@RunWith(Parameterized.class)
public class BunTest {
    private final String name;
    private final float price;
    private Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Test №{index}: name = {0}, price = {1}")
    public static Object[][] dataForTest() {
        return new Object[][]{
                {"Булочка без кунжута", 50},
                {"!@#$%^&*()_+", 34f},
                {"Bun", 43f},
                {"Bun56", 43f},
                {"  gfd", 53f},
                {"fghjklasdfghjklwErtyuiytgtfgfdfghjklasdfghjklwertyuiytgtfgfdfghjklasdfghjklwertyuiytgtfgfdfghjklasdfghjklwertyuiytgtfgfd", 100f},
                {null, 43f},
                {"", 10},
                {"Булочка с кунжутом", 3.4e+38f},
                {"Булочка", 3.4e-38f},
                {"Булочка", -3.4e+38f},
                {"Булочка", 0},
        };
    }

    //Создаём булочку
    @Before
    public void createBun(){
        bun = new Bun(name, price);
    }

    //Проверяем, что метод возвращает целые и дробные значения
    @Test //тест метода getPrice()
    public void getPriceTest() {
        Assert.assertEquals("Ошибка! Значения не совпали.", price, bun.getPrice(), 0f);
    }

    //Проверяем, что возвращается корректное название
    @Test //тест метода getPrice()
    public void getNameTest() {
        Assert.assertEquals("Ошибка! Значения не совпали.", name, bun.getName());
    }

}
