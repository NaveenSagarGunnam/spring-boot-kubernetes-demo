package com.example.springcloudant;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.IndexField;
import com.cloudant.client.api.model.IndexField.SortOrder;
import com.cloudant.client.api.model.Response;


@RestController
@RequestMapping("/add")
public class CustomerRestController {
	
	@Autowired
	private CloudantClient client;
	
	private Database db;
    // Create a new customer
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveCustomerInfo(@RequestBody Customer cust) {
    	db = client.database("customer", false);
    	cust.set_id(UUID.randomUUID().toString());
    	cust.setCustomerId("CUS"+ System.currentTimeMillis());
        //System.out.println("Save Customer " + cust);
        Response r = null;
        if (cust != null) {
            r = db.post(cust);
        }
        return "Customer Info persisted successfully. Generated CUST ID "+ cust.getCustomerId() ;
    }

    // Query reviews for all documents or by customerId
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<Customer> getAll(@RequestParam(required=false) String customerId) {
    	db = client.database("customer", false);
    	// Get all documents from customerdb
        List<Customer> allDocs = null;
        try {
            if (customerId == null) {
                allDocs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
                            .getDocsAs(Customer.class);
            } else {
                // create Index
                // Here is create a design doc named designdoc
                // A view named querybycustomerIdView
                // and an index named customerId
                db.createIndex("querybycustomerIdView","designdoc","json",
                    new IndexField[]{new IndexField("customerId",SortOrder.asc)});
                System.out.println("Successfully created index");
                allDocs = db.findByIndex("{\"customerId\" : " + customerId + "}", Customer.class);
            }
        } catch (Exception e) {
            System.out.println("Exception thrown : " + e.getMessage());
        }
        return allDocs;
    }
}
