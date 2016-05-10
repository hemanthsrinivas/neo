package com.example.cassandra.simple_client;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;
import com.datastax.driver.core.querybuilder.QueryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       /* 
        * *************************Class 1**********************
        * System.out.println( "Hello Cassandra!" );
        
        Cluster cluster;
		Session session;
		
		// Connect to the cluster and keyspace "demo"
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect("demo_1453872009_a7403c90437975acf4381ec47e2793f6");
		
		// Insert one record into the users table
		session.execute("INSERT INTO users (lastname, age, city, email, firstname) VALUES 	('Srinivas', 37, 'Dallas', 'hemanths@hotmail.com', 'Hemanth')");
								   
		// Use select to get the user we just entered
		ResultSet results = session.execute("SELECT * FROM users WHERE lastname='Jones'");
		for (Row row : results) {
		System.out.format("%s %d\n", row.getString("firstname"), row.getInt("age"));
		}
		
		// Update the same user with a new age
		session.execute("update users set age = 37 where lastname = 'Jones'");
		// Select and show the change
		results = session.execute("select * from users where lastname='Jones'");
		for (Row row : results) {
		System.out.format("%s %d\n", row.getString("firstname"), row.getInt("age"));

		}
		
		// Delete the user from the users table
	session.execute("DELETE FROM users WHERE lastname = 'Jones'");
		// Show that the user is gone
		results = session.execute("SELECT * FROM users");
		for (Row row : results) {
			System.out.format("%s %d %s %s %s\n", row.getString("lastname"), 			row.getInt("age"),  row.getString("city"), row.getString("email"), 			row.getString("firstname"));
		}					   
		
		// Clean up the connection by closing it
		cluster.close(); 
		**************************Class 1**********************
		*
		*/
    	
    	/***************************Class 2**********************/
    	
    	Cluster cluster;
		Session session;
		ResultSet results;
		Row rows;
		
		// Connect to the cluster and keyspace "demo"
		cluster = Cluster
				.builder()
				.addContactPoint("127.0.0.1")
				.withRetryPolicy(DefaultRetryPolicy.INSTANCE)		
				.build();
		session = cluster.connect("demo_1453872009_a7403c90437975acf4381ec47e2793f6");
		
		// Use select to get the user we just entered
				Statement select = QueryBuilder.select().all().from("demo_1453872009_a7403c90437975acf4381ec47e2793f6", "users").where(QueryBuilder.eq("lastname", "Jones"));
				results = session.execute(select);
				for (Row row : results) {
					System.out.format("%s %d \n", row.getString("firstname"),
							row.getInt("age"));
				}
		
				
				// Update the same user with a new age
				Statement update = QueryBuilder.update("demo_1453872009_a7403c90437975acf4381ec47e2793f6", "users")
						.with(QueryBuilder.set("age", 36))
						.where((QueryBuilder.eq("lastname", "Jones")));
		                        session.execute(update);
		// Select and show the change
				select = QueryBuilder.select().all().from("demo_1453872009_a7403c90437975acf4381ec47e2793f6", "users")
						.where(QueryBuilder.eq("lastname", "Jones"));
				results = session.execute(select);
				for (Row row : results) {
					System.out.format("%s %d \n", row.getString("firstname"),
							row.getInt("age"));
				}
					// Delete the user from the users table
			           Statement delete = QueryBuilder.delete().from("users")
							.where(QueryBuilder.eq("lastname", "Jones"));
					results = session.execute(delete);
					// Show that the user is gone
			           select = QueryBuilder.select().all().from("demo_1453872009_a7403c90437975acf4381ec47e2793f6", "users");
					results = session.execute(select);
					for (Row row : results) {
						System.out.format("%s %d %s %s %s\n", row.getString("lastname"),
								row.getInt("age"), row.getString("city"),
								row.getString("email"), row.getString("firstname"));
					}
					
					// Clean up the connection by closing it
					cluster.close();
				
		/***************************Class 2**********************/
    }
}
