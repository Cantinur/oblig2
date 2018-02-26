public abstract class Bil{
  //Klassen er abstract fordi det ikke gir mening a bare skape et bilobjek.
  public Bil(String regNummer){
    this.regNummer = regNummer;
  }
  /* Beskyttet variabel som kan nas av subklasser */
  protected String regNummer;
  //Beyttes en gang.
  public String returnerReg(){
    return regNummer;
  }

  /* Laget den enkleste del av utskriften her.
  Resten blir lagt til i de neste subklassenivaaene */
  public void type(){
    System.out.println("Reg.nr: " + returnerReg());
  }
  /* Lager det som en metode her for at alle andre subklasser skal ha tilgang
  til denne metoden.  */
  public boolean returnType(String tekst){
    return true;
  }

}
