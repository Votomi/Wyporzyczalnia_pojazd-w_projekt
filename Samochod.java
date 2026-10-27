
// Klasa reprezentujaca samochod w wypozyczalni.
// Dziedziczy wspolne cechy z klasy Pojazd i dodaje informacje o liczbie drzwi.
public class Samochod extends Pojazd {

    // Liczba drzwi w samochodzie.
    private int drzwi;

    // Konstruktor tworzy obiekt samochodu z podstawowymi danymi i parametrem drzwi.
    public Samochod(int id, String marka, String model, double cena, boolean dostepny, int drzwi) {
        super(id, marka, model, cena, dostepny);
        this.drzwi = drzwi;
    }

    // Zwraca typ pojazdu potrzebny do wyswietlania i rozpoznawania klasy.
    @Override
    public String getTyp() {
        return "Samochod";
    }

    // Zapisuje dane samochodu do formatu tekstowego, aby moc je odczytac ponownie.
    @Override
    public String zapisz() {
        return "Samochod|" + id + "|" + marka + "|"
                + model + "|" + cena + "|" + dostepny
                + "|" + drzwi;
    }

    // Wyswietla informacje o samochodzie wraz z dodatkowymi danymi dotyczacymi drzwi.
    // Na koncu drukowany jest separator, aby odroczyc kolejne elementy wyswietlacza.
    @Override
    public void wyswietl() {
        wyswietlDanePodstawowe();
        System.out.println("Drzwi: " + drzwi);
        System.out.println("--------------------");
    }
}
