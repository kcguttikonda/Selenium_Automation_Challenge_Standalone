import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleSite
{

    public static void main (String args[])
    {
        String nameToEnter, commentToEnter, emailToEnterValid, emailToEnterInvalid,  ErrorMessage, enteredComment, enteredName;
        commentToEnter = RandomStringUtils.randomAlphabetic(64);
        nameToEnter = RandomStringUtils.randomAlphabetic(5);
        emailToEnterValid = RandomStringUtils.randomAlphanumeric(10);
        emailToEnterValid = emailToEnterValid + "@email.com";
        emailToEnterInvalid = RandomStringUtils.randomAlphanumeric(7);
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://store.demoqa.com");
        System.out.print("Current Page Title :" + driver.getTitle());
        driver.findElement(By.linkText("Sample Page")).click();
        System.out.print("\n\nCurrent Page Title:" + driver.getTitle() + "\n");

        //Enter Invalid Email Address in the Sample-Page
        WebElement description = driver.findElement(By.id("comment"));
        WebElement name = driver.findElement(By.id("author"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement Submit = driver.findElement(By.id("submit"));
        description.sendKeys(commentToEnter);
        email.sendKeys(emailToEnterInvalid);
        name.sendKeys(nameToEnter);
        Submit.click();
        System.out.println("\nDone, submitting the form with invalid email id and the page returned following error: ");


        //Verify error message in the Error Page
        WebElement error = driver.findElement(By.xpath("//body[@id='error-page']/p[2]"));
        WebElement back = driver.findElement(By.linkText("« Back"));
        ErrorMessage = error.getText();
        back.click();
        System.out.println("\n" + ErrorMessage);
        System.out.print("\n" + "CurrentPageTitle:" + driver.getTitle() + "\n");
        System.out.println("\nDone, verifying the error message");




        //Enter valid Email Address in the Sample-Page
        description = driver.findElement(By.id("comment"));
        name = driver.findElement(By.id("author"));
        email = driver.findElement(By.id("email"));
        Submit = driver.findElement(By.id("submit"));
        description.clear();
        description.sendKeys(commentToEnter);
        email.sendKeys(emailToEnterValid);
        name.sendKeys(nameToEnter);
        Submit.click();
        System.out.println("\n\nDone, submitting the form with valid email id");


        //Verify the EnteredComment
        WebElement entered_comment = driver.findElement(By.className("comment-body"));
        WebElement entered_firstname = driver.findElement(By.className("fn"));
        enteredComment = entered_comment.getText();
        enteredName = entered_firstname.getText();
        System.out.println("\nComment Entered by the Script : " +commentToEnter);
        System.out.println("\nComment found in the SampleSite by the Script : " +enteredComment);
        if (enteredComment.equals(commentToEnter) && enteredName.equals(nameToEnter) )
        {
            System.out.println("\nDone verifying the posted comment");
            System.out.println("\nTestCasePassed");
        }
        else {
            //System.out.println("Done verifying the posted comment");
            System.out.print("\nTest Case Failed");
        }

        driver.close();
    }

}
