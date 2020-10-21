package com.message.app.model;

/**
 * Model class for data exchange.
 * 
 * @author Satheesh
 *
 */
public class Message {

	private final String msg;

	private final String question;

	/**
	 * 
	 * @param msg
	 * @param question
	 */
	public Message(String msg, String question) {
		this.msg = msg;
		this.question = question;
	}

	/**
	 * 
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 
	 * @return question
	 */
	public String getQuestion() {
		return question;
	}

}
