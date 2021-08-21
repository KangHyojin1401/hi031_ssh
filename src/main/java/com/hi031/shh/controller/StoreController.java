package com.hi031.shh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hi031.shh.domain.Coupon;
import com.hi031.shh.domain.ResponseWrapper;
import com.hi031.shh.domain.Store;
import com.hi031.shh.service.ShhFacade;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {
	
	@Autowired
	private ShhFacade shh;
	private ResponseWrapper responseWrapper;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Store insertStore(@RequestBody Store store) throws Exception {
		return shh.insertStore(store);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public Store updateStore(@RequestBody Store store) throws Exception {
		return shh.updateStore(store);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE)
	public void removeStore(@RequestBody Store store) throws Exception {
		shh.removeStore(store);
	}
	
	@ResponseBody
	@RequestMapping(path="/{storeId}", method=RequestMethod.GET)
	public Store getStore(@PathVariable int storeId) throws Exception {
		return shh.getStore(storeId);
	}
	
	@ResponseBody
	@RequestMapping(path="/{businessUserId}", method=RequestMethod.GET)
	public ResponseWrapper getMyStores(@PathVariable String businessUserId) throws Exception {
		List<Store> results = shh.getMyStores(businessUserId);
		long total = shh.getMyStoreCount(businessUserId);
		
		responseWrapper = new ResponseWrapper(total, (List<Object>)(Object)results);
		
		return responseWrapper;
	}
	
	@ResponseBody
	@RequestMapping(path="/{type}{keyword}{start}{end}", method=RequestMethod.GET)
	public ResponseWrapper getStoresByParams(@PathVariable int type, String keyword, int start, int end) throws Exception {
		
		if(type == 1) {
			List<Store> results = shh.getStoresByName(type, keyword, start, end);
			//total 어떻게 받아야 될 지 몰라서 임시로 해둠..!
			long total = 2;
			
			responseWrapper = new ResponseWrapper(total, (List<Object>)(Object)results);
		} else if(type == 2){
			List<Store> results = shh.getStoresByMainCategory(type, keyword, start, end);
			long total = 3;
			
			responseWrapper = new ResponseWrapper(total, (List<Object>)(Object)results);
		} else if(type == 3)
		{
			List<Store> results = shh.getStoresBySubCategory(type, keyword, start, end);
			long total = 3;
			
			responseWrapper = new ResponseWrapper(total, (List<Object>)(Object)results);
		} else {
			List<Store> results = shh.getStoresByLocation(type, keyword, start, end);
			long total = 3;
			
			responseWrapper = new ResponseWrapper(total, (List<Object>)(Object)results);
		} 
			
		
		return responseWrapper;
	}

}
