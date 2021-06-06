package com.mobile.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;
import com.mobile.email.Email;
import com.mobile.model.User;
import com.mobile.repository.UserRepository;

@Controller
@RequestMapping("/user")
@CrossOrigin(value="http://localhost:4200")
public class UserController {

	@Autowired
	UserRepository repo;
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(repo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/name/{username}")
	public ResponseEntity<User> getUserByName(@PathVariable("username") String username){
		return new ResponseEntity<User>(repo.findByUsername(username), HttpStatus.OK);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
		return new ResponseEntity<User>(repo.findByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping("/mobile/{mobile}")
	public ResponseEntity<User> getUserByMobile(@PathVariable("mobile") String mobile){
		return new ResponseEntity<User>(repo.findByMobile(mobile), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Void> storeUser(@RequestBody User user){
		repo.save(user);
		Email email = new Email(user.getEmail(), "Registration", "You have successfully registered on Quicktodo platform !!");
		email.sendEmail();
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Void> updateUser(@RequestBody User user){
		repo.save(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/upgradeToPremium")
	public void upgradeToPremium(HttpServletResponse resp)
	{
		try 
	       {
	           ApiContext context = ApiContext.create("test_UuV4JBZppv0R96oFTToxSptjuZUAsQxBLrJ", "test_Z72TnUzTUBAJPEV3AApt2jk17k2JgD892ULbi3SMbgR5aEy7fRTCqy37JYJgipg8iBZS3JRDrqQsMMQWEa7MPeGxruT8vqLZng51zpsYyoShcOeuzKCDISBJ7Ze", ApiContext.Mode.TEST);
	           Instamojo api = new InstamojoImpl(context);

	           PaymentOrder order = new PaymentOrder();
	           order.setName("Jayesh Karli");
	           order.setEmail("jayeshkarli@gmail.com");
	           order.setPhone("5123564789");
	           order.setCurrency("INR");
	           order.setAmount((double)11);
	           order.setDescription("This is a test transaction.");
	           order.setRedirectUrl("https://www.microsoft.com");
	           order.setWebhookUrl("https://www.google.com");
	           order.setTransactionId(UUID.randomUUID().toString());

	           PaymentOrderResponse paymentOrderResponse = api.createPaymentOrder(order);
	           resp.sendRedirect(paymentOrderResponse.getPaymentOptions().getPaymentUrl());
	       }
	       catch (Exception e) 
	       {
	           System.out.println(e);
	       }
		
	}
}
