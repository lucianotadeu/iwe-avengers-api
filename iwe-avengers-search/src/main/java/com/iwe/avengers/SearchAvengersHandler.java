package com.iwe.avengers;

import java.util.NoSuchElementException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDao;


public class SearchAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDao dao = new AvengerDao();
	
	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {
		
		final String id = avenger.getId();
		
		try {
			
			context.getLogger().log("[#] - Searching Avenger with id:" + id);
			final Avenger retrivedAvanger = dao.find(id);
						
			final HandlerResponse response =  
					HandlerResponse.builder()
					.setStatusCode(200)
					.setObjectBody(retrivedAvanger)
					.build();
			
			context.getLogger().log("[#] response - Avenger found! =) " );
		
			return response;
			
		} catch (NoSuchElementException e) {
			throw new AvengerNotFoundException("[NotFound] - Avenger id:" + id + "not found");
		}
		
		
	}
}
