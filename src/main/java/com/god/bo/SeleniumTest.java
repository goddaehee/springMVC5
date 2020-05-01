package com.god.bo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class SeleniumTest {
    private WebDriver driver;
    private WebElement webElement;

    //Properties
    public static final String WEB_DRIVER_CHROME = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_CHROME_PATH = "C:/work/selenium/chromedriver_win32/chromedriver.exe";
    public static final String WEB_DRIVER_FIREFOX = "webdriver.gecko.driver";
    public static final String WEB_DRIVER_FIREFOX_PATH = "C:/work/selenium/geckodriver-v0.26.0-win64/geckodriver.exe";
    public static final String WEB_DRIVER_IE = "webdriver.ie.driver";
    public static final String WEB_DRIVER_IE_PATH = "C:/work/selenium/IEDriverServer_x64_3.9.0/IEDriverServer.exe";

    //crolling URL
    private String base_url;

    public SeleniumTest() {
        super();
        //base_url = "https://www.naver.com";
        base_url = "https://shop.hansalim.or.kr/om/main.do";
        // base_url = "http://www.e-himart.co.kr/app/search/totalSearch?query=%EB%A7%88%EC%8A%A4%ED%81%AC%20kf94&optChk=&FROM=WEBTOP";
    }

    public static void main(String[] args) {
        SeleniumTest seleniumTest = new SeleniumTest();

        // loginTest
        seleniumTest.chromeInit();
        seleniumTest.hanTest();
        //seleniumTest.navLogin();
        //seleniumTest.crawl();

        //System.out.println("파폭시작~");

        //seleniumTest.firefoxInit();
        //seleniumTest.crawl();

        //System.out.println("IE시작~");
        //seleniumTest.ieInit();
        //seleniumTest.crawl();
    }

    public void chromeInit(){
        //System Property SetUp
        System.setProperty(WEB_DRIVER_CHROME, WEB_DRIVER_CHROME_PATH);

        //Driver SetUp
        //No option
        //driver = new ChromeDriver();

        ChromeOptions options = new ChromeOptions();
        options.setCapability("ignoreProtectedModeSettings", true);
        driver = new ChromeDriver(options);
    }

    public void firefoxInit(){
        //System Property SetUp
        System.setProperty(WEB_DRIVER_FIREFOX, WEB_DRIVER_FIREFOX_PATH);

        //Driver SetUp
        //driver = new FirefoxDriver();
    }

    public void ieInit(){
        //System Property SetUp
        System.setProperty(WEB_DRIVER_IE, WEB_DRIVER_IE_PATH);
        //DesiredCapabilities cappabilities = DesiredCapabilities.internetExplorer();

        //Driver SetUp
        //driver = new InternetExplorerDriver();

        //cappabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        /*cappabilities.setCapability("platform", "WIN10");*/
        /*cappabilities.setCapability("version", "11");*/
        /*cappabilities.setCapability("browserName", "internet explorer");
        cappabilities.setCapability("ignoreProtectedModeSettings",1);
        cappabilities.setCapability("nativeEvents","false");
        cappabilities.setCapability("requireWindowFocus","true");*/

        //cappabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, base_url);
        //cappabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

        //driver = new InternetExplorerDriver();
    }

    public void crawl() {

        try {
            // Maximize the browser window to fit into screen
            driver.manage().window().maximize();

            //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
            driver.get(base_url);

            System.out.println(driver.getPageSource());
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            driver.close();
        }
    }

    public void navLogin() {
        try {
            //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
            driver.get(base_url);

            //iframe으로 구성된 곳은 해당 프레임으로 전환시킨다.
            //driver.switchTo().frame(driver.findElement(By.className("ico_local_login")));

            webElement = driver.findElement(By.className("ico_local_login"));
            webElement.click();

            Thread.sleep(2000);

            //iframe 내부에서 id 필드 탐색
            webElement = driver.findElement(By.id("id"));
            String daum_id = "아이디";
            webElement.sendKeys(daum_id);

            //iframe 내부에서 pw 필드 탐색
            webElement = driver.findElement(By.id("pw"));
            String daum_pw = "비번";
            webElement.sendKeys(daum_pw);

            //로그인 버튼 클릭
            Thread.sleep(2000);

            webElement = driver.findElement(By.id("log.login"));
            webElement.submit();

            Thread.sleep(20000);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            driver.close();
        }
    }

    public void hanTest() {
        try {
            //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
            driver.get(base_url);

            //iframe으로 구성된 곳은 해당 프레임으로 전환시킨다.
            //driver.switchTo().frame(driver.findElement(By.className("ico_local_login")));

            webElement = driver.findElement(By.className("search-text"));
            String searchKeyword = "한뿌리";
            webElement.sendKeys(searchKeyword);
            //webElement.submit();
            webElement = driver.findElement(By.className("btn-search"));
            webElement.click();

            Thread.sleep(2000);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            driver.close();
        }
    }
}