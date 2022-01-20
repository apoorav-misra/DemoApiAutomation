package com.adidas.test.automation;

import com.adidas.test.api.pojo.PojoRequest;
import com.adidas.test.api.pojo.Category;
import com.adidas.test.api.pojo.Tag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiOperations {

	ConfigProvider provider = new ConfigProvider();
	Map<String, String> map = provider.getPropValues();
	Response response;
	PojoRequest request = new PojoRequest();
	Category category = new Category();
	Tag tag = new Tag();
	List<String> photoUrls = new ArrayList<>();
	List<Tag> tagList = new ArrayList<>();

	private String createRequestBody(int id, String name, String status) {
		category.setId(0);
		category.setName("string");
		photoUrls.add("string");
		tag.setId(0);
		tag.setName("string");
		tagList.add(tag);
		request.setId(id);
		request.setCategory(category);
		request.setName(name);
		request.setPhotoUrls(photoUrls);
		request.setTags(tagList);
		request.setStatus(status);
		ObjectMapper mapper = new ObjectMapper();
		String payload = null;
		try {
			payload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return payload;
	}

	public Response responseFromGetRequest(String status) {
		RestAssured.baseURI = map.get("getURI") + status;
		response = RestAssured.given().
				   headers("Content-Type", "application/json").
				   contentType(ContentType.JSON).accept(ContentType.JSON).
				   when().
				   get();
		return response;
	}

	public Response responseFromPostRequest(int id, String name, String status) {
		RestAssured.baseURI = map.get("postURI");
		String payload = createRequestBody(id,name,status);
		response = RestAssured.given().
				   header("Content-Type", "application/json").
				   contentType(ContentType.JSON).accept(ContentType.JSON).
				   body(payload).
				   when().
				   post();
		return response;
	}

	public Response responseFromPutRequest(int id, String name, String status) {
		RestAssured.baseURI = map.get("putURI");
		String payload = createRequestBody(id,name,status);
		response = RestAssured.given().
				   header("Content-Type", "application/json").
				   contentType(ContentType.JSON).accept(ContentType.JSON).
				   body(payload).
				   when().
				   put();
		return response;
	}

	public Response responseFromDeleteRequest(int id) {
		RestAssured.baseURI = map.get("deleteURI") + id;
		response = RestAssured.given().
				   header("Content-Type", "application/json").
				   contentType(ContentType.JSON).accept(ContentType.JSON).
				   when().
				   delete();
		return response;
	}

}
