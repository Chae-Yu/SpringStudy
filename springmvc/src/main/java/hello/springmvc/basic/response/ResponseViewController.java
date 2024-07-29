package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 뷰 템플릿을 호출하는 방법
@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    // string으로 반환할거라서 model이 필요함
    // @controller면서 string으로 반환하면 반환값이 뷰의 논리적인 이름이 됨
    // 만약에 @Responsebody 추가하면 뷰 이름으로 안 가고 바로 그 자체가 반환됨
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!!");

        return "response/hello";
    }

    // 뷰 이름을 없앤 경우인데 권장하는 방식은 아님
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!!");
    }


}
