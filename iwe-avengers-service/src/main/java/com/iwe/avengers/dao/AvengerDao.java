package com.iwe.avengers.dao;

import java.util.Optional;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.dynamodb.manager.DynamoDBManager;

public class AvengerDao {
	
	private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

	private static AvengerDao instance;

	public AvengerDao() {
	}

	public static AvengerDao getInstance() {
		if (instance == null) {
			instance = new AvengerDao();
		}
		return instance;
	}

	public Avenger find(final String id) {
		final Avenger avenger = mapper.load(Avenger.class, id);
		return Optional.ofNullable(avenger).get();
	}

	public Avenger create(final Avenger newavenger) {
		mapper.save(newavenger);
		return newavenger;
	}

	public void delete(Avenger avenger) {
		mapper.delete(avenger);
	}



}

