package br.com.caelum.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TratadorDeMensagens implements MessageListener{

	@Override
	public void onMessage(Message msg) {
		TextMessage textMessage = (TextMessage) msg;
		
		try {
			System.out.println("Tratador recebendo mensagem: "+ textMessage.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
