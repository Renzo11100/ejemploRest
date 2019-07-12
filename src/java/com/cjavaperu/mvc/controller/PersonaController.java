package com.cjavaperu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cjavaperu.mvc.bean.Usuario;

@Controller
@RequestMapping("rest")
public class PersonaController {

	@RequestMapping(value = "prueba1", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String prueba1() {
		return "prueba1";
	}

	@RequestMapping(value = "prueba2", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String prueba2(@RequestBody String parametro) {
		return "prueba2 [" + parametro + "]";
	}

	@RequestMapping(value = "prueba3", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String prueba3(@ModelAttribute("parametro") String parametro) {
		return "prueba3 [" + parametro + "]";
	}

	@RequestMapping(value = "cambioDolar/{tipo}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public double cambioDolar(@PathVariable Integer tipo) {
		double resultado = 0;
		if (tipo != null) {
			if (tipo == 1) // COMPRA
				resultado = 2.80;
			else
				// VENTA
				resultado = 2.77;
		}
		return resultado;
	}
	
	@RequestMapping(value = "consumir", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String consumir() {
		
		String url = "http://192.168.14.121:8085/s19_ejemplo02/rest/cambioDolar/1";

		RestTemplate restTemplate = new RestTemplate();
		String resultado = restTemplate.getForObject(url, String.class);
		
		return resultado;
	}
	
	@RequestMapping(value = "json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Usuario json() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setUsuario("admin");
		return usuario;
	}
	
	@RequestMapping(value = "xml", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	@ResponseBody
	public Usuario xml() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setUsuario("admin");
		return usuario;
	}
	
	@RequestMapping(value = "jsonxml", 
			        method = RequestMethod.GET, 
			        produces = "application/json; charset=utf-8",
			        headers = "Accept=application/json,application/xml")
	@ResponseBody
	public Usuario jsonxml() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setUsuario("admin");
		return usuario;
	}

}