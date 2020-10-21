package com.message.app.controller;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.message.app.constants.MessageConstants;
import com.message.app.model.Message;
import com.message.app.service.MessageService;

/**
 * 
 * Controller
 *
 */
@RestController
public class MessageController {

	@Autowired
	private MessageService mssageService;

	final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	ResourceBundle boundle = ResourceBundle.getBundle("MessageProperties");

	/**
	 * This method is the Rest WS end point to generate Message includes
	 * question. returns message as response.
	 * 
	 * @return Message
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Message getQuestion() {
		Message msg = null;
		try {
			msg = mssageService.createMessageWithQuestion();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return msg;
	}

	/**
	 * This method is the Rest WS end point to validate the response submitted
	 * by the user. returns the status as response.
	 * 
	 * @param msg
	 * @param question
	 * @param answer
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> verifyAnswer(@RequestParam("msg") String msg,
			@RequestParam("question") String question, @RequestParam("answer") String answer) {

		boolean validResponse = false;
		String expectedQuestion = null;
		
		if (msg == null) {
			String nullInputMessage = boundle.getString(MessageConstants.MESSAGE_VALIDATION_NULL_INPUT);
			logger.error(nullInputMessage);
		}
		else {

			try {
				expectedQuestion = mssageService.getExpectedQuestion(msg);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

			/*
			 * Comparing expected questions/answers with Submitted
			 * question/answer.
			 */
			validResponse = compareQuestionAnswer(expectedQuestion, question, answer);
			if (validResponse) {
				logger.info("The answer submitted is correct..!");
				return new ResponseEntity<>(JSONObject.quote("That is correct..!"), HttpStatus.OK);
			}

		}
		return new ResponseEntity<>(JSONObject.quote("That is wrong. Please try again."), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method Getting numbers from original question, calculating the sum
	 * and comparing with the user entered sum.
	 * 
	 * @param expectedQuestion
	 * @param question
	 * @param answer
	 * @return boolean
	 */
	private boolean compareQuestionAnswer(String expectedQuestion, String question, String answer) {

		boolean validQuestAnswr = false;
		if (null != expectedQuestion) {

			String[] originalQuestion = expectedQuestion.split(",");

			if (originalQuestion.length == 3) {
				String numString1 = originalQuestion[0].substring(23, originalQuestion[0].length());
				int num1 = Integer.parseInt(numString1.trim());
				int num2 = Integer.parseInt(originalQuestion[1].trim());
				int num3 = Integer.parseInt(originalQuestion[2].trim());
				String expectedAnswer = Integer.toString(num1 + num2 + num3);
				if (expectedQuestion.equals(question) && expectedAnswer.equals(answer)) {
					validQuestAnswr = true;
				}
			}
		}
		return validQuestAnswr;
	}

}