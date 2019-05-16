package com.persequor.main;

import java.time.LocalDateTime;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.persequor.EventSerializer;
import com.persequor.EventService;
import com.persequor.exceptions.EventServiceErrorException;
import com.persequor.model.Event;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class persequorMain {
	static Logger logger = Logger.getLogger(persequorMain.class);
	
	public static void main(String[] args) {
		
		//PropertiesConfigurator is used to configure logger from properties file
        PropertyConfigurator.configure("log4j.properties");
        
		final Gson gson = new GsonBuilder().create();
	    EventSerializer serialize = new EventSerializer(gson);
		
		//Serialize a object and return Json String
		Event event = new Event();
        event.setCreatedAt(LocalDateTime.now());
        event.setId(UUID.randomUUID());
        event.setAction("take-over");
        event.setSource("interweb");

        String serialize_str =null;
		serialize_str = serialize.serialize(event);      
        System.out.println("Serialize String --->" +serialize_str);
        logger.info("Serialize String ---> "+serialize_str);
        
        //Deserialize Same String and return Object
        
        Event de_serialize_event = serialize.deserialize(serialize_str);
        System.out.println(de_serialize_event.getId());
        System.out.println(de_serialize_event.getAction());
        System.out.println(de_serialize_event.getCreatedAt());
        System.out.println(de_serialize_event.getSource());
        logger.info("DeSerialize String ---> "+de_serialize_event.getId() + " "
        		+de_serialize_event.getAction() + " "
        		+de_serialize_event.getCreatedAt() + " "
        		+de_serialize_event.getSource());        
	}

}
