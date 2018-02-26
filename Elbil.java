class Elbil extends Bil{
  public Elbil(String regNummer, double batterikapasitet){
    //Bruker super for aa hente verdier fra superklassen.
    super(regNummer);
    this.batterikapasitet = batterikapasitet;
  }
  private double batterikapasitet;

  public double returBatterikapasitet(){
    return batterikapasitet;
  }
  /* Jeg testet meg paa aa bruke @Override for god praksis */
  @Override
  public void type(){
    System.out.println("Type motorvogn:      Elbil");
    super.type();
    System.out.println("Batterikapasitet (kWh): " + batterikapasitet);
    System.out.println("\n");
  }
  /*Returner true hvis det er snakk om en Elbil. Hvis det er genrerelt
  bil sa vil den benytte seg av super sin returType. Hvis ingen av delene sa vil
  koden returnere fals.*/
  @Override
  public boolean returnType(String tekst){
    return (tekst.equals("Elbiler"));
  }
}
