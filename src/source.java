import java.util.ArrayList;
import java.util.List;



  interface Subject //application
  {
   void attach(Observer o);
   void detach(Observer o);
   void notifyUpdate(Message m);
  }

   class Application implements Subject {

   private List<Observer> observers = new ArrayList<>();

   @Override
   public void attach(Observer o) {
    observers.add(o);
   }

   @Override
   public void detach(Observer o) {
    observers.remove(o);
   }

   @Override
   public void notifyUpdate(Message m) {
    for(Observer o: observers) {
     o.update(m);
    }
   }
  }


  interface Observer //server
  {
   public void update(Message m);
  }


   class Server implements Observer
  {
   @Override
   public void update(Message m) {
    System.out.println("server 1 :: " + m.getMessageContent());
   }
  }

   class Message
  {
   final String messageContent;

   public Message (String m) {
    this.messageContent = m;
   }

   public String getMessageContent() {
    return messageContent;
   }
  }



public class source {



  public static void main(String[] args) {
   Server s1 = new Server(); // create server on which myscribe will be running on

   Application myscribe = new Application(); // create the application myscribe for the connection

   myscribe.attach(s1); //  myscribe will update the connection to server

   myscribe.notifyUpdate(new Message("application has conected to server"));   // once it connects it will now
                                                                                  // notify to us that it has connected

   myscribe.detach(s1); // once we are done with mysribe it will now disconnect
   myscribe.attach(s1); // application has now updated the disconection from the server
   myscribe.notifyUpdate(new Message("application has disconnected from server")); // once it has disconnected it will
                                                                                      // notify of the update.
  }
 }