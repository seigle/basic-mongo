package com.mongoexample.basicmongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@SpringBootApplication
public class BasicMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicMongoApplication.class, args);
		
		// https://www.mongodb.com/developer/languages/java/java-setup-crud-operations/		
		String connectionUri = "mongodb://localhost:27017";
		
		try {
			MongoClient client = MongoClients.create(connectionUri);
			List<Document> databases = client.listDatabases().into(new ArrayList<>());
			databases.forEach(db -> System.out.println(db.toJson()));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
	}

}