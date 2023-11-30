package com.mongoexample.basicmongo.controllers;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Controller
@RequestMapping("/mongo")
public class MongoController {
	
	private String connectionUrl = "mongodb://localhost:27017";

	@GetMapping("list_collections")
	public String listDatabaseTables(Model model) {
		
		// https://www.mongodb.com/developer/languages/java/java-setup-crud-operations/		
		
		List<Document> databases = new ArrayList<>();
		try {
			MongoClient client = MongoClients.create(connectionUrl);
			databases = client.listDatabases().into(new ArrayList<>());
			System.out.println("databases is: " + databases.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

//		{"name": "admin", "sizeOnDisk": 40960, "empty": false}
//		{"name": "chatlog", "sizeOnDisk": 57344, "empty": false}
//		{"name": "config", "sizeOnDisk": 110592, "empty": false}
//		{"name": "local", "sizeOnDisk": 90112, "empty": false}
//		{"name": "sample_training", "sizeOnDisk": 73728, "empty": false}

		model.addAttribute("collections", databases);
		return "list_collections";
	}
	
	/**
	 * The 'testing_database' database and 'test_data' collection
	 * should be created in the MongoDB instance before using
	 * the temporary table and data methods.
	 * @param model
	 * @return the instance of the testing document
	 * @throws Exception 
	 * @exception An exception is thrown if the document does not exist or cannot be created. 
	 */		
	protected MongoCollection<Document> getTestingDocumentCollection() throws Exception {
		try {
			MongoClient client = MongoClients.create(connectionUrl);
			MongoDatabase testDatabase = client.getDatabase("testing_database");
			MongoCollection<Document> testCollection = getTestCollectionDocument(testDatabase, "test_data");
			return testCollection;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	protected MongoCollection<Document> getTestCollectionDocument(MongoDatabase database, String collectionName) {
		return database.getCollection(collectionName);
	}
	
}