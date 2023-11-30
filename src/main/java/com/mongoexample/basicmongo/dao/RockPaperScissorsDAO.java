package com.mongoexample.basicmongo.dao;

import java.time.Clock;
import java.time.Instant;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.mongoexample.basicmongo.MongoDbConfig;

public class RockPaperScissorsDAO {
	
	protected static String connectionUrl = MongoDbConfig.connectionUrl;
	protected static String mongoDatabase = "testing_database";
	protected static String mongoCollection = "rock_paper_scissors";
	
//	public static void insertOne(Document rpsDocument) {
	public static void insertOne(String personChoice, String computerChoice, String winner, Instant now) {
	    try {
	    	MongoCollection<Document> collection = getCollectionDocuments();
	    
	        try {
//	        	InsertOneResult result = collection.insertOne(rpsDocument);
	        	
	        	// Inserts a sample document describing a movie into the collection
	            InsertOneResult result = collection.insertOne(new Document()
	                    .append("_id", new ObjectId())
	                    .append("timestamp", now)
	                    .append("personChoice", personChoice)
	                    .append("computerChoice", computerChoice)
	                    .append("winner", winner));
//	                    .append("genres", Arrays.asList("Documentary", "Comedy")));
	            	            
	            // Prints the ID of the inserted document
	            System.out.println("Success! Inserted document id: " + result.getInsertedId());
	        
	        // Prints a message if any exceptions occur during the operation
	        } catch (MongoException me) {
	            System.err.println("Unable to insert due to an error: " + me);
	        }
	    } finally {
	    	
	    }
	
	}

	public static MongoCollection<Document> getCollectionDocuments() {
		MongoClient mongoClient = MongoClients.create(connectionUrl);
	    MongoDatabase database = mongoClient.getDatabase(mongoDatabase);
	    return database.getCollection(mongoCollection);
	}
}
