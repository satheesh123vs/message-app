package com.message.app.service;

import org.springframework.stereotype.Component;

import com.message.app.model.Message;

/**
 * @author satheesh
 *
 */

@Component
public interface MessageService {

	/**
	 * method to return message details
	 * @param msg
	 * @return string
	 */
	public String getExpectedQuestion(String msg);

	/**
	 * This method create question
	 * returns Message 
	 * @return Message
	 */
	public Message createMessageWithQuestion();

}
