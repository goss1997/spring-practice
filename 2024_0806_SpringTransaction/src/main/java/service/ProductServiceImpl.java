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
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.입고등록하기
		res = product_in_dao.insert(vo);
		
		//2.재고등록(수정)처리
		ProductVo remainVo = product_remain_dao.selectOne(vo.getName());
		
		if(remainVo==null) {
			//등록추가(등록상품이 없다)
			res = product_remain_dao.insert(vo);
		}else {
			//상품기등록상태: 수량수정
			// 재고수량 = 기존재고수량 + 추가수량
			int cnt = remainVo.getCnt() + vo.getCnt();
			remainVo.setCnt(cnt);
			
			res = product_remain_dao.update(remainVo);
		}
		
		return res;
	}

	@Override
	public int delete_in(int idx) throws Exception {

		int res = 0;

		// 해당 입고 데이터 가져오기.
		ProductVo productIn = product_in_dao.selectOne(idx);

		// 입고 취소 전 재고 수량 업데이트.
		// 재고 데이터 조회.
		ProductVo remain = product_remain_dao.selectOne(productIn.getName());

		// 유효성 체크.
		if(remain == null){
			throw new Exception("remain_not");
		}else{
			// 300 - 100
			int cnt = remain.getCnt() - productIn.getCnt();

			// 유효성 체크.
			if(cnt < 0) {
				throw new Exception("diff");
			}else{


				// 취소할 입고 수량을 재고 수량에서 빼기.
				remain.setCnt(cnt);

				// 0일 경우 재고 목록에서 제거 아니면 업데이트
				if (cnt==0) {
					// 재고 목록에서 제거
					product_remain_dao.delete(remain.getIdx());
				}else{
					// 재고 수량 업데이트
					product_remain_dao.update(remain);
				}

				//  입고 등록 취소.
				res = product_in_dao.delete(idx);
			}
		}

		return res;
	}

	@Override
	public int insert_out(ProductVo vo) throws Exception {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.출고등록
		res = product_out_dao.insert(vo);
		
		//2.재고정보 얻어오기
		ProductVo remainVo = product_remain_dao.selectOne(vo.getName());
		
		if(remainVo==null) {
			// 재고목록에 상품이 없을경우
			throw new Exception("remain_not");
			
		}else {
			//재고수량 = 원래재고수량      - 출고수량
			int cnt    = remainVo.getCnt() - vo.getCnt();

			if(cnt < 0) {
				//재고수량이 부족한 경우
				throw new Exception("remain_lack");
			}

			//재고수량 수정
			remainVo.setCnt(cnt);
			res = product_remain_dao.update(remainVo);

		}

		return res;
	}

	@Override
	public int delete_out(int idx) throws Exception {

		int res = 0;

		// 출고 데이터 가져오기.
		ProductVo productOut = product_out_dao.selectOne(idx);

		// 출고 취소 전 재고 수량 업데이트
		// 재고 데이터 조회.
		ProductVo remain = product_remain_dao.selectOne(productOut.getName());

		// 유효성 체크.
		if(remain == null){
			throw new Exception("remain_not");
		}else {

			// 취소할 출고 수량을 재고 수량에 더하기.
			remain.setCnt(remain.getCnt() + productOut.getCnt());

			// 재고 수량 업데이트
			product_remain_dao.update(remain);

			//  출고 취소.
			res = product_out_dao.delete(idx);

		}

		return res;
	}

}
