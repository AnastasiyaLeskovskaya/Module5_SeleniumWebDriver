//package selenium.pageManager;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import java.util.Collection;
//
//public class Finder {
//    private String firstPath;
//    private String secondPath;
//    private String thirdPath;
//
//
//    public Finder(String firstPath, String secondPath, String thirdPath) {
//        this.firstPath = firstPath;
//        this.secondPath = secondPath;
//        this.thirdPath = thirdPath;
//    }
//    public boolean finderElement(WebDriver driver){
//
//        Collection<WebElement> contactList = driver.findElements(By.xpath(firstPath));
//        if (!contactList.isEmpty()){
//            for (WebElement element : contactList) {
//                if ((element.findElement(By.xpath(secondPath)).getText().equalsIgnoreCase(subject+text))
//                        && (element.findElement(By.xpath(thirdPath)).getText().equalsIgnoreCase(addresseeMail))) {
//                    element.click();
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//}



