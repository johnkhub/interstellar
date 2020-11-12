package com.johnk.interstellar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnk.interstellar.model.PathPlanet;
import com.johnk.interstellar.model.Planet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTests {
	
	private static final int PathPlanet = 0;

	@LocalServerPort
	private int port;
	
	TestRestTemplate testRestTemplate=new TestRestTemplate();
	
	HttpHeaders headers=new HttpHeaders();
	
	@Test
    public void testCreatePlanet() throws Exception {
		String url = this.makeURLWithPort("planets");
		Planet pnt=new Planet("AA");
        HttpEntity<Planet> entity = new HttpEntity<Planet>(pnt, headers);
        ResponseEntity<Planet> response = testRestTemplate.exchange(url, HttpMethod.POST, entity, Planet.class);
        //Check
        assertEquals(201, response.getStatusCodeValue());  // create successfully
    }    

    @Test
    public void testGetPlanetById() throws Exception {
    	String url = this.makeURLWithPort("planets/{id}");
    	long id=1;
    	HttpEntity<Planet> entity = new HttpEntity<Planet>(null, headers);
    	ResponseEntity<Planet> response = testRestTemplate.exchange(url, HttpMethod.GET,entity, Planet.class,id);
    	assertEquals(response.getStatusCode(), HttpStatus.OK); 
    	assertThat(response.getBody().getPlanet_name()).isEqualTo("A");
    }
     
	@Test
    public void testGetAllPlanets() throws Exception {
	    String url = this.makeURLWithPort("planets/");
	    ResponseEntity<Planet[]> response = testRestTemplate.getForEntity(url, Planet[].class);
	    assertEquals(response.getStatusCode(), HttpStatus.OK);
	    assertEquals(38,response.getBody().length);    
    }
    
    @Test
    public void testUpdatePlanet() {
    	String url = this.makeURLWithPort("planets/{id}");
    	long id=1;
	    Planet planet = new Planet();
	    planet.setPlanet_name("AB");
	    HttpEntity<Planet> entity = new HttpEntity<Planet>(planet, headers);
    	ResponseEntity<Planet> response = testRestTemplate.exchange(url, HttpMethod.PUT,entity, Planet.class,id);
	    // Check
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED); // assertEquals(response.getStatusCode(), HttpStatus.OK); 
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(response.getBody().getPlanet_name()).isEqualTo("AB");
    }
    
    @Test
    public void testDeletePlanet() {
        String url = this.makeURLWithPort("planets/{id}");
        long id= 1;
        ResponseEntity<Boolean> response = testRestTemplate.exchange(url,
                HttpMethod.DELETE,HttpEntity.EMPTY,Boolean.class,id);
       // Check
       assertThat(response.getBody()).isEqualTo(true);
       assertEquals(response.getStatusCode(), HttpStatus.OK);           
    }
    
    @Test
    public void testCalculateDistance() {
        String url = this.makeURLWithPort("mindist/{dest}");
        String dest= "F";
        HttpEntity<Planet> entity = new HttpEntity<Planet>(null, headers);
    	ResponseEntity<Double> response = testRestTemplate.exchange(url, HttpMethod.GET,entity, Double.class,dest);
    	assertEquals(response.getStatusCode(), HttpStatus.OK); 
    	assertThat(response.getBody().doubleValue()).isEqualTo(1.2);         
    }
      
    private String makeURLWithPort(String uri) {
        return "http://localhost:" + port+"/interstellar/" + uri;
    }
			
}
