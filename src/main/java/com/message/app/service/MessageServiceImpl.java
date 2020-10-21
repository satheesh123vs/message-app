package com.message.app.service;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.message.app.model.Message;

/**
 * This is the Service class for Message application
 * 
 * @author satheesh
 *
 */

@Service
public class MessageServiceImpl implements MessageService{

	Base64.Decoder decoder = Base64.getDecoder();

	Base64.Encoder encoder = Base64.getEncoder();

	/**
	 * {@inheritDoc}
	 */
	public String getExpectedQuestion(String msg) {

		return new String(decoder.decode(msg));
	}

	/**
	 * {@inheritDoc}
	 */
	public Message createMessageWithQuestion() {
		int min = 0;
		int max = 10;
		int a = (int) (Math.random() * (max - min + 1) + min);
		int b = (int) (Math.random() * (max - min + 1) + min);
		int c = (int) (Math.random() * (max - min + 1) + min);
		String question = "Please sum the numbers " + a + ", " + b + ", " + c;
		String encodedMsg = new String(encoder.encode(question.getBytes()));
		return new Message(encodedMsg, question);
	}

}
