package com.message.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.message.app.MessageAppApplicationTests;

/**
 * 
 * Test class for controller
 *
 */
public class TestMessageController extends MessageAppApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/* Testing post valid scenario -POST */
	@Test
	public void testvalidResponsePost() throws Exception {

		mockMvc.perform(post("/")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8")).andReturn().getResponse()
				.getClass();
	}

	/* Testing valid scenario -Get */
	@Test
	public void testvalidResponse() throws Exception {

		Base64.Encoder encoder = Base64.getEncoder();
		String question = "Please sum the numbers 4, 8, 9";
		String encodedMsg = new String(encoder.encode(question.getBytes()));
		String answer = "21";

		mockMvc.perform(get("/").param("msg", encodedMsg).param("question", question).param("answer", answer))
				.andExpect(status().isOk());
	}

	/* Testing In-valid scenario -Get, by passing different question */
	@Test
	public void testInvalidQuestionResponse() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String question = "Please sum the numbers 4, 8, 9";
		String encodedMsg = new String(encoder.encode(question.getBytes()));
		String questionUpdated = "Please sum the numbers 4, 8, 10";
		String answer = "21";

		mockMvc.perform(get("/").param("msg", encodedMsg).param("question", questionUpdated).param("answer", answer))
				.andExpect(status().isBadRequest());
	}

	/* Testing In-valid scenario -Get, by passing wrong answer */
	@Test
	public void testInvalidSumResponse() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String question = "Please sum the numbers 4, 8, 9";
		String encodedMsg = new String(encoder.encode(question.getBytes()));
		String questionUpdated = "Please sum the numbers 4, 8, 9";
		String answer = "11";

		mockMvc.perform(get("/").param("msg", encodedMsg).param("question", questionUpdated).param("answer", answer))
				.andExpect(status().isBadRequest());
	}
}

