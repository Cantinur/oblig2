class Lastebiler extends Fossilbiler{
  public Lastebiler(String regNummer, double utslipp, double nyttevekt){
    super(regNummer, utslipp);
    this.nyttevekt = nyttevekt;
  }

  private double nyttevekt;

  public double returnnyttevekt(){
    return nyttevekt;
  }
  //Denne typen overrider bade bil og Fossilbiler sin metode
  @Override
  public void type(){
    System.out.println("Type motorvogn:      Lastebil");
    /*Bruker denne biten kode for a hente ut informasjon fra de to faregaende
    metodene med samme navn. Dette skaper mindre repitisjon. Samtidig som jeg far
    teste ut a bruke litt forskjellig type kode. */
    super.type();
    System.out.println("Nyttevekt: " + nyttevekt);
    System.out.println("\n");
  }
  /*Returner true hvis det er snakk om en Fossilbiler eller Lastebil. Hvis det er genrerelt
  bil sa vil den benytte seg av super sin returType. Hvis ingen av delene sa vil
  koden returnere fals.*/
  @Override
  public boolean returnType(String tekst){
    return (tekst.equals("Fossilbiler") || tekst.equals("Lastebiler"));
  }
}
