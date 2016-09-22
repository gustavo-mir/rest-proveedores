package com.sancorsalud.service.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import com.sancorsalud.common.error.GlobalException;
import com.sancorsalud.entity.Proveedor;
import com.sancorsalud.service.ProveedorService;

@RunWith(Parameterized.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ProveedorTest {

	/*
	 * Variable para cargar el contexto, trabajar con la capa se servicio e inyectar dependencias
	 * 
	 * */
	private TestContextManager testContextManager;

    @Before
    public void setUpSpringContext() throws Exception {
        testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }

	/*
	 * Las reglas se utilizan para añadir funcionalidad adicional que se aplica a todos los test
	 * Esta regla se aplicará a cada uno de los test de la clase (si se vence el timeout hace que el test falle)
	 * Tiene en consideración el tiempo de ejecución de los métodos @Before y @After que acompañan a cada test
	 * 
	 * */
	@Rule
	public Timeout timeout = Timeout.millis(10000);

    /*
     * Inject de componentes de la capa de servicio
     * 
     * */
	@Inject
	private ProveedorService proveedorService;

    /*
     * Parametros a ser utilizados por cada invocación de un test
     * 
     * */
	@Parameters
    public static Collection<Object[]> data() {
    	return Arrays.asList(new Object[][] {
    		{ true, 1026, 0 }, 
    		{ false, 1028, 0 }
    	});
	}

	/*
	 * Variables de trabajo a ser utilizadas por @Parameters en cada uno de los test
	 * 
	 * */
    private boolean ejecutarTest;
    private int numero;
    private int subNumero;

    public ProveedorTest(boolean ejecutarTest, int numero, int subNumero) {
    	this.ejecutarTest = ejecutarTest;
        this.numero= numero;
        this.subNumero = subNumero;
    }

	/*
	 * Se ejecuta tantas veces como parametros definidos en la colección
	 * 
	 * */
	@Test(timeout=5000)
	public void testGetPorNumero() {

		try {
			Proveedor proveedor = proveedorService.getProveedor(numero, subNumero);
			if (proveedor == null) {
				fail("PROVEEDOR NO ENCONTRADO");
			}

		} catch (GlobalException e) {
			fail("ERROR EN: proveedorService.getProveedor()");
		}
	}

	/*
	 * Se ejecuta tantas veces como parametros definidos en la colección
	 * 
	 * */
	@Test
	public void testGetPorNombre() {

		/*
		 * Si bien el test este se ejecuta N veces, 
		 * el código del test solo se ejecuta solo la primera vez gracias a la variable 'ejecutarTest'  
		 * 
		 * */
		if (ejecutarTest) {

			try {
				List<Proveedor> proveedores = proveedorService.getProveedores("FARMACIA");
				if (proveedores == null) {
					fail("PROVEEDOR NO ENCONTRADO");
				}

				/*
				 * Verifica si el método controla la cantidad mínima de caracteres para buscar por nombre
				 * Sino controla, debería fallar por timeout...
				 * 
				 * */
				proveedores = proveedorService.getProveedores("F");

			} catch (GlobalException e) {
				fail("ERROR EN: proveedorService.getProveedores()");
			}
		}
	}

}