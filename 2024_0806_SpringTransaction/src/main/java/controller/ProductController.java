package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.ProductService;
import vo.ProductVo;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

	@Autowired
	ProductService product_service;

	/**
	 * 입고,재고,출고 리스트 조회.
	 * @param model
	 * @return model -> map
	 */
	@RequestMapping("/product/list.do")
	public String list(Model model) {

		Map<String, List<ProductVo>> map = product_service.selectTotalMap();

		model.addAttribute("map",map);

		return "product/product_list";
	}

	/**
	 * 상품 입고하기.
	 * @param vo : name, cnt
	 * @return : list.do
	 */
	@RequestMapping("/product/insert_in.do")
	public String insert_in(ProductVo vo) {

        try {
            product_service.insert_in(vo);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:list.do";
	}


}
