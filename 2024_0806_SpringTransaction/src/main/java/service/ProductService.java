package service;

import java.util.List;
import java.util.Map;

import vo.ProductVo;

public interface ProductService {

	//전체조회
	Map<String,List<ProductVo>> selectTotalMap();
	
	//입고처리
	int insert_in(ProductVo vo) throws Exception;
	
	//출고처리
	int insert_out(ProductVo vo) throws Exception;
}
