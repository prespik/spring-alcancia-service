package com.prespik.alcanciaservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prespik.alcanciaservice.service.AlcanciaService;

/**
 * @author prespik
 */
@CrossOrigin(maxAge = 3600)		//Se habilita el CORS para que la pagina pueda hacer fetch de los datos
@RestController
@RequestMapping("alcancia")
public class AlcanciaController {
	
	@Autowired
	private AlcanciaService alcanciaService;

	@GetMapping("/{cantidad}/{denominacion}")
	public String ingresarMonedas(@PathVariable Integer cantidad, @PathVariable Integer denominacion) {
		//return alcanciaService.retrieveCourses(studentId);
				
		if( alcanciaService.recibirDinero(denominacion, cantidad) ) {
			return "Se ingresó a la Alcancia ["+ cantidad + "]" +  " Monedas de " + denominacion + " pesos";
		}else {
			return "Denominacion ["+ denominacion + "]" +  " No Valida\n" + " Intente con [50,100,200,500,1000]";
		}	
				
	}
	
	@GetMapping("/monedas/{denominacion}")
	public String getMonedasPorDenominacion(@PathVariable Integer denominacion) {
		//return alcanciaService.retrieveCourses(studentId);
		return "Cantidad de Monedas por Denominacion ["+ denominacion +"]: " + alcanciaService.getCantidadMonedasDenominacion(denominacion) ;		
	}
	
	@GetMapping("/monedas")
	public String getCantidadMonedas() {
		//return alcanciaService.retrieveCourses(studentId);
		return "Cantidad de Monedas en la Alcancia: " + alcanciaService.getCantidadMonedas();		
	}
	
	@GetMapping("/dinero/{denominacion}")
	public String getDineroPorDenominacion(@PathVariable Integer denominacion) {
		//return alcanciaService.retrieveCourses(studentId);
		return "Cantidad de Dinero por Denominacion ["+ denominacion +"]: " + alcanciaService.getCantidadDineroDenominacion(denominacion) + " pesos";		
	}
	
	@GetMapping("/dinero")
	public String getCantidadDinero() {
		return "Cantidad de Dinero en la Alcancia: " + alcanciaService.getCantidadDinero() + " pesos";		
	}
	
	@GetMapping("/status/check")
	public String status() {
		return "working";
	}
	
	
}