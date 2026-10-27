
// Abstrakcyjna klasa bazowa dla wszystkich typow pojazdow.
// Zawiera wspolne pola i metody, ktore dziedzicza samochod, rower i motocykl.
public abstract class Pojazd {

    // Wspolne dane kazdego pojazdu.
    protected int id;
    protected String marka;
    protected String model;
    protected double cena;
    protected boolean dostepny;

    // Konstruktor ustawiajacy podstawowe informacje o pojezdzie.
    public Pojazd(int id, String marka, String model,
                  double cena, boolean dostepny) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.cena = cena;
        this.dostepny = dostepny;
    }

    // Zwraca identyfikator pojazdu.
    public int getId() {
        return id;
    }

    // Zwraca informacje, czy pojazd jest dostepny.
    public boolean isDostepny() {
        return dostepny;
    }

    // Ustawia status dostepnosci pojazdu.
    public void setDostepny(boolean dostepny) {
        this.dostepny = dostepny;
    }

    // Kazdy typ pojazdu musi zdefiniowac swoj typ.
    public abstract String getTyp();

    // Kazdy typ pojazdu musi umiec zapisac sie do pliku tekstowego.
    public abstract String zapisz();

    // Wyswietla wspolne dane pojazdu dla wszystkich podklas.
    // To jest metoda wspolna dla samochodu, roweru i motocykla.
    protected void wyswietlDanePodstawowe() {
        System.out.println("ID: " + id);
        System.out.println("Marka: " + marka);
        System.out.println("Model: " + model);
        System.out.println("Cena: " + cena);

        if (dostepny) {
            System.out.println("Dostepny");
        } else {
            System.out.println("Wypozyczony");
        }
    }

    public void wyswietl() {
        wyswietlDanePodstawowe();
    }
}
