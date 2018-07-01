package Main_Robot;

import java.util.ArrayList;
import java.util.Random;
import java.time.*;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;



public class App extends ListenerAdapter
{
	public static void main( String[] args ) throws Exception   {
		JDA jda = new JDABuilder(AccountType.BOT).setToken(Ref.token).buildBlocking();
		jda.addEventListener(new App());
	}


	ArrayList<String> reminders = new ArrayList<String>();
	ArrayList<String> usertime = new ArrayList<String>();
	ArrayList<String> time = new ArrayList<String>();
	@Override
	public void onMessageReceived(MessageReceivedEvent evt) {

		
		//Objects
		User objUser = evt.getAuthor();
		MessageChannel objMsgCh = evt.getChannel();
		Message objMsg = evt.getMessage();
		Random generator = new Random();

		//Commands
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "ping")) {
			objMsgCh.sendMessage(objUser.getAsMention() + " Pong!").queue();
		}

		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "dab")) {
			String[] dab = {"I dab on your statements", "I dab on u cuz ur bad", "Dab on the Haters(that's u)", "Dab on the dabbers(that u 2)", "Ur mum dabbed on you in your sleep", "Dab on the h8'rs", "How do you dab without hands?", "Just dab on all of your problems", "When dabbing always dab"};
			objMsgCh.sendMessage(dab[generator.nextInt(dab.length)]).queue();
		}

		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "8ball")) {
			int temp = generator.nextInt(100);

			if (temp >= 0 && temp < 20) {
				objMsgCh.sendMessage("Yes").queue();
			}

			else if (temp >=20 && temp < 40) {
				objMsgCh.sendMessage("No").queue();
			}

			else if (temp >=40 && temp < 60) {
				objMsgCh.sendMessage("I can't answer that. Try asking Daddy Tek.").queue();
			}

			else if (temp >= 60 && temp < 80) {
				objMsgCh.sendMessage("Maybe the answer you're looking for is inside of you").queue();
			}

			else if (temp >= 80 && temp <= 100) {
				objMsgCh.sendMessage("Ok how about you just D A B on that question??").queue();
			}

			else {
				objMsgCh.sendMessage("Tell Tek he's a bad programmer").queue();
			}

		}

		if (objMsg.getContentRaw().startsWith(Ref.prefix + "remind")) {
			if(objMsg.getContentRaw() != (Ref.prefix + "remindme")){
				String message = objMsg.getContentRaw();
				message = message.replaceAll("!remind", "");

				if (objMsg.getContentRaw().startsWith(Ref.prefix + "remindme") == false) {
					reminders.add(message);
					objMsgCh.sendMessage("Reminder Saved!").queue();
				}

			}

			for (int i = 0; i < reminders.size(); i++) {
				if (reminders.get(i) == "") {
					reminders.remove(i);
				}
			}

		}

		if (objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "remindme")) {
			for (int i = 0; i < reminders.size(); i++) {
				objMsgCh.sendMessage( (i + 1) + "." + reminders.get(i)).queue(); 
			}
		}
		
		if (objMsg.getContentRaw().startsWith(Ref.prefix + "music")) {
			String message = objMsg.getContentRaw();
			message = message.replaceAll("!music", "");
			
		}
		
		if (objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "setTime")) {
			String timing = set();
			usertime.add(objUser.getId());
			time.add(timing);
			
			objMsgCh.sendMessage("Time Set! Have Fun!").queue();
			
			
		}

		if (objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "getTime")) {
			boolean gotId = false;
			
			for (int i = 0; i < usertime.size(); i++) {
				if (objUser.getId().equals(usertime.get(i))) {
					objMsgCh.sendMessage("Your Set time is " + time.get(i)).queue();
					gotId = true;
				}
			}
			
			if (gotId == false) {
				objMsgCh.sendMessage("Set a time using !setTime !").queue();
			}
			
		}
		
		if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix + "present")) {
			
		}
		

	}

	
	private String set() {
		
		LocalTime time = LocalTime.now();
		return time.toString();
		
		
	}

}
