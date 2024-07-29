package hello.springmvc.basic.request;

import hello.springmvc.basic.requestmapping.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;


@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        Integer age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");

    }

    // controller 면서 String이면 뷰 리졸버를 찾게 되기 때문에 @RestController로 바꿔도되지만 @ResponseBody를 써도됨
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            // 변수명은 마음대로 해도됨
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={},age={}", memberName, memberAge );

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={},age={}", username, age);

        return "ok";
    }

    // 단순 타입이면 @RequestParam 생략 가능 그치만 없으면 직관적으로 알기는 어려움
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username={},age={}", username, age );

        return "ok";
    }

    // required가 true면 무조건 있어야함 (필수값임)
    // integer는 객체형이라서 null이 들어갈 수 있지만 Int에는 들어갈 수 없음
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired( @RequestParam(required = true) String username,
    @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 넘어오는 값이 없으면 디폴트로 넣는 값 지정 (빈문자 값도 디폴트값으로 넣음)
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"),
                paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
//        HelloData helloData = new HelloData();
//        helloData.setUesrname(username);
//        helloData.setAge(age);
//        log.info("username={}, age={}", helloData.getUsername(),
//                helloData.getAge());
//        return "ok";

        public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(),
                helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(),
                helloData.getAge());
        return "ok";
    }

}
