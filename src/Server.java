class Server implements Observer
  {
   @Override
   public void update(Message m) {
    System.out.println("server 1 :: " + m.getMessageContent());
   }
}
