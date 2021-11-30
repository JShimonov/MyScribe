interface Subject //application
{
 void attach(Observer o);
 void detach(Observer o);
 void notifyUpdate(Message m);
}