public class Source {



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