package com.persequor;


import com.persequor.repository.EventRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {
	
	@InjectMocks
	private EventService service;
    @Mock
    private EventSerializer serializer;
    @Mock
    private EventRepository repository;   

    @Before
    public void setup() {     	
    	MockitoAnnotations.initMocks(this);
    	service = new EventService(serializer, repository);  	         
    }
    
    @Test
    public void serviceProcessMethodCheck() throws Throwable{ 	        
        service.process("{id:'4ba28c38-6c9b-4926-b6f5-0d45794fc41f', createdAt: '2018-01-12T13:37:30', source: 'interweb', action: 'take-over'}");        
    }
    
}