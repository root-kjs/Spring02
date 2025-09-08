package example.d4_빌더_크롤링._2크롤링;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task/d4")
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    @GetMapping("/craw1")
    public List<String> task1(){
        return crawlingService.task1();
    }//func end

    @GetMapping("/craw2")
    public List<Map<String,String>> task2(){
        return crawlingService.task2();
    }//func end

    @GetMapping("/craw3")
    public Map<String,String> task3(){
        return crawlingService.task3();
    }//func end

}// class end
