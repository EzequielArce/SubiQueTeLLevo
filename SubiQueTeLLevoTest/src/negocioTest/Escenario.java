package negocioTest;

import modeloNegocio.Empresa;

public class Escenario {
	public Empresa empresa;
	
	public void setUp() {
		empresa = empresa.getInstance();
	}
	
	public void tearDown() {
		empresa = null;
	}
}
