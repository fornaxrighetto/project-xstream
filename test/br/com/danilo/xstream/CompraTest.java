package br.com.danilo.xstream;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class CompraTest {
	/*Testando a classe Compra*/
	
	@Test
	public void deveGerarCadaUmDosProdutosDeUmaCompra(){
		String xmlEsperado = "<compra>\n"+
				"  <id>15</id>\n"+
				"  <produtos>\n"+
				"    <produto codigo=\"1587\">\n"+
				"      <nome>geladeira</nome>\n"+
				"      <preco>1000.0</preco>\n"+
				"      <descrição>geladeira de duas portas</descrição>\n"+
				"    </produto>\n"+
				"    <produto codigo=\"1588\">\n"+
				"      <nome>ferro de passar</nome>\n"+
				"      <preco>100.0</preco>\n"+
				"      <descrição>ferro com vaporizador</descrição>\n"+
				"    </produto>\n"+
				"  </produtos>\n"+
			    "</compra>";
		
		Produto geladeira = new Produto("geladeira", 1000.0, "geladeira de duas portas", 1587);
		Produto ferro = new Produto("ferro de passar", 100.0, "ferro com vaporizador", 1588);
		
		List<Produto> produtos = new ArrayList<>();
		produtos.add(geladeira);
		produtos.add(ferro);
		Compra compra = new Compra(15, produtos);
		
		
		XStream xstream = new XStream();
		xstream.alias("produto", Produto.class);
		xstream.alias("compra", Compra.class);
		xstream.aliasField("descrição", Produto.class, "descricao");
		xstream.useAttributeFor(Produto.class, "codigo");
		
		String xmlGerado = xstream.toXML(compra);
		assertEquals(xmlEsperado, xmlGerado);
		
		/*Transformando XML em Objeto JAVA*/
	}
		
		@Test
		public void deveGerarUmaCompraComCadaUmDosProdutosDoXML(){
			String xmlDeOrigem = "<compra>\n"+
					"  <id>15</id>\n"+
					"  <produtos>\n"+
					"    <produto codigo=\"1587\">\n"+
					"      <nome>geladeira</nome>\n"+
					"      <preco>1000.0</preco>\n"+
					"      <descrição>geladeira de duas portas</descrição>\n"+
					"    </produto>\n"+
					"    <produto codigo=\"1588\">\n"+
					"      <nome>ferro de passar</nome>\n"+
					"      <preco>100.0</preco>\n"+
					"      <descrição>ferro com vaporizador</descrição>\n"+
					"    </produto>\n"+
					"  </produtos>\n"+
				    "</compra>";
			
			Produto geladeira = new Produto("geladeira", 1000.0, "geladeira de duas portas", 1587);
			Produto ferro = new Produto("ferro de passar", 100.0, "ferro com vaporizador", 1588);
			
			List<Produto> produtos = new ArrayList<>();
			produtos.add(geladeira);
			produtos.add(ferro);
			Compra compraEsperada = new Compra(15, produtos);
			
			XStream xstream = new XStream();
			xstream.alias("produto", Produto.class);
			xstream.alias("compra", Compra.class);
			xstream.aliasField("descrição", Produto.class, "descricao");
			xstream.useAttributeFor(Produto.class, "codigo");
			
			Compra compraDeserializada = (Compra)xstream.fromXML(xmlDeOrigem);
			
			assertEquals(compraEsperada, compraDeserializada);
	}
}
