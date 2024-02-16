package org.example.testngtests;

import org.testng.annotations.*;

public class FirstTest {

    @BeforeSuite
    public void beforesuite(){
        System.out.println("beforesuite");
    }

    @BeforeTest
    public void beforetest(){
        System.out.println("beforetest");
    }

    @BeforeClass
    public void beforeclass(){
        System.out.println("beforeclass");
    }

    @BeforeMethod
    public void beforemethod(){
        System.out.println("beforemethod");
    }

    // with enabled = false the test will not be executed
    @Test (priority = 2, enabled = false)
    public void test1(){
        System.out.println("test1");
    }

    // With set prority the test will execute according to the priority set,
    // and invocationCount will run the test number of times provided
    @Test (priority = 1, invocationCount = 2)
    public void test2(){
        System.out.println("test2");
    }

    @AfterMethod
    public void aftermethod(){
        System.out.println("aftermethod");
    }

    @AfterClass
    public void afterclass(){
        System.out.println("afterclass");
    }

    @AfterTest
    public void aftertest(){
        System.out.println("aftertest");
    }

    @AfterSuite
    public void aftersuite(){
        System.out.println("aftersuite");
    }

}
