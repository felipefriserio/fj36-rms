package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviaMensagemParaOTopico {
	
	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		ConnectionFactory factory = 
				(ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		
		Topic topico = (Topic) ic.lookup("jms/TOPICO.LIVRARIA");

		
		try(JMSContext context = factory.createContext("jms2","jms2")){
			JMSProducer producer = context.createProducer();
			producer.setProperty("formato", "ebook");
			
			Scanner scanner = new Scanner(System.in);
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				producer.send(topico,line);
			}
			scanner.close();
		}
		
		/*try(JMSContext context = factory.createContext("jms2","jms2")){
			context.setClientID("GeradorEbook");
			JMSConsumer consumer = context.createDurableConsumer(
			topico,"AssinaturaEbook", "formato='ebook'", false);
			consumer.setMessageListener(new TratadorDeMensagens());
			context.start();
			
			
			Scanner teclado = new Scanner(System.in);
			System.out.println("Gerador d=esperando as mensagens do topico...");
			System.out.println("Aperte enter para fechar a conexao");
			
			teclado.nextLine();
			teclado.close();
			context.close();
		}
*/		
		
	}

}
