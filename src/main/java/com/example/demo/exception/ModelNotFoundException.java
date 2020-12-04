package com.example.demo.exception;

import com.example.demo.configurate.OUI;


public class ModelNotFoundException extends RuntimeException {

	private static final long serialVersion = OUI.serialOUI;
	
	public ModelNotFoundException(String mensaje) {
		super(mensaje);
		
	}

}
