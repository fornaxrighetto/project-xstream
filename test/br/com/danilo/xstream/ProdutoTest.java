package br.com.danilo.xstream;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class ProdutoTest {
	/*
	 * Vamos garantir que usando o XStream
	 * Ele deve gerar o XML com o nome - Preco e Descricao Adequados
	 * */
	
	@Test
	public void deveGerarOXMLAdequadamente(){
		String xmlEsperado = "<produto codigo=\"1587\">\n"+
				"  <nome>geladeira</nome>\n"+
				"  <preco>1000.0</preco>\n"+
				"  <descricao>geladeira duas portas</descricao>\n"+
				"</produto>";
		
		Produto geladeira = new Produto("geladeira", 1000.0, "geladeira duas portas", 1587);
		
		XStream xstream = new XStream();
		/*Vamos falar pro XStream para que sempre que aparecer
		 * a classe Produto.class ele chamar apenas de 'produto'
		 * veja o exemplo abaixo */
		xstream.alias("produto", Produto.class);
		
		/*Adicionando um campo ao atributo 'produto'
		 * Exemplo: '<produto codigo=1234>'
		 * */
		xstream.useAttributeFor(Produto.class, "codigo");
		String xmlGerado = xstream.toXML(geladeira);
		
		assertEquals(xmlEsperado, xmlGerado);
		
		/*O XStream por padrao pega o nome do pacote na serializacao
		 * exemplo: br.com.danilo.xstream 
		 * */
	}
}
