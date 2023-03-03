package seliumTests;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;
/*
* Krav
För godkänt betyg (G)
1. Alla metoder i service-klassen samt våra egenskrivna metoder i repositoryt testas med lämpliga
enhetstester (observera att det ibland kan vara lämpligt med fler enhetstester av samma metod)
2. Mockning tillämpas där så är lämpligt, dvs. Åtminstone då serviceklassen enhetstestas ska
repositoryt vara mockat.
3. Konfiguration mot Github Actions som automatiskt bygger och kör alla tester för varje gång
koden pushas.
4. Testning med Selenium (valfri frontend-webbapp som läser ut data från projekt-API:et):
a. Kontrollera att webbplatsens titel stämmer
b. Kolla att det totala antalet produkter stämmer
c. Kontrollera att priset blir rätt på minst 3 produkter
För väl godkänt betyg (VG)
Alla krav för godkänt, samt:
1. Koden ska vara lättläst och följa SOLID-principerna där så är tillämpligt samt följa best practice
för enhetstester och clean code.
2. Du kan klart och tydligt förklara syftet med testerna, vad de testar och hur de fungerar
tillsammans med API:et.
3. Testning med Selenium (valfri frontend-webbapp som läser ut data från projekt-API:et):
a. Skriv ut alla kategorier och kolla att de har rätt namn
b. Kontrollera priset på ytterligare 3 produkter
c. Kontrollera att bilderna skrivs ut/visas till minst 3 produkter
d. Kontrollera att bilden har rätt src-attribut på minst 3 produkter
e. Kontrollera att alla produkter som skrivs ut har rätt namn
f. Gör produktkategorierna klickbara och testa att rätt antal produkter visas vid klick på
respektive kategori
*/
public class SeleniumTests {
    //G-nivå tester
    WebDriver driver = new ChromeDriver();

    @BeforeEach
void init(){
    driver.get("https://onlineoasisfrontend.netlify.app/");
}
@AfterEach
void quit(){
    driver.quit();
}
    @Test
    @DisplayName("Kontrollerar att webbplatsens titel stämmer")
    public void checkTitle(){

        Assertions.assertEquals("Online Oasis",driver.getTitle(),"Titeln stämmer inte med förväntat");
        Assertions.assertNotEquals("Website",driver.getTitle(),"Titeln stämmer inte överens med förväntat");
    }
    @Test
    @DisplayName("Kolla att det totala antalet produkter stämmer")
    void numberOfProductsShouldBeTwenty(){
        List<WebElement> products = driver.findElements(By.className("productItem"));
        Assertions.assertEquals(20, products.size(), "Antalet produkter stämmer inte");
        Assertions.assertNotEquals(21,products.size(), "Antalet produkter stämmer inte");
    }
    @Test
    @DisplayName("Kontrollera att priset blir rätt på produkt 1")
    void checkThatPriceOfProducOnetIsCorrect(){

        WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"products\"]/div[1]/div/div/div/div/p/b"));
        String productPriceText = productPrice.getText();
        Assertions.assertEquals("$"+109.95,productPriceText,"Priset verkar inte stämma överens");
    }
    @Test
    @DisplayName("Kontrollera att priset blir rätt på produkt 2")
    void checkThatPriceOfProductTwoIsCorrect(){
        WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"products\"]/div[2]/div/div/div/div/p/b"));
        String productPriceText = productPrice.getText();
        Assertions.assertEquals("$"+22.3,productPriceText,"Priset verkar inte stämma överens");
    }
    @Test
    @DisplayName("Kontrollera att priset blir rätt på produkt 3")
    void checkThatPriceOfProducThreetIsCorrect(){
        WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"products\"]/div[3]/div/div/div/div/p/b"));
        String productPriceText = productPrice.getText();
        Assertions.assertEquals("$"+55.99,productPriceText,"Priset verkar inte stämma överens");
    }

