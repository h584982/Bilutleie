package bilutleie;

public class Bil {

	private int registreringsnummer;
	private String merke;
	private String modell;
	private String farge;
	private char utleiegruppe;
	private boolean ledig;

	public int getRegistreringsnummer() {
		return registreringsnummer;
	}

	public void setRegistreringsnummer(int registreringsnummer) {
		this.registreringsnummer = registreringsnummer;
	}

	public String getMerke() {
		return merke;
	}

	public void setMerke(String merke) {
		this.merke = merke;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
	}

	public String getFarge() {
		return farge;
	}

	public void setFarge(String farge) {
		this.farge = farge;
	}

	public char getUtleiegruppe() {
		return utleiegruppe;
	}

	public void setUtleiegruppe(char utleiegruppe) {
		this.utleiegruppe = utleiegruppe;
	}

	public boolean isLedig() {
		return ledig;
	}

	public void setLedig(boolean ledig) {
		this.ledig = ledig;
	}

}
