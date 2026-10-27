
// Klasa reprezentujaca motocykl w wypozyczalni.
// Dodaje informacje o pojemnosci silnika, ktore sa charakterystyczne dla tego modelu.
public class Motocykl extends Pojazd {

    // Pojemnosc silnika motocykla.
    private int pojemnosc;

    // Konstruktor inicjalizuje motocykl danymi podstawowymi i pojemnoscia silnika.
    public Motocykl(int id, String marka, String model,
                    double cena, boolean dostepny, int pojemnosc) {
        super(id, marka, model, cena, dostepny);
        this.pojemnosc = pojemnosc;
    }

    // Zwraca typ motocykla.
    @Override
    public String getTyp() {
        return "Motocykl";
    }

    // Zapisuje motocykl do formatu tekstowego, aby mozna go bylo odczytac ponownie.
    @Override
    public String zapisz() {
        return "Motocykl|" + id + "|" + marka + "|"
                + model + "|" + cena + "|" + dostepny
                + "|" + pojemnosc;
    }

    // Wyswietla informacje o motocyklu wraz z pojemnoscia silnika.
    // Separator na koncu robi odstep pomiedzy kolejnymi wpisami na ekranie.
    @Override
    public void wyswietl() {
        wyswietlDanePodstawowe();
        System.out.println("Pojemnosc silnika: " + pojemnosc);
        System.out.println("--------------------");
    }
}
