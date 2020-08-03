package com.deap.TravellingApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class TravellingAppApplicationTests {

	@Test
	void contextLoads() {
	}
	public static void main(String[] args) {
		//Make sure constructor number (4) matches the one used in WebSecurityConfig
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        //Hashing example of the string "password"
		//--> only hashed passwords are saved in the database!
		String raw = "password";
		System.out.println("The password "+raw +" hashed is " +bCryptPasswordEncoder.encode(raw));
	}

}
