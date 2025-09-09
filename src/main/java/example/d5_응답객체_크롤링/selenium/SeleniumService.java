package example.d5_응답객체_크롤링.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@Service // 스프링 컨테이너(메모리) 빈(객체) 등록 // IoC --> 스프링이 대신 객체를 생성해줌(의존성을 주입). 스프링 MVC패턴 서비스 레이어에서 비지니스 로직을 담당
public class SeleniumService {
    
    /* 다음 날씨 정보 크롤링 */
    public Map<String,String> task1(){
        // 1-1. 크롬 설치
        WebDriverManager.chromedriver().setup();

        // 1-2. 크롬 옵션 객체 생성
        ChromeOptions chromeOptions = new ChromeOptions();

        // **** 크롬을 사용하지만, 크롬의 브라우저 창은 띄우지 않는다. 내부적으로 동작하는 설정(꼭 사용하는게 좋음)
        chromeOptions.addArguments("--headless=new", "--disable--gpu");

        // 1-3. 크롬 옵션을 웹드라이버(셀레니움)에 객체 생성
        WebDriver webDriver = new ChromeDriver( chromeOptions );

        // 1-4. 크롤링할 웹주소 기입
        String URL = "https://weather.daum.net/";

        // 1-5. 셀레니움(웹드라이버)으로 웹주소 가져오기
        webDriver.get( URL ); // 매개변수를 받을 수 있어서 변수 처리함.

        // 1-6. 셀레니움(웹드라이버) 잠시 대기( 날씨 데이터를 가져와야해서 잠시 대기!)--> new WebDriver
        // 동적페이지는 해당 페이지의 패치가(js) 정보를 가져올 때까지 기다린 후 데이터를 가져온다.
        WebDriverWait wait = new WebDriverWait( webDriver , Duration.ofSeconds( 3 ) );

        // 1-7. 대기 후 크롤링할 데이터 돔구조 분석하여 식별자 가져오기
            // 지역명: .info_location .tit_location
            WebElement location = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".info_location .tit_location")));
            System.out.println("location = " + location);
            // 온도 : .info_weather .num_deg
            WebElement temp = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".info_weather .num_deg")));
            System.out.println("temp = " + temp);
            // 상태 :  .sub_info .txt_sub
            WebElement state = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".info_weather .txt_sub")));
            System.out.println("state = " + state);
        return null;
    }//func end
    
}//class end
