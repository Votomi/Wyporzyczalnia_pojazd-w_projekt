
// Klasa reprezentujaca klienta wypozyczalni.
// Zawiera dane osobowe klienta oraz informacje o przypisanym pojezdzie.
public class Klient {

    // Unikalny identyfikator klienta.
    private int id;

    // Imie i nazwisko klienta.
    private String imie;
    private String nazwisko;

    // ID przypisanego pojazdu. 0 oznacza brak przypisanego pojazdu.
    private int pojazdId;

    // Konstruktor domyslny bez przypisanego pojazdu.
    public Klient(int id, String imie, String nazwisko) {
        this(id, imie, nazwisko, 0);
    }

    // Konstruktor z przypisaniem pojazdu.
    public Klient(int id, String imie, String nazwisko, int pojazdId) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pojazdId = pojazdId;
    }

    // Zwraca ID klienta.
    public int getId() {
        return id;
    }

    // Zwraca ID przypisanego pojazdu.
    public int getPojazdId() {
        return pojazdId;
    }

    // Ustawia ID przypisanego pojazdu.
    public void setPojazdId(int pojazdId) {
        this.pojazdId = pojazdId;
    }

    // Zapisuje dane klienta do tekstu, aby moc je zapisac do pliku.
    public String zapisz() {
        return id + "|" + imie + "|" + nazwisko + "|" + pojazdId;
    }

    // Wyswietla informacje o kliencie i przypisanym pojezdzie.
    // Dodatkowy pusty wiersz oddziela kolejne rekordy klientow na ekranie.
    public void wyswietl() {
        System.out.println("ID klienta: " + id);
        System.out.println("Imie: " + imie);
        System.out.println("Nazwisko: " + nazwisko);
        if (pojazdId > 0) {
            System.out.println("Przypisany pojazd: " + pojazdId);
        } else {
            System.out.println("Przypisany pojazd: brak");
        }
    }
}
