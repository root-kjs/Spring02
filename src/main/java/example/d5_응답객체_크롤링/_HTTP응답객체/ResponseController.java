package example.d5_응답객체_크롤링._HTTP응답객체;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController // http 요청과 응답을 처리하는 클래스
@RequestMapping("/task/day5") // http URL 매핑(연결) 하는 어노테이션
public class ResponseController {
    //------------------------- HTTP 응답객체 --------------
    // public bool t1(){}
    // 기본타입을 참조타입으로 사용하는 클래스들 --> 기본타입은 객체가 아니라서 객체 전근연산자.(점)을 찍을 수 없음, 객체는 기능/함수를 사용할 수 있음
    @GetMapping("/bool")
    public ResponseEntity< Boolean > task1(){
        return ResponseEntity.status( 401 ).body( false ); // 자료응답에 대한 의미(상태)를 부여
    }// func end

    @GetMapping("/int")
    public ResponseEntity< Integer > task2(){
        return ResponseEntity.status( 201 ).body( 1 ); // 요청했지만
    }// func end

    @GetMapping("/string")
    public ResponseEntity< String > task3(){
        return ResponseEntity.status( 201 ).body( "qwe111" ); // 자료응답에 대한 의미(상태)를 부여
    }// func end

    @GetMapping("/void")
    public ResponseEntity< Void > task4(){
        return ResponseEntity.status( 403 ).build(); // build() 반환값이 없다. 접근권한 없음(403)
    }// func end

    @GetMapping("/object")
    public ResponseEntity< Void > task5(){
       try{
           //Integer.parseInt("a");// 강제로 오류 발생
           Map<String, String> map = new HashMap<>(); // 예제 샘플
           map.put("data","sample");
           return ResponseEntity.status( 200 ).build();
       }catch ( Exception e ){
           return ResponseEntity.status( 500 ).build();
       }
    }// func end

}//class end
