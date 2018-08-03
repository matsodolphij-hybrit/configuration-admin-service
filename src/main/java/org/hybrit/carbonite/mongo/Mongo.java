package org.hybrit.carbonite.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;
import java.util.Collections;

/**
 * MONGO client singleton.
 */
public enum Mongo {

    INSTANCE;
    final static String USER = "mule";     // the user name
    final static String DBNAME = "carbonite";
    final static String SOURCE = "minddev";   // the source where the user is defined
    final char[] PASSWORD = "Uhv8XDx4pYf7fTLS".toCharArray(); // the password as a character array
    private MongoClient mongoClient;

    Mongo() {
        if (mongoClient == null) {
            mongoClient = getClient();
        }
    }

    private MongoClient getClient() {
        System.out.println("Should appear only once!");
        System.out.println("Creating client with username" + USER + " source " + SOURCE + " and password " + Arrays.toString(PASSWORD));
        final MongoCredential credential = MongoCredential.createCredential(USER, SOURCE, PASSWORD);
        return MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder -> builder.hosts(Collections.singletonList(new ServerAddress("localhost", 27017))))
                        .credential(credential)
                        .build());
    }

    public int getCountInCollection(final String collectionName) {
        try {
            final MongoDatabase mongoDatabase = mongoClient.getDatabase(DBNAME);
            System.out.println(mongoDatabase.getName());
            return ((int) mongoDatabase.getCollection(collectionName).countDocuments());
        } catch (final MongoException e) {
            System.out.println("An error has occured with counting the documents in collection: " + collectionName + " the error" + e.getMessage());
            return 0;
        }
    }

}