//VG nivå tester
@Test
@DisplayName("Kolla att men's clothes har rätt namn")
void checkIfMensClothesCategoryIsCorrect(){
    WebElement categoryButton = driver.findElement(By.xpath("//*[@id=\"men\"]"));
    String text = categoryButton.getText();
    Assertions.assertEquals("men's clothes",text.toLowerCase(), "Texten verkar inte stämma överens med kategorin");
}
    @Test
    @DisplayName("Kollar att women's clothes har rätt namn")
    void checkIfWomensClothesCategoryIsCorrect(){
        WebElement categoryButton = driver.findElement(By.xpath("//*[@id=\"women\"]"));
        String text = categoryButton.getText();
        Assertions.assertEquals("women's clothes",text.toLowerCase(), "Texten verkar inte stämma överens med kategorin");
    }
    @Test
    @DisplayName("Kollar att jewelery har rätt namn")
    void checkIfJeweleryCategoryIsCorrect(){
        WebElement categoryButton = driver.findElement(By.xpath("//*[@id=\"jewelery\"]"));
        String text = categoryButton.getText();
        Assertions.assertEquals("jewelery",text.toLowerCase(), "Texten verkar inte stämma överens med kategorin");
    }
    @Test
    @DisplayName("Kollar att electronics har rätt namn")
    void checkIfElectronicsCategoryIsCorrect(){
        WebElement categoryButton = driver.findElement(By.xpath("//*[@id=\"electronics\"]"));
        String text = categoryButton.getText();
        Assertions.assertEquals("electronics",text.toLowerCase(), "Texten verkar inte stämma överens med kategorin");
    }
    @Test
    @DisplayName("Kontrollera att priset blir rätt på produkt 4")
    void checkThatPriceOfProductFourtIsCorrect(){
        WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"products\"]/div[4]/div/div/div/div/p/b"));
        String productPriceText = productPrice.getText();
        Assertions.assertEquals("$" + 15.99, productPriceText,"Priset verkar inte stämma överens");
    }
    @Test
    @DisplayName("Kontrollera att priset blir rätt på produkt 5")
    void checkThatPriceOfProductFivetIsCorrect(){
        WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"products\"]/div[5]/div/div/div/div/p/b"));
        String productPriceText = productPrice.getText();
        Assertions.assertEquals("$" + 695, productPriceText,"Priset verkar inte stämma överens");
    }
    @Test
    @DisplayName("Kontrollera att priset blir rätt på produkt 6")
    void checkThatPriceOfProductSixIsCorrect(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"products\"]/div[6]/div/div/div/div/p/b"));
        String productPriceText = productPrice.getText();
        Assertions.assertEquals("$" + 168, productPriceText,"Priset verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollera att bilderna skrivs ut/visas för produkt 1")
    void imageOfProductOneShouldBeVisible(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productImage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"products\"]/div[1]/div/img[1]")));

        Assertions.assertTrue(productImage.isDisplayed(),"Bilden verkar inte läsas in");
        driver.quit();
        //WebElement productImage = driver.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[1]/div/img"));
        //*[@id="productsContainer"]/div/div[1]/div/img
    }
    @Test
    @DisplayName("Kontrollera att bilderna skrivs ut/visas för produkt 2")
    void imageOfProductTwoShouldBeVisible(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productImage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"products\"]/div[2]/div/img[1]")));

        Assertions.assertTrue(productImage.isDisplayed(),"Bilden verkar inte läsas in");
        driver.quit();
        //WebElement productImage = driver.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[1]/div/img"));
        //*[@id="productsContainer"]/div/div[1]/div/img
    }
    @Test
    @DisplayName("Kontrollera att bilderna skrivs ut/visas för produkt 3")
    void imageOfProductThreeShouldBeVisible(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productImage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"products\"]/div[3]/div/img[1]")));

        Assertions.assertTrue(productImage.isDisplayed(),"Bilden verkar inte läsas in");
        driver.quit();
        //WebElement productImage = driver.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[1]/div/img"));
        //*[@id="productsContainer"]/div/div[1]/div/img
    }
    @Test
    @DisplayName("Kontrollera att bilden har rätt src-attribut på produkt 1")
    void checkIProductOneHasRightSrcAttribute(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productImage = driver.findElement(By.xpath("//*[@id=\"products\"]/div[1]/div/img[1]"));
        String sqlSrc = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg";
        String frontEndSrc = productImage.getAttribute("src");
        Assertions.assertEquals(sqlSrc,frontEndSrc,"src attributen verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollera att bilden har rätt src-attribut på produkt 2")
    void checkIProductOTwoHasRightSrcAttribute(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productImage = driver.findElement(By.xpath("//*[@id=\"products\"]/div[2]/div/img[1]"));
        String sqlSrc = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg";
        String frontEndSrc = productImage.getAttribute("src");
        Assertions.assertEquals(sqlSrc,frontEndSrc,"src attributen verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollera att bilden har rätt src-attribut på produkter 1")
    void checkIProductThreeHasRightSrcAttribute(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productImage = driver.findElement(By.xpath("//*[@id=\"products\"]/div[3]/div/img[1]"));
        String sqlSrc = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg";
        String frontEndSrc = productImage.getAttribute("src");
        Assertions.assertEquals(sqlSrc,frontEndSrc,"src attributen verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 1 har rätt namn")
    void checkIfProductOneHasTheCorrectName(){
    WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
    WebElement productNameElement = driver.findElement(By.id("title1"));
    String productNameText = productNameElement.getText();
    String productNameTextExpected = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops";
    Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 2 har rätt namn")
    void checkIfProductTwoHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title2"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "Mens Casual Premium Slim Fit T-Shirts";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 3 har rätt namn")
    void checkIfProductThreeHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title3"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "Mens Cotton Jacket";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }@Test
    @DisplayName("Kontrollerar att produkt 4 har rätt namn")
    void checkIfProductFourHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title4"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "Mens Casual Slim Fit";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 5 har rätt namn")
    void checkIfProductFiveHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title5"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 6 har rätt namn")
    void checkIfProductSixHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title6"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "SolGold Petite Micropave";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 7 har rätt namn")
    void checkIfProductSevenHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title7"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "White Gold Plated Princess";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 8 har rätt namn")
    void checkIfProductEightHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title8"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "Pierced Owl Rose Gold Plated Stainless Steel Double";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 9 har rätt namn")
    void checkIfProductNineHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title9"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "WD 2TB Elements Portable External Hard Drive - USB 3.0";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();

    }
    @Test
    @DisplayName("Kontrollerar att produkt 10 har rätt namn")
    void checkIfProductTenHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title10"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 11 har rätt namn")
    void checkIfProductElevenHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title11"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III 2.5";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 12 har rätt namn")
    void checkIfProductTwelveHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title12"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 13 har rätt namn")
    void checkIfProductThirteenHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title13"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 14 har rätt namn")
    void checkIfProductFourteenHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title14"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) – Super Ultraw Screen QLED";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 15 har rätt namn")
    void checkIfProductFifteenHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title15"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "BIYLACLESEN Women's 3-in-1 Snowboard Jacket Winter Coats";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 16 har rätt namn")
    void checkIfProductSixteenHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title16"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 16 har rätt namn")
    void checkIfProductSeventeenHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title17"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "Rain Jacket Women Windbreaker Striped Climbing Raincoats";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 18 har rätt namn")
    void checkIfProductEighteenHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title18"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "MBJ Women's SolShort Sleeve Boat Neck V";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 19 har rätt namn")
    void checkIfProductNineteenHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title19"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "Opna Women's Short Sleeve Moisture";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att produkt 20 har rätt namn")
    void checkIfProductTwentyHasTheCorrectName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://onlineoasisfrontend.netlify.app/");
        WebElement productNameElement = driver.findElement(By.id("title20"));
        String productNameText = productNameElement.getText();
        String productNameTextExpected = "DANVOUY Womens T Shirt Casual Cotton Short";
        Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        driver.quit();
    }
    @Test
    @DisplayName("Kontrollerar att alla 20 produkter har rätt namn")
    void checkAllNames(){
String[] names = {"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
        "Mens Casual Premium Slim Fit T-Shirts",
        "Mens Cotton Jacket",
        "Mens Casual Slim Fit",
        "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
        "SolGold Petite Micropave",
        "White Gold Plated Princess",
        "Pierced Owl Rose Gold Plated Stainless Steel Double",
        "WD 2TB Elements Portable External Hard Drive - USB 3.0",
        "SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s",
        "Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III 2.5",
        "WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive",
        "Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin",
        "Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) – Super Ultraw Screen QLED",
        "BIYLACLESEN Women's 3-in-1 Snowboard Jacket Winter Coats",
        "Rain Jacket Women Windbreaker Striped Climbing Raincoats",
        "MBJ Women's SolShort Sleeve Boat Neck V",
        "Opna Women's Short Sleeve Moisture",
        "DANVOUY Womens T Shirt Casual Cotton Short"};
        for (int i = 0; i < names.length; i++) {
            WebElement productNameElement = driver.findElement(By.id("title" +(i+1)));
            String productNameText = productNameElement.getText();
            String productNameTextExpected = names[i];
            Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
        }
    }
}
