package com.persequor;

import com.google.gson.GsonBuilder;
import com.persequor.model.Event;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
 
@RunWith(JUnit4.class)
public class EventSerializerTest {
	
	private EventSerializer serializer;
	static Logger logger = Logger.getLogger(EventSerializer.class);   

    @Before
    public void setup() {
        serializer = new EventSerializer(new GsonBuilder().setPrettyPrinting()
				.serializeNulls()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonConverter())
				.create());
        PropertyConfigurator.configure("log4j.properties");
    }
    
     // Write all the tests you need    
    
    @Test
    public void correctValuesSerializeTest() throws Throwable {
    	
        Event event = new Event();
        event.setCreatedAt(LocalDateTime.of(2018, Month.JANUARY, 12, 13, 37, 30));
        event.setId(UUID.fromString("4ba28c38-6c9b-4926-b6f5-0d45794fc41f"));
        event.setAction("take-over");
        event.setSource("interweb");

        String actual_str = serializer.serialize(event);
        Event actual = serializer.deserialize(actual_str);
        
        assertEquals(actual.getId(), event.getId());
        assertEquals(actual.getAction(), event.getAction());
        assertEquals(actual.getCreatedAt(), event.getCreatedAt());
        assertEquals(actual.getSource(), event.getSource());
        
    }
    
    @Test
    public void nullValueSerializeCheckTest() throws Throwable {
    	
        Event event = new Event();
        event.setCreatedAt(LocalDateTime.now());
        event.setId(UUID.randomUUID());
        event.setAction(null);
        event.setSource("interweb");

        String actual_str = serializer.serialize(event);
        Event actual = serializer.deserialize(actual_str);      
        
        System.out.println(" Serialize String After Null Check --->" +actual_str);
    }
    
    @Test
    public void blankValueSerializeCheckTest() throws Throwable {
        Event event = new Event();
        event.setCreatedAt(LocalDateTime.now());
        event.setId(UUID.randomUUID());
        event.setAction("");
        event.setSource("interweb");

        String str = serializer.serialize(event);
        System.out.println(" Serialize String After Blank Check --->" +str);
    }

    @Test
    public void correctValuesDeSerializeTest() throws Throwable {
    	
        Event actual = serializer.deserialize("{id:'4ba28c38-6c9b-4926-b6f5-0d45794fc41f', createdAt: '2018-01-12T13:37:30', source: 'interweb', action: 'take-over'}");
    	        
        Event expectedOutput = new Event();
        expectedOutput.setCreatedAt(LocalDateTime.of(2018, Month.JANUARY, 12, 13, 37, 30));
        expectedOutput.setId(UUID.fromString("4ba28c38-6c9b-4926-b6f5-0d45794fc41f"));
        expectedOutput.setAction("take-over");
        expectedOutput.setSource("interweb");
                
        assertEquals(actual.getId(), expectedOutput.getId());
        assertEquals(actual.getAction(), expectedOutput.getAction());
        assertEquals(actual.getCreatedAt(), expectedOutput.getCreatedAt());
        assertEquals(actual.getSource(), expectedOutput.getSource());
        
        System.out.println("Event is "+expectedOutput);
        
    }
    
        
}
