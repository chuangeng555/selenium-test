import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.html.HTMLFieldSetElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        driver.navigate().to("http://www.esist.org.tw/Database/Search?PageId=0");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Supply and Consumption

        /*
         * Change to gregorian calendar,  month type remain the same
         */
        String gregorianCalenderPath = "//*[@id=\"yearType\"]/option[1]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(gregorianCalenderPath)));
        driver.findElement(By.xpath(gregorianCalenderPath)).click();


        /* Find this start month Select path tag,
        * get all Options tag under them,
        * Choose the earliest month / year and assign to startMonthPath ,
        * endMonth by default latest
        * */

        String startMonthPath = "//*[@id=\"ddl_s_m\"]";

        Select startSelect = new Select(driver.findElement(By.xpath(startMonthPath)));
        List<WebElement> allStartOptions = startSelect.getOptions();
        final String startDate = "10806"; //actual --> 10601 == Jan 2017
        //hardcode as that is the start based on requirements
        WebElement start = allStartOptions.stream().filter(o -> startDate.equals(o.getAttribute("value"))).findFirst().orElse(null);
//        System.out.println(start.getText());
        if (start == null) {
            System.out.println("Start cant be found");
            driver.close();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(start));
            start.click();
        }

        /*
         * Find Supply checkbox and click it,
         * Click on select all
         */
        String supplyPath = "//*[@id=\"rightBox\"]/article[1]/section/label";
        String supplySelectAllPath = "//*[@id=\"rightBox\"]/article[1]/div/div[1]/label";

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(supplyPath)));
        driver.findElement(By.xpath(supplyPath)).click();
//        System.out.println(driver.findElement(By.xpath(supplyPath)).getText());


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(supplySelectAllPath)));
        driver.findElement(By.xpath(supplySelectAllPath)).click();
//        System.out.println(driver.findElement(By.xpath(supplySelectAllPath)).getText());


        /*
         * Find Crude oil and petroleum products checkbox,
         * Click on select all
         */

        String oilPath = "//*[@id=\"leftBox\"]/article[3]/section/label";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oilPath)));
        driver.findElement(By.xpath(oilPath)).click();
//        System.out.println(driver.findElement(By.xpath(oilPath)).getText());


        String oilSelectAllPath = "//*[@id=\"leftBox\"]/article[3]/div/div[1]/label";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oilSelectAllPath)));
        driver.findElement(By.xpath(oilSelectAllPath)).click();
//        System.out.println(driver.findElement(By.xpath(oilSelectAllPath)).getText());


        /*
         * Find Domestic energy consumption (), Click on select all
         */
        String domesticConsumptionPath = "//*[@id=\"rightBox\"]/article[3]/section/label";
        String domesticConsumptionSelectAllPath = "//*[@id=\"rightBox\"]/article[3]/div/div[1]/label";

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(domesticConsumptionPath)));
        driver.findElement(By.xpath(domesticConsumptionPath)).click();
//        System.out.println(driver.findElement(By.xpath(domesticConsumptionPath)).getText());


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(domesticConsumptionSelectAllPath)));
        driver.findElement(By.xpath(domesticConsumptionSelectAllPath)).click();
//        System.out.println(driver.findElement(By.xpath(domesticConsumptionSelectAllPath)).getText());


        /*
        * Submit query
         */
        String submitButtonPath = "//*[@id=\"data_accordion\"]/div[2]/div[2]/button";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(submitButtonPath)));
        driver.findElement(By.xpath(submitButtonPath)).click();

        /*
         * Download Excel
         */
        By downloadExcelPath = By.xpath("/html/body/div[1]/div/div/section/ul/li[1]/a");
        wait.until(ExpectedConditions.presenceOfElementLocated(downloadExcelPath)).click();
//        System.out.println(driver.findElement(downloadExcelPath).getAttribute("href"));
//        String url = driver.findElement(downloadExcelPath).getAttribute("href");
//        try {
//            URL urlCsv = new URL(url);
//            URLConnection urlConn = urlCsv.openConnection();
//            InputStreamReader inputCSV = new InputStreamReader(
//                    ((URLConnection) urlConn).getInputStream());
//            BufferedReader br = new BufferedReader(inputCSV);
//
//            System.out.println(br.readLine());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        driver.quit();
    }
}
