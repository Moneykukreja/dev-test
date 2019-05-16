package com.persequor;

import java.util.UUID;
import com.google.gson.Gson;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;
import com.persequor.model.Event;
import com.persequor.exceptions.EventServiceErrorException;

public class EventSerializer  {
	
    private final Gson gson;
    static Logger logger = Logger.getLogger(EventSerializer.class);    
    
    public EventSerializer(Gson gson) {
        this.gson = gson;
    }

    public Event deserialize(String serializedEvent) { 
       	Event eventObject = gson.fromJson(serializedEvent, Event.class);   //deserialize Json
       	return eventObject;         
    }

    public String serialize(Event event) {
    	String eventJson = null;
    	String action = event.getAction();
    	LocalDateTime date = event.getCreatedAt();
    	UUID id = event.getId();
    	String source = event.getSource();
    	
    	if(action == null || date == null || source == null || id == null) //check for any Null Value
    	{
    		try {
				throw new EventServiceErrorException("PER_400_1");
			} catch (EventServiceErrorException message) {
				System.out.println(message);
				logger.info(message);
			}           
    	}
    	else if(action == "" || source == "")  //check for blank Values
    	{
    		try {
				throw new EventServiceErrorException(" PER_400_2 ");
			} catch (EventServiceErrorException message) {
				System.out.println(message);
				logger.info(message);
			} 
    	}
    	else
    	{
    		eventJson = gson.toJson(event);
    		logger.info(" PER_200 ");
    	}
    	
    	return eventJson; //Serialize Json
        
    }
}
