
// Klasa reprezentujaca rower w wypozyczalni.
// Dodaje informacje o liczbie przerzutek, ktore sa unikalne dla tego typu pojazdu.
public class Rower extends Pojazd {

    // Liczba biegow lub przerzutek w rowerze.
    private int liczbaPrzerzutek;

    // Konstruktor inicjalizuje podstawowe dane roweru oraz liczbe przerzutek.
    public Rower(int id, String marka, String model,
                 double cenaZaDobe, boolean dostepny,
                 int liczbaPrzerzutek) {

        super(id, marka, model, cenaZaDobe, dostepny);
        this.liczbaPrzerzutek = liczbaPrzerzutek;
    }

    // Zwraca liczbe przerzutek roweru.
    public int getLiczbaPrzerzutek() {
        return liczbaPrzerzutek;
    }

    // Zwraca typ roweru.
    @Override
    public String getTyp() {
        return "Rower";
    }

    // Zapisuje dane roweru do pliku tekstowego.
    @Override
    public String zapisz() {
        return "Rower;" + id + ";" + marka + ";"
                + model + ";" + cena + ";"
                + dostepny + ";" + liczbaPrzerzutek;
    }

    // Wyswietla informacje o rowerze wraz z liczba przerzutek.
    // Separator na koncu pomaga czytelnie oddzielic jeden rower od nastepnego.
    @Override
    public void wyswietl() {
        wyswietlDanePodstawowe();
        System.out.println(
            "Liczba przerzutek: " + liczbaPrzerzutek
        );
        System.out.println("--------------------");
    }
}
