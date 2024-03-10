package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.Order;
import com.ecommerceproject.Exception.OrderException;
import com.ecommerceproject.Repository.OrderRepository;
import com.ecommerceproject.Response.ApiResponse;
import com.ecommerceproject.Response.PaymentLinkResponse;
import com.ecommerceproject.Service.OrderService;
import com.ecommerceproject.Service.UserService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {
    @Value("{razorpay.api.key}")
    String apiKey;

    @Value("{razorpay.api.secret}")
    String apiSecret;

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/payment/{orderId}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestHeader("Authorization") String jwt,
                                                                 @PathVariable Long orderId) throws OrderException, RazorpayException {
        Order order=orderService.findOrderById(orderId);
        try{
            RazorpayClient razorpayClient=new RazorpayClient(apiKey,apiSecret);
            JSONObject paymentLinkRequest=new JSONObject();
            paymentLinkRequest.put("amount",order.getDiscountPrice()*100);
            paymentLinkRequest.put("currency","INR");


            JSONObject customer=new JSONObject();
            customer.put("name",order.getUser().getFirstName());
            customer.put("email",order.getUser().getEmail());
            paymentLinkRequest.put("customer",customer);

            JSONObject notify=new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);

            paymentLinkRequest.put("callback_url","http://localhost:3000/payment/"+orderId);
            paymentLinkRequest.put("callback_method","get");

            PaymentLink paymentLink= razorpayClient.paymentLink.create(paymentLinkRequest);
            String paymentLinkId=paymentLink.get("id");
            String paymentLinkUrl=paymentLink.get("shot_url");

            PaymentLinkResponse response=new PaymentLinkResponse();
            response.setPayment_link_id(paymentLinkId);
            response.setPayment_link_url(paymentLinkUrl);

            return new ResponseEntity<PaymentLinkResponse>(response,HttpStatus.CREATED);
        }
        catch (Exception e){
            throw new RazorpayException(e.getMessage());
        }
    }

    @GetMapping("/payments")
    public ResponseEntity<ApiResponse> redirect(@RequestParam(name="payment_id")String paymentId,
                                                @RequestParam(name="orderId")Long orderId) throws OrderException, RazorpayException {
        Order order=orderService.findOrderById(orderId);
        RazorpayClient client=new RazorpayClient(apiKey,apiSecret);
        try{
            Payment payment=client.payments.fetch(paymentId);
            if (payment.get("status").equals("captured")){
                order.getPaymentDetails().setPaymentId(paymentId);
                order.getPaymentDetails().setPaymentStatus("COMPLETED");
                order.setOrderStatus("PLACED");
                orderRepository.save(order);
            }
            ApiResponse apiResponse=new ApiResponse();
            apiResponse.setMessage("order placed successfully");
            apiResponse.setStatus(true);
            return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.ACCEPTED);
        }catch (Exception e){
            throw new RazorpayException(e.getMessage());
        }
    }
}
