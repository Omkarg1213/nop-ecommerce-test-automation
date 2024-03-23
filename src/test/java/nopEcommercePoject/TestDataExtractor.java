package nopEcommercePoject;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class TestDataExtractor {
    
	
    public static void main(String[] args) {
        Object[][] registrationData = getRegistrationData();
        
        // Extract email and password from registration data
        List<String[]> extractedData = extractEmailAndPassword(registrationData);
        
        // Print extracted email and password data
        for (String[] data : extractedData) {
            System.out.println("Email: " + data[0] + ", Password: " + data[1]);
        }
    }

    @DataProvider(name = "registrationData")
    public static Object[][] getRegistrationData() {
        // Defining test data for registration
        return new Object[][] {
            // Test data sets with various combinations
            {"Male", "Omkar G", "Patil", "15", "January", "1990", "omkarg@example.com", "ACME Corp", true, 
                "password123"},
				/*
				 * {"Female", "Shubha", "Swain", "20", "March", "1985", "shuba@example.com",
				 * "XYZ Inc", false, "password456"}, {"Male", "Razi", "mommin", "10", "April",
				 * "1987", "razi@example.com", "123 Company", false, "test1234"}, {"Female",
				 * "Prashanthi", "Moolya", "25", "May", "1983", "prashanthi@example.com",
				 * "ABC Ltd", true, "test5678"}, {"Male", "Saran", "Moolya", "5", "June",
				 * "1982", "saran@example.com", "XYZ Inc", true, "pass1234"}, {"Female",
				 * "Sarah", "Wilson", "30", "July", "1980", "sarah.wilson@example.com",
				 * "XYZ Inc", false, "testpass"}, {"Male", "Praveen", "Pathra", "10", "August",
				 * "1981", "praveen@example.com", "456 Corp", true, "password123"}, {"Female",
				 * "Lakshmi", "Koppe", "15", "September", "1979", "lakshmi@example.com",
				 * "789 Company", true, "test123"}
				 */
            
        };
    }
    
    @DataProvider(name = "loginData")
    public static List<String[]> extractEmailAndPassword(Object[][] data) {
        List<String[]> extractedData = new ArrayList<>();
        for (Object[] row : data) {
            String email = (String) row[6]; //  email is at index 6
            String password = (String) row[9]; //  password is at index 9
            String[] emailPasswordPair = {email, password};
            extractedData.add(emailPasswordPair);
        }
        return extractedData;
    }
    
    @DataProvider(name = "billingAddressData")
    public static Object[][] getbillingAddressData() {
        // Defining test data for registration
        return new Object[][] {
            // Test data sets with various combinations
            {"Omkar G", "Patil","omkarg@example.com", "Masai School", "India", "Other",
        		"Bangalore", "Shivapura", "Peenya", "560058", "9731656168", "9035634715"}
            
        };
    }
}
