package example.d5_응답객체_크롤링.selenium;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task/d5")
@RequiredArgsConstructor// final 생성자 자동 생성
public class SeleniumController {
    
    private final SeleniumService seleniumService; // DI(의존성 주입), 객체 가져오기, 서비스의 메소드를 호출하기 위해

    // 1.
    @GetMapping("/c1")
    public Map<String,String> task1(){
        Map<String,String> result = seleniumService.task1();
        return result;
    }// func end

    // 2. CGV 리뷰 : 무한 스크롤 페이지 크롤링
    @GetMapping("/c2")
    public List<String> task2(){
        List<String> result = seleniumService.task2();
        return result;
    }// func end
    
    
    
    

}//class end
