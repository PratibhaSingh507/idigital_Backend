package com.farm.adminTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.farm.controller.AdminController;
import com.farm.model.Admin;
import com.farm.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminController.class)
public class AdminControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AdminService adminService;

	@Test
	public void testNewAdmin() throws Exception {
		String URI = "/admin/add";
		Admin admin = new Admin();
		  admin.setId(1); 
		  admin.setEmail("pranesh@gmail.com");
		  admin.setPassword("Manlite@24");
		  String message = "Admin Created Successfully ";
		 String jsonInput = this.converttoJson(admin);

		Mockito.when(adminService.createAdmin(Mockito.any(Admin.class))).thenReturn(admin);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonOutput).isEqualTo(message);
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}

	@Test
	public void testGetAllAdmin() throws Exception {
		String URI = "/admin/getAll";
		Admin admin = new Admin();
		admin.setId(3);
		admin.setEmail("pranesh@gmail.com");
		admin.setPassword("Manlite@24");

		Admin admin1 = new Admin();
		admin1.setId(4);
		admin1.setEmail("dheena@gmail.com");
		admin1.setPassword("Dheena@123");

		List<Admin> adminList = new ArrayList<>();
		adminList.add(admin);
		adminList.add(admin1);

		String jsonInput = this.converttoJson(adminList);
		Mockito.when(adminService.getAllAdmin()).thenReturn(adminList);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);

	}

	/**
	 * Convert Object into Json String by using Jackson ObjectMapper
	 * 
	 * @param ticket
	 * @return
	 * @throws JsonProcessingException
	 */
	private String converttoJson(Object admin) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(admin);
	}


}
