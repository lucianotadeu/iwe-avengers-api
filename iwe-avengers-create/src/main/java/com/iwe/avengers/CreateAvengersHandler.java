package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDao;

public class CreateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {
	
	
	private AvengerDao dao = new AvengerDao();

	@Override
	public HandlerResponse handleRequest(final Avenger newAvenger, final Context context) {
		
		
		context.getLogger().log(" [#]Initiante  registry");
		
		final Avenger avenger = dao.create(newAvenger);
		
		final HandlerResponse response =  HandlerResponse.builder()
				.setStatusCode(200)
				.setObjectBody(avenger)
				.build();
		context.getLogger().log("[#] - Avenger resgistered  sucessfuly");

		return response;

	}
}
