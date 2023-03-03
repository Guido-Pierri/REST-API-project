package com.example.produktapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
* Krav
För godkänt betyg (G)
1. Alla metoder i service-klassen samt våra egenskrivna metoder i repositoryt testas med lämpliga
enhetstester (observera att det ibland kan vara lämpligt med fler enhetstester av samma metod)
2. Mockning tillämpas där så är lämpligt, dvs. Åtminstone då serviceklassen enhetstestas ska
repositoryt vara mockat.
3. Konfiguration mot Github Actions som automatiskt bygger och kör alla tester för varje gång
koden pushas.
4. Testning med Selenium (valfri frontend-webbapp som läser ut data från projekt-API:et):
a. Kontrollera att webbplatsens titel stämmer
b. Kolla att det totala antalet produkter stämmer
c. Kontrollera att priset blir rätt på minst 3 produkter
För väl godkänt betyg (VG)
Alla krav för godkänt, samt:
1. Koden ska vara lättläst och följa SOLID-principerna där så är tillämpligt samt följa best practice
för enhetstester och clean code.
2. Du kan klart och tydligt förklara syftet med testerna, vad de testar och hur de fungerar
tillsammans med API:et.
3. Testning med Selenium (valfri frontend-webbapp som läser ut data från projekt-API:et):
a. Skriv ut alla kategorier och kolla att de har rätt namn
b. Kontrollera priset på ytterligare 3 produkter
c. Kontrollera att bilderna skrivs ut/visas till minst 3 produkter
d. Kontrollera att bilden har rätt src-attribut på minst 3 produkter
e. Kontrollera att alla produkter som skrivs ut har rätt namn
f. Gör produktkategorierna klickbara och testa att rätt antal produkter visas vid klick på
respektive kategori
*/
@SpringBootApplication
public class ProduktapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProduktapiApplication.class, args);
	}

}
