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
				"      <descri��o>geladeira de duas portas</descri��o>\n"+
				"    </produto>\n"+
				"    <produto codigo=\"1588\">\n"+
				"      <nome>ferro de passar</nome>\n"+
				"      <preco>100.0</preco>\n"+
				"      <descri��o>ferro com vaporizador</descri��o>\n"+
				"    </produto>\n"+
				"  </produtos>\n"+
			    "</compra>";
		
		Compra compra = compraComGeladeiraEFerro();
		
		XStream xstream = xstreamParaCompraEProduto();
		
		String xmlGerado = xstream.toXML(compra);
		assertEquals(xmlEsperado, xmlGerado);
		
		/*Transformando XML em Objeto JAVA*/
	}

	private XStream xstreamParaCompraEProduto() {
		XStream xstream = new XStream();
		xstream.alias("produto", Produto.class);
		xstream.alias("compra", Compra.class);
		xstream.aliasField("descri��o", Produto.class, "descricao");
		xstream.useAttributeFor(Produto.class, "codigo");
		return xstream;
	}

	private Compra compraComGeladeiraEFerro() {
		Produto geladeira = geladeira();
		Produto ferro = ferro();
		
		List<Produto> produtos = new ArrayList<>();
		produtos.add(geladeira);
		produtos.add(ferro);
		Compra compra = new Compra(15, produtos);
		return compra;
	}

	private Produto ferro() {
		return new Produto("ferro de passar", 100.0, "ferro com vaporizador", 1588);
	}

	private Produto geladeira() {
		return new Produto("geladeira", 1000.0, "geladeira de duas portas", 1587);
	}
		
		@Test
		public void deveGerarUmaCompraComCadaUmDosProdutosDoXML(){
			String xmlDeOrigem = "<compra>\n"+
					"  <id>15</id>\n"+
					"  <produtos>\n"+
					"    <produto codigo=\"1587\">\n"+
					"      <nome>geladeira</nome>\n"+
					"      <preco>1000.0</preco>\n"+
					"      <descri��o>geladeira de duas portas</descri��o>\n"+
					"    </produto>\n"+
					"    <produto codigo=\"1588\">\n"+
					"      <nome>ferro de passar</nome>\n"+
					"      <preco>100.0</preco>\n"+
					"      <descri��o>ferro com vaporizador</descri��o>\n"+
					"    </produto>\n"+
					"  </produtos>\n"+
				    "</compra>";
			
			Compra compraEsperada = compraComGeladeiraEFerro();
			
			XStream xstream = xstreamParaCompraEProduto();
			
			Compra compraDeserializada = (Compra)xstream.fromXML(xmlDeOrigem);
			
			assertEquals(compraEsperada, compraDeserializada);
	}
}
