package dao;

import java.util.List;

import vo.ProductVo;

public interface ProductDao {

	List<ProductVo>		selectList();
	ProductVo			selectOne(int idx);
	default ProductVo	selectOne(String name) { return null; }
	int                	insert(ProductVo vo);
	int                	update(ProductVo vo);
	int					delete(int idx);
	
}
