package com.deap.TravellingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.deap.TravellingApp.repository.EmailRepository;

@Service
public class MyEmailService implements EmailRepository{

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Async
	public void sendEmail(SimpleMailMessage email) {
		javaMailSender.send(email);
	}

}
