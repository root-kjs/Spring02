package example.d4_빌더_크롤링._1빌더패턴;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
// [01] 설계 클래스 ------------------------------------------
@NoArgsConstructor  // 빈 생성자 생성
@AllArgsConstructor // 풀 생성자 생성
@Builder // **** 빌더 패턴
@ToString
class MemberDto{
    // 1.멤버변수 : (메모리)객체의 속성
    private String name;
    private int age;
//    // 2.생성자 : (초기화)객체를 생성할 때 사용되는 초기화 함수
//    MemberDto(){} // 1.기본(빈) 생성자는 매개변수가 없음.
//    MemberDto( String name , int age ){ // 2.전체(풀) 생성자는 모든 매개변수를 가짐.
//        this.name = name;
//        this.age = age;
//
//    }
    // 3.메소드 : (함수)객체의 행동/이벤트/함수

}//class end

// [02] 실행 클래스 ------------------------------------------
public class Exam1 {
    public static void main(String[] args) {
//        // 1. 객체를 생성할때 사용되는 메소드 : new 연산자 뒤로 MemberDto()
//        MemberDto m1 = new MemberDto(); // 1. 기본 생성자
//
//        // 2.
//        MemberDto m2 = new MemberDto( "유재석", 30 ); // 2. 전체 생성자
//
//        // 3. 생성자의 규칙 : 존재하지 않은 객체 생성자 만들기는 불가능(매개변수 안의 멤버변수 종류나 순서가 다른거는 !객체 생성 안됨!)
//        // 생성자는 정해진 매개변수에 따라 사용되어 유연성이 떨어진다.
//        MemberDto m3 = new MemberDto();

        // 4. 롬복 빌더 패턴 **************************************
        // 생성자 유무와 상관없이 메소드로 객체 초기화 가능! 해당 DTO 위에 @Builder 어노테이션 기입
        // 필요한 매개변수만 선택적으로 초기화
        // 주요메소드 :
            // 1) .builder() : 빌더객체 시작점 ----> static 함수 그래서 클래스명으로 함수 호출가능
            // 2) .속성명(초기값) : 지정한 속성명에 초기값 대입
            // 3) .build() : 빌더 객체 끝지점
        // 사용법 : 해당 DTO 위에 @Builder 어노테이션 기입
        // 빌더패턴 사용 ---> DTO를 유연하게 사용하기 위해 사용됨. 매개변수가 많을 경우, 선택적으로 매개변수 사용할 경우, 사용 순서 바꿀경우
            /* 클래스명 변수명 = new 생성자(클래스)명(값1, 값2);*/
            // VS
            /* 클래스명 변수명 = 클래스명.builder().속성명(값).속성명(값).build();*/

        // * static이란? 정적변수/메소드 --> 즉 객체없이 사용되는 변수/메소드
            // 관례적으로 객체는 변수에 저장되므로 !소문자, 클래스는 대문자
        

        MemberDto m5 = MemberDto.builder().build();
        System.out.println("m5 = " + m5); // soutv ---> 가장 가까운 매개변수 값이 나옴

        MemberDto m6 = MemberDto.builder().name("유재석").build();
        System.out.println("m6 = " + m6);

        MemberDto m7 = MemberDto.builder().name("유재석").age(40).build();
        System.out.println("m7 = " + m7);

        MemberDto m8 = MemberDto.builder().age(40).name("유재석").build();
        System.out.println("m8 = " + m8);

        
    }// main end
}//class end














