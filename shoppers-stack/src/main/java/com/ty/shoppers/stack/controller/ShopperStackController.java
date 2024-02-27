package com.ty.shoppers.stack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.shoppers.stack.dto.ResponseStructure;
import com.ty.shoppers.stack.entity.Orders;
import com.ty.shoppers.stack.entity.Product;
import com.ty.shoppers.stack.entity.Review;
import com.ty.shoppers.stack.entity.Shopper;
import com.ty.shoppers.stack.repository.ShopperRepository;
import com.ty.shoppers.stack.service.OrderService;
import com.ty.shoppers.stack.service.ProductService;
import com.ty.shoppers.stack.service.ReviewService;
import com.ty.shoppers.stack.service.ShopperService;
import com.ty.shoppers.stack.util.OrderHelper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shopperstack")
@Tag(name = "Shopper Stack",description = "shopper Stack Application Endpoints")
public class ShopperStackController {
	@Autowired
	private ShopperService shopperService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ShopperRepository shopperRepository;
	
	@ApiResponses(value = {@ApiResponse(description = "creates Shopper",responseCode = "200")})
	@Operation(description = "shopper will be created",summary = "new shopper will create")
	
	@PostMapping(value="/shopper",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Shopper>> createShopper(@RequestBody Shopper shopper){
		return shopperService.createShopper(shopper);
	}
	
	@ApiResponses(value = {@ApiResponse(description = "find shopper",responseCode = "200"),@ApiResponse(description = "shopper not found",responseCode = "400")})
	@Operation(description = "shopper will be displayed",summary = "shopper will displayed")
	
	@GetMapping(value="/shopper/{shopperId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Shopper>> findShopperById(@PathVariable int shopperId){
		return shopperService.findShopperById(shopperId);
	}
	
	@GetMapping(value = "/shopper")
	public ResponseEntity<ResponseStructure<Shopper>> shopperLogin(@RequestParam String email,@RequestParam String password){
		return shopperService.shopperLogin(email, password);
	}
	@ApiResponses(value = {@ApiResponse(description = "creates Product",responseCode = "200")})
	@Operation(description = "product will be created",summary = "new product will created and added to shopper")
	
	@PostMapping(value="/product/{shopperId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Product>> createProduct(@RequestBody Product product,@PathVariable int shopperId){
		return productService.createProduce(product, shopperId);
	}
	
	@ApiResponses(value = {@ApiResponse(description = "to find all products",responseCode = "200"),@ApiResponse(description = "shopper not found to display products",responseCode = "400")})
	@Operation(description = "All the products will be displayed",summary = "all the productes will be displayed")
	
	@GetMapping(value="/products",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<Product>>> findallProducts(){
		return productService.findAllProducts();
	}
	
	@ApiResponses(value = {@ApiResponse(description = "creates Product",responseCode = "200"),@ApiResponse(description = "shoppers not found",responseCode = "400")})
	@Operation(description = "product will be created",summary = "new product will created and added to shopper")
	
	@GetMapping(value="/products/{shopperId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<Product>>> findallProductsByShopperId(@PathVariable int shopperId){
		return productService.findAllProducteByShopperId(shopperId);
	}
	
	@ApiResponses(value = {@ApiResponse(description = "Updates Product",responseCode = "200"),@ApiResponse(description = "shopper or product not found",responseCode = "400")})
	@Operation(description = "pass all the fields while passing object",summary = "Product details will be updated")
	
	@PutMapping(value="/product/{shopperId}/{productId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable int productId,@RequestBody Product product,@PathVariable int shopperId){
		return productService.updateProduct(product, productId,shopperId);
	}
	
	@ApiResponses(value = {@ApiResponse(description = "Producet removed",responseCode = "200")})
	@Operation(description = "product will be removed from shopper",summary = "Product will be removed")
	
	
	@DeleteMapping(value="/product/{shopperId}/{productId}")
	public ResponseEntity<ResponseStructure<String>> removeProduct(@PathVariable int productId,@PathVariable int shopperId){
		return productService.removeProduct(productId, shopperId);
	}
	
	@ApiResponses(value = {@ApiResponse(description = "creates Review",responseCode = "200")})
	@Operation(description = "Review will be created",summary = "new review will create")
	
	@PostMapping(value="/review/{shopperId}/{productId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Review>> createReview(@RequestBody Review review,@PathVariable int shopperId,@PathVariable int productId){
		return reviewService.createReview(review,shopperId,productId);
	}
	
	@ApiResponses(value = {@ApiResponse(description = "Find all reviewsr",responseCode = "200"),@ApiResponse(description = "shopper not found or Invalid shopper",responseCode = "400")})
	@Operation(description = "reviews will be displayed to product",summary = "reviews will be displayed")
	
	@GetMapping(value="/reviews/{productId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<Review>>> findallreviewsByproductId(@PathVariable int productId){
		return reviewService.findAllReviewsByProductId(productId);
	}
	
	@ApiResponses(value = {@ApiResponse(description = "review will be deleted",responseCode = "200"),@ApiResponse(description = "shopper or Review not found or invalid Shopper",responseCode = "400")})
	@Operation(description = "review will be removed to product",summary = "review will be deleted")
	
	@DeleteMapping(value="/review/{shopperId}/{reviewId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<String>> removeReview(@PathVariable int reviewId,@PathVariable int shopperId){
		return reviewService.removeReview(reviewId, shopperId);
	}
	
	@ApiResponses(value = {@ApiResponse(description = "Create order",responseCode = "200")})
	@Operation(description = "Order will be created with products",summary = "new Order will create")
	
	@PostMapping(value="/order/{shopperId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Orders>> createOrder(@RequestBody OrderHelper orderHelper,@PathVariable int shopperId ){
		return orderService.createOrder(orderHelper, shopperId);
	}
	
	@ApiResponses(value = {@ApiResponse(description = "find order by order id",responseCode = "200"),@ApiResponse(description = "shopper or Order not found",responseCode = "400")})
	@Operation(description = "order will be displayed with products",summary = "order will be displayed")
	
	@GetMapping(value="/order/{shopperId}/{orderId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Orders>> findOrderByOrderId(@PathVariable int shopperId,@PathVariable int orderId){
		return orderService.findOrderById(shopperId, orderId);
	}
	
	
 
}
