
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

// Glowna klasa programu, ktora steruje calym menu aplikacji.
// Tutaj tworzone sa obiekty pojazdow i klientow, zapisane sa dane do plikow oraz
// wykonywane sa operacje dotyczace wypozyczania i wyswietlania informacji.
public class Main {

    private static class ZapisException extends RuntimeException {
        public ZapisException(String komunikat, Exception przyczyna) {
            super(komunikat, przyczyna);
        }
    }

    // Lista wszystkich pojazdow dostepnych w wypozyczalni.
    static ArrayList<Pojazd> pojazdy = new ArrayList<Pojazd>();

    // Lista wszystkich klientow zapisanych w programie.
    static ArrayList<Klient> klienci = new ArrayList<Klient>();

    // Obiekt do odczytu danych wprowadzanych z klawiatury przez uzytkownika.
    static Scanner scanner = new Scanner(System.in);

    // Liczniki ID dla kolejnych pojazdow i klientow.
    static int idPojazdu = 1;
    static int idKlienta = 1;

    // Punkt startowy programu.
    // Najpierw wczytuje dane z plikow, a nastepnie wyswietla menu w petli.
    public static void main(String[] args) {
        wczytajDane();

        int wybor;

        do {
            System.out.println("\nWYPOZYCZALNIA");
            System.out.println("1. Dodaj samochod");
            System.out.println("2. Dodaj rower");
            System.out.println("3. Dodaj motocykl");
            System.out.println("4. Dodaj klienta");
            System.out.println("5. Wyswietl pojazdy");
            System.out.println("6. Wyswietl klientow");
            System.out.println("7. Przypisz pojazd do klienta");
            System.out.println("8. Zapisz dane");
            System.out.println("0. Koniec");

            wybor = scanner.nextInt();
            scanner.nextLine();

            switch (wybor) {
                case 1:
                    dodajSamochod();
                    break;

                case 2:
                    dodajRower();
                    break;

                case 3:
                    dodajMotocykl();
                    break;

                case 4:
                    dodajKlienta();
                    break;

                case 5:
                    wyswietlPojazdy();
                    break;

                case 6:
                    wyswietlKlientow();
                    break;

                case 7:
                    przypiszPojazdDoKlienta();
                    break;

                case 8:
                    zapiszDane();
                    break;

                case 0:
                    zapiszDane();
                    System.out.println("Koniec programu");
                    break;

                default:
                    System.out.println("Bledna opcja");
            }

        } while (wybor != 0);
    }

    // Wczytuje dane z plikow txt, aby po ponownym uruchomieniu programu
    // nie zniknely dodane pojazdy i klienci.
    static void wczytajDane() {
        wczytajPojazdy();
        wczytajKlientow();
    }

