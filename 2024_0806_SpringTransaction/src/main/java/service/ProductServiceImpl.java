package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ProductDao;
import vo.ProductVo;

public class ProductServiceImpl implements ProductService {

	ProductDao  product_in_dao;		 	// 입고
	ProductDao  product_out_dao; 		// 출고
	ProductDao  product_remain_dao; 	// 재고
	
	// Constructor Injection	
	public ProductServiceImpl(ProductDao product_in_dao, ProductDao product_out_dao, ProductDao product_remain_dao) {
		super();
		this.product_in_dao = product_in_dao;
		this.product_out_dao = product_out_dao;
		this.product_remain_dao = product_remain_dao;
	}

	@Override
	public Map<String, List<ProductVo>> selectTotalMap() {
		// TODO Auto-generated method stub
		
		List<ProductVo> in_list 	= product_in_dao.selectList(); 		//입고목록
		List<ProductVo> out_list 	= product_out_dao.selectList(); 	//출고목록
		List<ProductVo> remain_list = product_remain_dao.selectList(); 	//재고목록
		
		Map<String, List<ProductVo>> map = new HashMap<String, List<ProductVo>>();
		map.put("in_list",  in_list);
		map.put("out_list", out_list);
		map.put("remain_list", remain_list);
		
		return map;
	}

	@Override
	public int insert_in(ProductVo vo) throws Exception {

		int res = 0;

		// 1. 입고 등록하기.
		res = product_in_dao.insert(vo);

		// 2. 재고 등록하기.
		ProductVo remainVo = product_remain_dao.selectOne(vo.getName());

		if(remainVo == null){
			// 재고가 없으므로 상품 추가.
			res = product_remain_dao.insert(vo);
		}else {
			// 상품가등록상태 : 수량 수정
			// 재고 수량 = 기존 재고 수량 + 추가 수량
			int cnt = remainVo.getCnt() + vo.getCnt();
			remainVo.setCnt(cnt);

			res = product_remain_dao.update(remainVo);
		}

		return res;
	}

	@Override
	public int insert_out(ProductVo vo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
