package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDao;

public class RemoveAvengerHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDao dao = new AvengerDao();
	
	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {

		context.getLogger().log(" [#]Initiante  registry");
		final String id = avenger.getId();
		context.getLogger().log("[#] - Searching Avenger with id:" + id);
		
		final Avenger retrivedAvanger = dao.delete(id);
		
		if(retrivedAvanger == null) {
			throw new AvengerNotFoundException("[NotFound] - Avenger id:" + id + "nota found");
			
		}
		
		final HandlerResponse response =  HandlerResponse.builder()
				.setStatusCode(204)
				.setObjectBody(avenger)
				.build();
		context.getLogger().log("[#] - Avenger delete  sucessfuly");

		return response;
	}
}
