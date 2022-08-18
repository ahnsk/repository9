package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;


//==> ȸ���������� ������ ���� �߻�ȭ/ĸ��ȭ�� Service  Interface Definition  
public interface ProductService {
	
//	// ȸ������
//	public void insertProduct(Product product) throws Exception;
//	
//	// ������Ȯ�� / �α���
//	public ProductgetUser(String userId) throws Exception;
//	
//	// ȸ����������Ʈ 
//	public Map<String , Object> getUserList(Search search) throws Exception;
//	
//	// ȸ����������
//	public void updateUser(User user) throws Exception;
//	
	// INSERT
	public void insertProduct(Product product) throws Exception ;

	// SELECT ONE
	public Product findProduct(int prodNo) throws Exception ;

	// SELECT LIST
	public Map<String, Object> getProductList(Search search) throws Exception ;

	// UPDATE
	//public void updateUser(User user) throws Exception ;
	public void updateProduct(Product product) throws Exception ;

	
}