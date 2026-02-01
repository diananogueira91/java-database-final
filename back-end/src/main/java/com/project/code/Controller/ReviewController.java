package com.project.code.Controller;

import com.project.code.Model.Customer;
import com.project.code.Model.Review;
import com.project.code.Repo.CustomerRepository;
import com.project.code.Repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1. Set Up the Controller Class:
//    - Annotate the class with `@RestController` to designate it as a REST controller for handling HTTP requests.
//    - Map the class to the `/reviews` URL using `@RequestMapping("/reviews")`.
@RestController
@RequestMapping("/reviews")
public class ReviewController {

 // 2. Autowired Dependencies:
//    - Inject the following dependencies via `@Autowired`:
//        - `ReviewRepository` for accessing review data.
//        - `CustomerRepository` for retrieving customer details associated with reviews.
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

// 3. Define the `getReviews` Method:
//    - Annotate with `@GetMapping("/{storeId}/{productId}")` to fetch reviews for a specific product in a store by `storeId` and `productId`.
//    - Accept `storeId` and `productId` via `@PathVariable`.
//    - Fetch reviews using `findByStoreIdAndProductId()` method from `ReviewRepository`.
//    - Filter reviews to include only `comment`, `rating`, and the `customerName` associated with the review.
//    - Use `findById(review.getCustomerId())` from `CustomerRepository` to get customer name.
//    - Return filtered reviews in a `Map<String, Object>` with key `reviews`.
    @GetMapping("/{storeId}/{productId}")
    public Map<String, Object> getReviews(@PathVariable Long storeId, @PathVariable Long productId) {
        Map<String, Object> response = new HashMap<>();
        
        // Retrieve all reviews for the given store and product
        List<Review> rawReviews = reviewRepository.findByStoreIdAndProductId(storeId, productId);
        
        // Transform reviews to include customer name
        List<Map<String, Object>> processedReviews = new ArrayList<>();
        
        for (Review review : rawReviews) {
            Map<String, Object> reviewData = new HashMap<>();
            reviewData.put("rating", review.getRating());
            reviewData.put("comment", review.getComment());
            
            // Find customer name using the customer ID from the review
            Customer customer = customerRepository.findById(review.getCustomerId());
            if (customer != null) {
                reviewData.put("customerName", customer.getName());
            } else {
                reviewData.put("customerName", "Unknown");
            }
            
            processedReviews.add(reviewData);
        }
        
        response.put("reviews", processedReviews);
        return response;
    }
    
}