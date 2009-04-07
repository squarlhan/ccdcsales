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
	 * 返回某产品的库存信息
	 * @param pid 产品的id
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
    Users loadmyshr();
    Users loadmynhr();

}
