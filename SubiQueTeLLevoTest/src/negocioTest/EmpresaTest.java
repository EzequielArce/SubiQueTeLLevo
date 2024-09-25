package negocioTest;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import excepciones.ChoferNoDisponibleException;
import excepciones.ChoferRepetidoException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteSinViajePendienteException;
import excepciones.PedidoInexistenteException;
import excepciones.VehiculoNoDisponibleException;
import excepciones.VehiculoNoValidoException;
import excepciones.VehiculoRepetidoException;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Vehiculo;
import modeloDatos.Pedido;

public class EmpresaTest {
	private Escenario escenario = new Escenario();
	
	@Before
	public void setUp() {
		escenario.setUp();
	}
	
	@After
	public void tearDown() {
		escenario.tearDown();
	}
	
	@Test
	public void testAgregarChofer_Clase1() {
		try {
			escenario.empresa.agregarChofer(new ChoferTemporario("32909289","julian"));
			if(!escenario.empresa.getChoferes().containsKey("32909289"))
				fail("No se añadio a la coleccion de choferes");
		} catch (ChoferRepetidoException e) {
			fail("La coleccion de choferes esta vacia, no deberia entrar aqui");
		}
	}
	
	@Test
	public void testAgregarVehiculo_Clase1() {
		try {
			escenario.empresa.agregarVehiculo(new Combi("AA223FF", 9, true));
			if(!escenario.empresa.getVehiculos().containsKey("AA223FF"))
				fail("No se añadio a la coleccion de vehiculos");
		} catch (VehiculoRepetidoException e) {
			fail("La coleccion de vehiculos esta vacia, no deberia entrar aqui");
		}
	}
	
	@Test
	public void testCrearViaje_Clase1() {
		Cliente cliente = new Cliente("Esteban Gutierrez", "12345678", "estaban1200");   
		Chofer chofer = new ChoferTemporario("77893456", "Marcos");
		Vehiculo vehiculo = new Auto("HG676JK",5,false);
		
		try {
			escenario.empresa.crearViaje(new Pedido(cliente,3,false,false,3,"ZONA_SIN_ASFALTAR"), chofer, vehiculo);
			fail("Debio salir por alguna excepcion");
		} catch (PedidoInexistenteException e) {
			
		} catch (ChoferNoDisponibleException e) {
			
		} catch (VehiculoNoDisponibleException e) {
			
		} catch (VehiculoNoValidoException e) {
			
		} catch (ClienteConViajePendienteException e) {
			fail("No es posible, ya que el cliente no esta en la lista de clientes ni tiene viajes pendientes");
		}
	}
	
	@Test
	public void testPagarYFinalizarViaje_Clase1() {
		try {
			escenario.empresa.pagarYFinalizarViaje(4);
			fail("Debio saltar una excepcion");
		} catch (ClienteSinViajePendienteException e) {
			
		} catch (Exception e) {
			fail("Salto por otra excepcion, posible porque no hay nadie logeado");
		}
	}
	
	@Test
	public void testValidarPedido_Clase1() {
		Cliente cliente = new Cliente("Esteban Gutierrez", "12345678", "estaban1200");   

		escenario.empresa.validarPedido(new Pedido(cliente,3,false,false,3,"ZONA_SIN_ASFALTAR"));
	}
}
