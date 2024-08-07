package controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ProductService;
import vo.ProductVo;

@Controller
public class ProductController {

	@Autowired
	ProductService product_service;
	
	@RequestMapping("/product/list.do")
	public String list(Model model) {
		
		Map map = product_service.selectTotalMap();
		
		model.addAttribute("map", map);
		
		return "product/product_list";
	}
	
	
	//입고처리
	// /product/insert_in.do?name=TV&cnt=100
	@RequestMapping("/product/insert_in.do")
	public String insert_in(ProductVo vo) {
		
		try {
			
			product_service.insert_in(vo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return "redirect:list.do";
	}

	// 입고 취소
	// /product/delete_in.do?idx=
	@RequestMapping("/product/delete_in.do")
	public String delete_in(int idx, RedirectAttributes ra) {

        try {
            product_service.delete_in(idx);
        } catch (Exception e) {
			String message = e.getMessage();
			ra.addAttribute("error", message);
        }


        return "redirect:list.do";
	}
	
	
	//출고처리
	// /product/insert_out.do?name=TV&cnt=100
	@RequestMapping("/product/insert_out.do")
	public String insert_out(ProductVo vo, RedirectAttributes ra) {
		
		try {
			
			product_service.insert_out(vo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			String message = e.getMessage();
			ra.addAttribute("error", message);
		}
		
		return "redirect:list.do";
	}

	// 출고 취소
	// /product/delete_out.do?idx=
	@RequestMapping("/product/delete_out.do")
	public String delete_out(int idx, RedirectAttributes ra) {

		try {
			product_service.delete_out(idx);
		} catch (Exception e) {
			String message = e.getMessage();
			ra.addAttribute("error", message);
		}


		return "redirect:list.do";
	}
	
	
	
}
