package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vo.VisitVo;

import java.util.List;

@Controller
public class RestVisitController {

    // 전체조회
    @RequestMapping(value = "/visits", method = RequestMethod.GET)
    public List<VisitVo> selectList() {
        return null;
    }






}
