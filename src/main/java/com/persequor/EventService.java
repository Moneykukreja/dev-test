package com.persequor;

import com.google.gson.Gson;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;
import com.persequor.model.Event;
import com.google.gson.GsonBuilder;
import com.persequor.repository.EventRepository;
import com.persequor.exceptions.EventServiceException;
import com.persequor.repository.exceptions.EventRepositoryErrorException;


public class EventService implements EventRepository {

	Event event;
	private final EventSerializer eventSerializer;
    private final EventRepository repository;
    static Logger logger = Logger.getLogger(EventService.class); 
    
    
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
											   .serializeNulls()
											   .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonConverter())
											   .create();
    
    EventSerializer eventSerializer1 = new EventSerializer(gson);    

    public EventService(EventSerializer eventSerializer, EventRepository repository) {    	
        this.eventSerializer = eventSerializer;
        this.repository = repository;       
    }   
    
    public void process(String serializedEvent) throws EventServiceException {    	
    	System.out.println("String is "+serializedEvent);    	
    	event = eventSerializer1.deserialize(serializedEvent); // call to Deserialize Method of EventSerializer 
	}

	@Override
	public void persist(Event event) throws EventRepositoryErrorException {
		// TODO Auto-generated method stub
	}	
}
