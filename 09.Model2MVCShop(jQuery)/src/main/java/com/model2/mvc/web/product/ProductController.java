package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	//Field
@Autowired
@Qualifier("productServiceImpl")
private ProductService productService;

	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	
	@RequestMapping(value="addProduct", method=RequestMethod.GET)
	public String addProductView() throws Exception{
		
		System.out.println("'/product/addProductView : GET");
	
		return "redirect:/product/addProductView.jsp";
	}
	
	@RequestMapping(value="addProduct", method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, Model model) throws Exception{
		System.out.println("/product/addProduct : POST");

		productService.insertProduct(product);
			
		System.out.println(product);
		model.addAttribute("product",	product);
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping(value="getProduct", method=RequestMethod.GET)
	public String getProduct(@RequestParam("prodNo") int prodNo, Model model)throws Exception{
		
		System.out.println("/getProduct");
		
		Product product=productService.findProduct(prodNo);
		System.out.println(prodNo);
		model.addAttribute("product", product);
		
		return "forward:/product/getProduct.jsp";
	}
	@RequestMapping(value="updateProduct", method=RequestMethod.GET)
	public String updateProductView(@RequestParam("prodNo")int prodNo,Model model, HttpSession session) throws Exception{
		System.out.println("/updateProduct : GET");
		Product product=productService.findProduct(prodNo);
		//productService.updateProduct(product);		
		//String sessionId=((Product)session.getAttribute("product"))
		model.addAttribute("product",product);
		
		return "forward:/product/updateProductView.jsp";
		
	}
	@RequestMapping(value="updateProduct", method=RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product")Product product,Model model, HttpSession session)throws Exception{
	System.out.println("updateProduct.do");
	productService.updateProduct(product);
	System.out.println("???"+product);
	model.addAttribute("prodNo", product.getProdNo());
	return "forward:/product/getProduct.jsp";
	}
	
	//@RequestMapping("/listProduct.do")
	@RequestMapping(value="listProduct")
	public String listProduct(@ModelAttribute("search") Search search,Model model,HttpServletRequest request) throws Exception{
		System.out.println("/product/listProduct : GET/ POST");
		
		if(search.getCurrentPage()==0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map=productService.getProductList(search);
		
		Page resultPage=new Page(search.getCurrentPage(),((Integer)map.get("totalCount")).intValue(),pageUnit,pageSize);
		System.out.println(resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
	}
	
}

