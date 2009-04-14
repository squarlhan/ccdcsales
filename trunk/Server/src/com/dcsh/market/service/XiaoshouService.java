package com.dcsh.market.service;


import java.util.List;

import com.dcsh.market.Products;
import com.dcsh.market.XSKcxx;


import java.util.List;

import com.dcsh.market.Canku;
import com.dcsh.market.Custom;
import com.dcsh.market.Fahuo;
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.Users;
import com.dcsh.market.XSfahuoxx;
import com.dcsh.market.XSyikuxx;


public interface XiaoshouService {

	/**
	 * ����ĳ��Ʒ�Ŀ����Ϣ
	 * @param pid ��Ʒ��id
	 * @return
	 */
	List<XSKcxx> listStorageByPrd(List<Products> productList);

	List<Fahuo> getAllFahuos();
	List<Custom> getAllCustom();
	List<Canku> getAllCankus();
	List<Products> getAllProductList();
	List<Specifications> getAllSpecificationList();
    void doYiku(XSyikuxx xsyikuxx);
    void doXsfahuo(XSfahuoxx xsfahuoxx);
    
    List<Products> getProductNameById(int id);
	List<Specifications> getSpecificationNameById(int id);
	Canku getCangkuById(Integer cangkuId);
	Custom getCustomerById(Integer customerId);
	Users loadmyshr();
    Users loadmynhr();
}