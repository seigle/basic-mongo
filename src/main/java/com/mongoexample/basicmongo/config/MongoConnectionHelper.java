package com.mongoexample.basicmongo.config;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnectionHelper {
	
	/**
	 * The 'testing_database' database and 'test_data' collection
	 * should be created in the MongoDB instance before using
	 * the temporary table and data methods.
	 * @param model
	 * @return the instance of the testing document
	 * @throws Exception 
	 * @exception An exception is thrown if the document does not exist or cannot be created. 
	 */		
	public static MongoCollection<Document> getDocuments(String connectionUrl,
													 String databaseName,
													 String collectionName) throws Exception {
		try {
			MongoClient client = MongoClients.create(connectionUrl);
			MongoDatabase testDatabase = client.getDatabase(databaseName);
			MongoCollection<Document> documents = getDocumentsByCollection(testDatabase, collectionName);
			return documents;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	protected static MongoCollection<Document> getDocumentsByCollection(MongoDatabase database, String collectionName) {
		return database.getCollection(collectionName);
	}
}
