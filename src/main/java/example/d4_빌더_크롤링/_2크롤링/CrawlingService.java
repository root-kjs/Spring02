package example.d4_빌더_크롤링._2크롤링;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrawlingService {
    // [01] 한국부동산 뉴스 크롤링 접금허용여부 확인 : https://www.karnews.or.kr/robots.txt
    public List<String> task1() {
        List<String> list = new ArrayList<>();
        try {
                // 1.2. 크롤링할 웹페이지 주소
                String URL = "https://www.karnews.or.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm";
                // 1.3. Jsoup : import org.jsoup.nodes.Document; 을 이용하여
                // Document document = Jsoup.connect( 크롤링할 주소 ).get(); 일반예외 발생( I/O Exeption )
                // JS    --> docunment.querySelect("css선택자");
                // Jsoup --> document.select(".tile > a");
                Document document = Jsoup.connect(URL).get();
                Elements aList = document.select("h4.titles > a");
                // System.out.println("aList = " + aList); //!확인용
                // 1.4. 가져온 마크업을 반복하여 텍스트만 추출
                for( Element a : aList ) {
                    String title = a.text();
                    if( title.isBlank() ) continue; // 내용이 없다면 다음 반복
                    list.add( title );
                }// for2 end
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return list;
    }//func end

    // [02] YES24 >  https://www.yes24.com/robots.txt
    // 베스트셀러 > 상품 정보 > https://www.yes24.com/product/category/daybestseller?categoryNumber=001&pageNumber=2&pageSize=24&type=day
    public List<Map<String, String>> task2(){
        List<Map<String,String>> list = new ArrayList<>();
        try {
            // 1-1. 페이지마다~1~3) 책정보 가져오기 반복
            for( int page = 1; page <= 3; page++ ){
                // 1-2. 웹스크롤링할 주소 + 페이지 변수넣어 데이터 더 가져오기
                String URL = "https://www.yes24.com/product/category/daybestseller?categoryNumber=001&pageNumber="+page+"&pageSize=24&type=day";

                // 1-3. jsoup으로 URL 연결
                Document document = Jsoup.connect( URL ).get();
                // 1-4. 해당 웹페이지에서 가져올 데이터의 식별자(.css) 확인
                    // 도서명 : .info_name > .gd_name / 도서가격 : .info_price > .txt_num > .yes_b
                    // 도서 이미지 : img_bdr
                Elements nameList = document.select( ".info_name .gd_name ");            // 도서명 리스트
                Elements priceList = document.select(".info_price > .txt_num > .yes_b"); // 도서가격 리스트
                Elements imgList = document.select(".img_bdr .lazy"); // 도서 이미지 --> 도서 이미지 경로(이미지 속성명 : data-original)

                // 1-5. 반복문으로 크롤링한 책정보들 조합
                    // 1) 일반 인덱스 포문으로 각자 정보를 추출한 다음 2) 맵으로 조합하여 3) 리스트로 저장)
                for ( int i = 0; i < nameList.size(); i++ ){
                    String name = nameList.get(i).text();
                    String price = priceList.get(i).text();
                    String img = imgList.get(i).attr("data-original");

                    Map<String,String> map = new HashMap<>();
                    map.put( "name" , name );
                    map.put( "price" , price );
                    map.put( "img" , img );

                    list.add( map );
                }//for2 end
            }//page for1 end
        }catch ( Exception e ){
            System.out.println("e = " + e);
        }
        return list;
    }//func end

    // [03] 다음날씨 https://weather.daum.net/robots.txt ===> 동적 페이지라서 Jsoup 안됨!******************************
    public Map<String, String> task3(){
        // 1. 날씨 정보를 저장할 맵
        Map<String, String> map = new HashMap<>();
        try {
            // 2. 크롤링할 웹주소
            //String URL ="https://weather.daum.net/?location-regionId=AB20010202&weather-cp=kweather";
            String URL = "https://search.naver.com/search.naver?where=nv&sm=top_sug.pre&fbm=0&acr=3&acq=qnvudrn&qdt=0&ie=utf8&query=%EB%B6%80%ED%8F%89%EA%B5%AC+%EB%82%A0%EC%94%A8&ackey=ckflen16";
            
            // https://weather.daum ====> 안됨. Jsoup동적페이지라서....net/?location-regionId=AB20010202&weather-cp=kweather
            // 페이지 소스보기에서 html 없으면 동적 웹페이지 

            // 3. jsoup 연결
            Document document = Jsoup.connect( URL ).get();
            // 4. 가져올 웹페이지 데이터 식별자 연결
                // Element element = document.selectFirst(".info_weather > .num_deg"); // select  반환타입은 Elements // 다음
                 Element element = document.selectFirst(".weather_main .weather_graphic .temperature_text"); // select  반환타입은 Elements // 네아버
                System.out.println("elements = " + element);
        }catch ( Exception e ){
            System.out.println("e = " + e);
        }
        return map;
    }//func end

}// class end
