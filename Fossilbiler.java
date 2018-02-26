abstract class Fossilbiler extends Bil{
  //Klassen er abstract fordi det ikke gir mening a bare skape et Fossilbiler objekt.
  public Fossilbiler(String regNummer, double utslipp){
    super(regNummer);
    this.utslipp = utslipp;
  }

  protected double utslipp;

  public double returUtslipp(){
    return utslipp;
  }

  @Override
  public void type(){
    super.type();
    System.out.println("CO2-utslipp:" + returUtslipp());
  }
  /*Returner true hvis det er snakk om en Fossilbiler. Hvis det er genrerelt
  bil sa vil den benytte seg av super sin returType. Hvis ingen av delene sa vil
  koden returnere fals.*/
  @Override
  public boolean returnType(String tekst){
    return (tekst.equals("Fossilbiler"));
  }
}
