package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.TraceResponse;

public interface TraceResponseService {

	public abstract List<TraceResponse> listAllTraceResponse();

	public abstract TraceResponse addTraceResponse(TraceResponse traceResponse);
	
}
