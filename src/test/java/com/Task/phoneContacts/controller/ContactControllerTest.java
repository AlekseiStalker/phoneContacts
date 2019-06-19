package com.Task.phoneContacts.controller;
 
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException; 
  
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith; 
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType; 
import org.springframework.test.context.junit4.SpringRunner; 
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext; 

//TODO: change configure(WebSecurity web) to 
//      web.ignoring().antMatchers("/api/contact/**"); 
//      for execute each test separately

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest { 
	
	
	private static final String CONTACT_URL = "/api/contact";
    
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Before
    public void setUp() {  
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    } 
	
	String contactJsonExamples[] = new String[] { "{\"name\":\"user1\", "
									    		+ "\"emails\": [\"one@mail.ru\", \"two@mail.ru\"], "
									    		+ "\"phones\": [\"+380951112233\", \"0951112233\"]}",
									    		  "{\"name\":\"user2\", "
									    		+ "\"emails\": [\"third@gmail.com\", \"fourth@gmail.com\"], "
									    		+ "\"phones\": [\"0660002233\", \"+380939990033\"]}",};
	 
	@Test
	public void contextLoads() throws Exception {
		this.mockMvc.perform(get(CONTACT_URL)) 
					.andDo(print())
			        .andExpect(status().isOk())
			        .andExpect(content().string(containsString("Welcome to contacts.")));
	}

	@Test
    public void addNewContact() throws Exception {   
		int count = getContactCount();
		System.out.println(count + " <- addNewContact");
		
		String expectedContact = contactJsonExamples[0];
		String actualContact = createContact(contactJsonExamples[0]);
		
		JSONAssert.assertEquals(expectedContact, actualContact, false);
		
		String findCotnactById = this.mockMvc.perform(get(CONTACT_URL + "/1"))
											  .andDo(print())
											  .andReturn().getResponse().getContentAsString();
		JSONAssert.assertEquals(expectedContact, findCotnactById, false); 
    }
	
//	@Test
//    public void updateContact() throws Exception { 
//		String contact = "{\"name\":\"alex\", "
//			    		+ "\"emails\": [\"one@mail.ru\"], "
//			    		+ "\"phones\": [\"+380951112233\"]}";
//		
//		String contactUpdateData = "{\"name\":\"\", "
//						    		+ "\"emails\": [\"added@mail.ru\"], "
//						    		+ "\"phones\": [\"0950006655\"]}";
//		
//		String contactResult = "{\"name\":\"alex\", "
//					    		+ "\"emails\": [\"one@mail.ru\", \"added@mail.ru\"], "
//					    		+ "\"phones\": [\"+380951112233\", \"0950006655\"]}";
//		
//		createContact(contact);
//		
//		String contactActual = this.mockMvc.perform(put(CONTACT_URL + "/1").contentType(MediaType.APPLICATION_JSON)
//											.content(contactUpdateData))
//									        .andDo(print())
//									        .andExpect(status().isOk())
//									        .andReturn().getResponse().getContentAsString();
//		 
//		JSONAssert.assertEquals(contactResult, contactActual, false);  
//    }
	
//	@Test
//    public void deleteContact() throws Exception { 
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		
//		int count = getContactCount();
//		System.out.println(count + " <- deleteContact");
//		
//		createContact(contactJsonExamples[0]); 
//		createContact(contactJsonExamples[1]);
//		 
//		int countContacts = getContactCount();
// 
//		assertThat(countContacts).isEqualTo(2);  
//		
//		this.mockMvc.perform(delete(CONTACT_URL + "/2"))
//			        .andDo(print())
//			        .andExpect(status().isOk());
//		
//		countContacts = getContactCount();
//		
//		assertThat(countContacts).isEqualTo(1);  
//    }
	
	private String createContact(String jsonContactDto) throws Exception {
		String resultContact = this.mockMvc.perform(post(CONTACT_URL + "/create").contentType(MediaType.APPLICATION_JSON)
											.content(jsonContactDto))
								            .andDo(print())
								            .andExpect(status().isCreated())
								            .andReturn().getResponse().getContentAsString();
		return resultContact;
	} 
	
	private int getContactCount() throws UnsupportedEncodingException, Exception {
		String countContacts= this.mockMvc.perform(get(CONTACT_URL + "/count"))
										  .andDo(print())
										  .andReturn().getResponse().getContentAsString();
		return Integer.parseInt(countContacts);
	} 
}