    // Odczytuje pozycje z pliku z pojazdami i tworzy odpowiednie obiekty.
    static void wczytajPojazdy() {
        File plik = new File("pojazdy.txt");
        if (!plik.exists()) {
            return;
        }

        try (Scanner fileScanner = new Scanner(plik)) {
            while (fileScanner.hasNextLine()) {
                String linia = fileScanner.nextLine().trim();
                if (linia.isEmpty()) {
                    continue;
                }

                String[] dane = linia.split("[;|]");
                if (dane.length < 6) {
                    continue;
                }

                String typ = dane[0];
                int id = Integer.parseInt(dane[1]);
                String marka = dane[2];
                String model = dane[3];
                double cena = Double.parseDouble(dane[4]);
                boolean dostepny = Boolean.parseBoolean(dane[5]);

                switch (typ) {
                    case "Samochod":
                        if (dane.length >= 7) {
                            int drzwi = Integer.parseInt(dane[6]);
                            pojazdy.add(new Samochod(id, marka, model, cena, dostepny, drzwi));
                        }
                        break;
                    case "Rower":
                        if (dane.length >= 7) {
                            int przerzutki = Integer.parseInt(dane[6]);
                            pojazdy.add(new Rower(id, marka, model, cena, dostepny, przerzutki));
                        }
                        break;
                    case "Motocykl":
                        if (dane.length >= 7) {
                            int pojemnosc = Integer.parseInt(dane[6]);
                            pojazdy.add(new Motocykl(id, marka, model, cena, dostepny, pojemnosc));
                        }
                        break;
                    default:
                        break;
                }

                if (id >= idPojazdu) {
                    idPojazdu = id + 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Blad odczytu pojazdow");
        }
    }

    // Odczytuje klientow z pliku i tworzy ich obiekty.
    static void wczytajKlientow() {
        File plik = new File("klienci.txt");
        if (!plik.exists()) {
            return;
        }

        try (Scanner fileScanner = new Scanner(plik)) {
            while (fileScanner.hasNextLine()) {
                String linia = fileScanner.nextLine().trim();
                if (linia.isEmpty()) {
                    continue;
                }

                String[] dane = linia.split("\\|");
                if (dane.length < 3) {
                    continue;
                }

                int id = Integer.parseInt(dane[0]);
                String imie = dane[1];
                String nazwisko = dane[2];
                int pojazdId = dane.length > 3 ? Integer.parseInt(dane[3]) : 0;

                klienci.add(new Klient(id, imie, nazwisko, pojazdId));

                if (id >= idKlienta) {
                    idKlienta = id + 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Blad odczytu klientow");
        }
    }

    // Dodaje nowy samochod do listy i zapisuje go od razu do pliku.
    static void dodajSamochod() {
        System.out.print("Marka: ");
        String marka = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Cena za dobe: ");
        double cena = scanner.nextDouble();

        System.out.print("Liczba drzwi: ");
        int drzwi = scanner.nextInt();
        scanner.nextLine();

        Samochod samochod = new Samochod(
                idPojazdu, marka, model, cena, true, drzwi
        );

        pojazdy.add(samochod);
        idPojazdu++;

        zapiszDane();
    }

    // Dodaje nowy rower do listy i zapisuje go do pliku.
    static void dodajRower() {
        System.out.print("Marka: ");
        String marka = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Cena za dobe: ");
        double cena = scanner.nextDouble();

        System.out.print("Liczba przerzutek: ");
        int przerzutki = scanner.nextInt();
        scanner.nextLine();

        Rower rower = new Rower(
                idPojazdu, marka, model, cena, true, przerzutki
        );

        pojazdy.add(rower);
        idPojazdu++;

        zapiszDane();
    }

    // Dodaje nowy motocykl do listy i zapisuje go do pliku.
    static void dodajMotocykl() {
        System.out.print("Marka: ");
        String marka = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Cena za dobe: ");
        double cena = scanner.nextDouble();

        System.out.print("Pojemnosc silnika: ");
        int pojemnosc = scanner.nextInt();
        scanner.nextLine();

        Motocykl motocykl = new Motocykl(
                idPojazdu, marka, model, cena, true, pojemnosc
        );

        pojazdy.add(motocykl);
        idPojazdu++;

        zapiszDane();
    }

    // Dodaje nowego klienta do listy i zapisuje go do pliku.
    static void dodajKlienta() {
        System.out.print("Imie: ");
        String imie = scanner.nextLine();

        System.out.print("Nazwisko: ");
        String nazwisko = scanner.nextLine();

        Klient klient = new Klient(
                idKlienta, imie, nazwisko
        );

        klienci.add(klient);
        idKlienta++;

        zapiszDane();
    }

    // Wyswietla wszystkie pojazdy wraz z ich typem i szczegolowymi danymi.
    // Kazdy element jest oddzielony pustym wierszem, aby dane byly czytelniejsze.
    static void wyswietlPojazdy() {
        if (pojazdy.isEmpty()) {
            System.out.println("Brak pojazdow do wyswietlenia.");
            return;
        }

        for (Pojazd pojazd : pojazdy) {
            System.out.println("Typ: " + pojazd.getTyp());
            pojazd.wyswietl();
            System.out.println();
        }
    }

    // Wyswietla wszystkich klientow wraz z ich danymi i przypisanym pojazdem.
    // Kazdy klient jest wyswietlany w osobnym bloku oddzielonym pustym wierszem.
    static void wyswietlKlientow() {
        if (klienci.isEmpty()) {
            System.out.println("Brak klientow do wyswietlenia.");
            return;
        }

        for (Klient klient : klienci) {
            klient.wyswietl();
            System.out.println();
        }
    }

    // Pozwala przypisac wybrany pojazd do konkretnego klienta.
    // Ustawia pojazd jako niedostepny i zapisuje zmiany w pliku.
    static void przypiszPojazdDoKlienta() {
        if (pojazdy.isEmpty()) {
            System.out.println("Brak pojazdow do przypisania.");
            return;
        }
        if (klienci.isEmpty()) {
            System.out.println("Brak klientow do przypisania.");
            return;
        }

        System.out.println("Dostepne pojazdy:");
        for (Pojazd pojazd : pojazdy) {
            System.out.println("ID: " + pojazd.getId() + " | Typ: " + pojazd.getTyp() + " | Marka: " + pojazd.marka + " | Model: " + pojazd.model);
        }

        System.out.print("Wybierz ID pojazdu: ");
        int wybranyPojazdId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Lista klientow:");
        for (Klient klient : klienci) {
            System.out.println("ID: " + klient.getId() + " | " + klient.zapisz());
        }

        System.out.print("Wybierz ID klienta: ");
        int wybranyKlientId = scanner.nextInt();
        scanner.nextLine();

        Pojazd wybranyPojazd = null;
        for (Pojazd pojazd : pojazdy) {
            if (pojazd.getId() == wybranyPojazdId) {
                wybranyPojazd = pojazd;
                break;
            }
        }

        Klient wybranyKlient = null;
        for (Klient klient : klienci) {
            if (klient.getId() == wybranyKlientId) {
                wybranyKlient = klient;
                break;
            }
        }

        if (wybranyPojazd == null || wybranyKlient == null) {
            System.out.println("Niepoprawny ID pojazdu lub klienta.");
            return;
        }

        wybranyPojazd.setDostepny(false);
        wybranyKlient.setPojazdId(wybranyPojazdId);
        System.out.println("Pojazd " + wybranyPojazd.getTyp() + " o ID " + wybranyPojazdId + " zostal przypisany do klienta " + wybranyKlient.getId());
        zapiszDane();
    }

    // Zapisuje wszystkie pojazdy i klientow do odpowiednich plikow tekstowych.
    static void zapiszDane() {
        try {
            FileWriter zapisPojazdow =
                    new FileWriter("pojazdy.txt");

            for (Pojazd pojazd : pojazdy) {
                zapisPojazdow.write(pojazd.zapisz() + "\n");
            }

            zapisPojazdow.close();

            FileWriter zapisKlientow =
                    new FileWriter("klienci.txt");

            for (Klient klient : klienci) {
                zapisKlientow.write(klient.zapisz() + "\n");
            }

            zapisKlientow.close();

            System.out.println("Zapisano dane");

        } catch (Exception e) {
            throw new ZapisException("Blad zapisu danych", e);
        }
    }
}
