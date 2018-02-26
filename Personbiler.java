class Personbiler extends Fossilbiler{
  public Personbiler(String regNummer, double utslipp, int seter){
    super(regNummer, utslipp);
    this.seter = seter;
  }

  private int seter;

  public int antSeter(){
    return seter;
  }

  @Override
  public void type(){
    System.out.println("Type motorvogn:      Personbil");
    super.type();
    System.out.println("Seter: " + seter);
    System.out.println("\n");
  }
  /*Returner true hvis det er snakk om en Fossilbiler eller Personbil. Hvis det er genrerelt
  bil sa vil den benytte seg av super sin returType. Hvis ingen av delene sa vil
  koden returnere fals.*/
  @Override
  public boolean returnType(String tekst){
    return (tekst.equals("Fossilbiler") || tekst.equals("Personbiler"));
  }
}
