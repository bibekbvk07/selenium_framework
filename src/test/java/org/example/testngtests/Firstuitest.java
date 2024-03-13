package org.example.testngtests;

import org.example.utilities.CommonFunctions;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Firstuitest {
    WebDriver driver;

    @BeforeTest
    public void beforetest() {
        CommonFunctions.invoke();
        driver = new ChromeDriver(CommonFunctions.getChromeOptions());
        //implicit wait
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
//        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void checkTitle() {

        String title = driver.getTitle();
        System.out.println("The title of the website is: "+title);

        if (title.equals("Swag Labs")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Title didn't match");
        }
    }

    @Test
    public void LoginTest(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test(dependsOnMethods = "LoginTest")
    public void clickImg(){
        String xpath = "(//img[@class='inventory_item_img']/../../../following-sibling::div/div/a/img[@class='inventory_item_img'])[5]";
        driver.findElement(By.xpath(xpath)).click();
    }


    @Test(dependsOnMethods = "LoginTest")
    public void test2(){
        Map<String, String> item_price_collection = new HashMap<>();

        for (int i = 1; i <=3 ; i++) {

            String product_name = driver.findElement(By.xpath("(//div[@id='inventory_container']//div[@class='inventory_item'])["+i+"]//div[@class='inventory_item_name ']")).getText();

            String product_price = driver.findElement(By.xpath("(//div[@id='inventory_container']//div[@class='inventory_item'])["+i+"]//div[@class='inventory_item_price']")).getText();
            item_price_collection.put(product_name, product_price);
            String add_to_cart_xpath = "(//div[@id='inventory_container']//div[@class='inventory_item'])["+i+"]//button";
            driver.findElement(By.xpath(add_to_cart_xpath)).click();
        }

        String items_in_cart = driver.findElement(By.className("shopping_cart_badge")).getText();
        int total_items = Integer.parseInt(items_in_cart);

        System.out.println("Total items in cart: "+items_in_cart);

        Assert.assertEquals(total_items, 3);

        driver.findElement(By.className("shopping_cart_link")).click();

        List<WebElement> items = driver.findElements(By.xpath("//div[@class='cart_list']//div[@class='cart_item']"));

        Assert.assertEquals(items.size(), 3, "Expected total 3 items to present but actually "+items.size()+" are present.");

        for (int i = 1; i <= items.size() ; i++) {
            String item_name = driver.findElement(By.xpath("//div[@class='cart_item']["+i+"]//div[@class='inventory_item_name']")).getText();

            String item_price = driver.findElement(By.xpath("//div[@class='cart_item']["+i+"]//div[@class='inventory_item_price']")).getText();

            String expected_price = item_price_collection.get(item_name);

            if(expected_price == null){
                Assert.fail("Expected product "+item_name+" to be present in cart but not present");
            }else{
                Assert.assertEquals(item_price, expected_price, "Actual & expected price don't match");
            }
        }
    }

    @Test(dependsOnMethods = "LoginTest")
    public void test3(){
        Map<String, String> itemCollections = new HashMap<>();
        for (int i = 1; i <=6; i++){
            String product_name = driver.findElement(By.xpath("(//div[@class='inventory_list']//div[@class='inventory_item'])["+i+"]//div[@class='inventory_item_name ']" )).getText();
            String item_price = driver.findElement(By.xpath("(//div[@class='inventory_list']//div[@class='inventory_item'])["+i+"]//div[@class='inventory_item_price']")).getText();

            itemCollections.put(product_name, item_price);

            driver.findElement(By.xpath("(//div[@class='inventory_container']//div[@class='inventory_item'])["+i+"]//div[@class='pricebar']//button ")).click();
        }
        // Print the HashMap
        System.out.println("HashMap contents: ");
        for (Map.Entry<String, String> entry: itemCollections.entrySet()){
            String productName = entry.getKey();
            String price = entry.getValue();

            System.out.println(productName + ": "+ price);
        }
        int cart_Items = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
        System.out.println(cart_Items);

        Assert.assertEquals(cart_Items,6);

        driver.findElement(By.xpath("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']")).click();

//      driver.findElement(By.className("shopping_cart_link")).click();

        List<WebElement> itemList = driver.findElements(By.xpath("(//div[@class='cart_list']//div[@class='cart_item'])"));

        Assert.assertEquals(itemList.size(), 6);

        for (int i = 1; i <= itemList.size(); i++){
            String product_name = driver.findElement(By.xpath("(//div[@class='cart_item']//div[@class='inventory_item_name'])["+i+"]")).getText();
            String product_price = driver.findElement(By.xpath("(//div[@class='cart_item']//div[@class='inventory_item_price'])["+i+"]")).getText();

            String expectedPrice = itemCollections.get(product_name);

            if (expectedPrice == null){
                Assert.fail("Not Present!");
            }else{
                Assert.assertEquals(product_price, expectedPrice,"Actual and Expected Price do not match");
            }
        }

        driver.findElement(By.xpath("//div[@class='cart_footer']//button[@id='checkout']")).click();

        driver.findElement(By.id("first-name")).sendKeys("Bibek");
        driver.findElement(By.id("last-name")).sendKeys("Shrestha");
        driver.findElement(By.id("postal-code")).sendKeys("56001");

        driver.findElement(By.id("continue")).click();

        List<WebElement> checkoutList = driver.findElements(By.xpath("(//div[@id='checkout_summary_container']//div[@class='cart_list']//div[@class='cart_item'])"));
        Assert.assertEquals(checkoutList.size(), 6);

        double checkoutTotal = 0.0;

        for (int i = 1; i <= checkoutList.size(); i++){
            String product = driver.findElement(By.xpath("(//div[@class='cart_item']//div[@class='inventory_item_name'])["+i+"]")).getText();
            String price = driver.findElement(By.xpath("(//div[@class='cart_item']//div[@class='item_pricebar'])["+i+"]")).getText();

            if (itemCollections.get(product) == null){
                Assert.fail("Not Present!");
            }else {
                Assert.assertEquals(price, itemCollections.get(product), "Actual and Expected Price do not match");
            }

            checkoutTotal += Double.parseDouble(price.substring(1));
        }

        // Total Price Validation
        double itemTotal = Double.parseDouble((driver.findElement(By.className("summary_subtotal_label")).getText()).replaceAll("[^\\d.]",""));

        Assert.assertEquals(checkoutTotal, itemTotal, "Price doesn't match!");

        // Total tax
        double totalTax = Double.parseDouble((driver.findElement(By.className("summary_tax_label")).getText()).replaceAll("[^\\d.]",""));

        double Total = Double.parseDouble((driver.findElement(By.className("summary_total_label")).getText()).replaceAll("[^\\d.]",""));

        Assert.assertEquals((itemTotal+totalTax), Total, "Total Price doesn't match");

    }



    @AfterTest
    public void aftertest() {
        driver.quit();
    }

}
