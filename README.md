# Android-studentlitteratur-app
-Read me


Applikationen Studentlitteratur
Caserapport av: Albin Bäckström

### Inledning, bakgrund och analys
Applikationen Studentlitteratur är en applikation till smarttelefoner och surfplattor för snabb och enkel åtkomst till litteratur som används vid studier.  I demo-versionen som har utvecklats får användaren tillgång till ett antal exempel inom området informatik. Innehållet som presenteras är enbart generell information om böckerna och användaren får möjligheten att länkas till Studentlitteraturs hemsida där böckerna kan köpas i fysiskt form eller i vissa fall e-bok. I fullversionen är tanken att användaren ska få ett antal böcker gratis, detta för att visa applikationens fulla potential och detta leder troligtvis till att fler böcker köps och att applikationen når ut till en bredare massa. Elektroniska böcker köpta via applikationen bör även säljas till ett rabatterat pris eftersom det sparar in på de fysiska böckerna som behöver tryckas. Vilket i sin tur sparar företaget pengar genom minskad kostnad för tryckeri och frakt.

Den tilltänkta användargruppen till applikationen är först och främst studenter som läser på högskola eller universitet och använder litteratur från Studentlitteratur. Då applikationen i nuläget innehåller studentlitteratur från företaget Studentlitteratur passar applikationen även för lärare och andra personer i liknande ställningar vid planering och schemaläggning av kurser och program. 

Syftet med applikationen är att tillhanda hålla ett elektroniskt bibliotek med studentlitteratur för användarna som snabbt ska komma åt. Oavsett om denne är uppkopplad eller inte. Om boken köps kommer en elektronisk version att finnas tillgänglig för användaren via applikationen. Antingen online eller laddas den ner till enhetens minne för åtkomst när enheten är utan uppkoppling.  Mervärde skapas i och med att användaren inte behöver gå runt med ett antal tunga böcker på sig. Tid sparas på leveranstider mot att köpa en fysisk bok som dessutom även kan vara slut hos leverantören. Att söka direkt i boken via en sökfunktion i applikationen är överlägset mot att slå upp i register och leta genom ett antal sidor innan det som söks finns. 

#### Skallkrav
- Skapa användare – Applikationen skall vara personlig med egna böcker.
- Logga in - Med ovan skapade användare.
- Logga ut - Andra ska kunna använda applikationen på samma enhet.
- Läsa böcker – Grundtanken med applikationen är att det ska gå att läsa böcker  i den.
- Söka efter böcker – Användaren ska kunna söka efter ny litteratur.
- Söka i text i böcker – Det ska kunna gå att söka genom en bok efter vissa ord eller meningar.
- Köpa ny litteratur genom applikationen – Användaren ska kunna köra nya böcker genom applikationen.
- Radera böcker från applikationen – Om boken ej längre är av intresse ska den kunna ta bort ur systemet. Men användaren ska fortfarande ha möjlighet att få åtkomst till den igen via sitt konto.

#### Börkrav
- Lägga in egen litteratur – Det bör kunna gå att lägga in egen kurslitteratur eller liknande material i applikationen.
- Få mer information om boken – Information om författare, förlag, år, upplaga med mera bör vara åtkomligt för användaren.
- Spara favoritböcker för snabb åtkomst – Att spara sina favoritböcker eller den litteratur som är mest aktuell för tillfället bör vara snabbt åtkomstbar.
- Radera favoritböcker – Böcker som inte längre är aktuella som favoriter bör kunna raderas ur favoritlistan.

#### Kompletteringskrav
- Referenshantering – Få färdiga referenser till exempel Harvard-systemet.
- Möjlighet att kopiera delar ur boken – Till exempel för citat eller kopior inom tillåtna områden.
- Byta utseende/tema på applikationen – Ett standardutseende kan bli tröttsamt och kan i värsta fall leda till bortfall av användare.


### Andra system
För effektiv hantering av innehåll och bilder tillhörande böcker så bör en databas användas. De flesta mobila system har en inbyggd databashanterare som heter SQLite, den används av både Android och IOS. Det är en lättviktigt DBMS som fungerar på små enheter med lite utrymme och ingen data behövs lagras externt, utan allt lagras på enheten som används. Har utvecklaren lite erfarenhet från andra SQL databaser är det relativt enkelt att sätta sig in i syntaxen.

Applikationen bör även sammankopplas med de större elektroniska bokhandlarna som finns på marknaden. Först och främst Studentlitteratur då de tillhandahåller stora mängder med kursmaterial till svenska lärosäten. Andra företag som adlibiris och bokus bör även kopplas samman med applikationens sökfunktion. Även sidor som säljer begagnade böcker i fysisk form kan vara av intresse även om det är lågt prioriterat och inte går helt i linje med applikationen grundtanke som är att tillhanda hålla elektronisk kurslitteratur. 

### Motivering av design
Med flertalet användbarhetskrav i åtanke har designen utvecklats och ett antal funktioner har skapats och placerats på strategiska platser. Att en ny användare ska hitta det den söker på ett snabbt sätt har det lagts stor vikt på vid designen. Med stora tydliga menyer som beskriver vad som klickas på har det användbarhetskravet uppfyllts. En annan viktigt detalj som många användare anser är viktigt är att det ska gå att känna igen sig i en ny applikation eller system. En drawer/navigationsmeny, även kallad hamburgermeny har implementerats för dess skull. Den kommer användare åt när den är inne och läser en bok. I den finns tydliga menyer och ikoner som inte ger upphov till någon tvekan till vad som sker vid användningen av knapparna. Ikonerna som används genomgående i designen är rakt på sak och beskriver bildligt talat vad knappen gör, även om det saknas text. En stjärna lägger till den aktuella boken som favorit medan en papperskorg raderar en bok. Klickar man på ikonen som är ett hus kommer man till hemmenyn. En mapp med en stjärna på motsvarar alla favoriter samlade på ett och samma ställe och ett plustecken visar möjligheten att lägga till eget material. En jordglob som ikon i en applikation är numera likställt med internet, vilket även används här.

En annan sak som är väldigt vanligt i mobilapplikationer är en meny som kommer fram vid längre tryck på en knapp eller lista. Även i den här applikationen finns det, användaren får ett antal val vid användningen av den funktionen bland annat att lägga till den som favorit eller att besöka bokens websida. Om användaren är inne bland sina favoriter finns det mindre val att göra via menyn, men den är fyller fortfarande sin fulla funktion sett till kontexten den används i. Här kan användaren välja att ta bort en favorit i taget genom kontextmenyn eller radera alla via en ikon som ser ut som en papperskorg. För att tydliggöra för användaren vilken bok som är öppen har titeln på boken valts att visas högst upp på skärmen. 

### Implementation
Utvecklingen av applikationen gjordes till större del i Android Studio version 1.2 med Java Runtime Envoirment 1.8.0. Applikationen är utvecklad under SDK version 19 vilket motsvarar 4.4 även kallat Kit-Kat, men den ska kunna köras på högre versioner än då. Källkoden kan även kompileras om så att det bör klara tidigare versioner av Android om det är önskvärt och når ut till fler användare. 

Vid skapandet av databasen användes ett tredjepartsprogram vid namn DB Browser for SQLite. Användandet av programmet underlättade skapandet och implementeringen av databasen och gav en väldigt överskådlig bild över hela databasen och dess innehåll. Det var även väldigt enkelt att ändra data direkt i databasen som sedan visades i applikationen. För att säkerställa databasen och dess innehåll i telefonen användes en applikation som heter aSQLiteManager som laddades ner via Google Play. Då den telefonen som användes vid utvecklandet är ”rootad” fungerade användandet av aSQLiteManager väldigt bra och gav uppdaterad info i realtid. 


### Drift, underhåll och avveckling
För att driftsätta applikationen behövs det tillstånd från de tilltänkta förlagen som äger rättigheterna till böckerna. Det är även tänkt att de skall stå för den största delen av finansieringen för utvecklandet. Utan dem kan det bli problematiskt att inrikta sig på deras specifika kurslitteratur. Speciellt när tanken är att ge användaren ett antal böcker gratis i fullversionen av applikationen. Om inget förlag ger sitt medgivande kommer applikationen att förlora sitt fulla syfte och enbart fungera som ett e-handelsystem mot diverse bokhandlare. Det vill troligtvis även medföra att de elektroniska böckerna kommer ha svårt att implementeras i applikationen då många av E-böcker är skyddade av olika system som gör det svårt att använda dem hur som helst.


### Första skissen på appen
![alt text](https://i.imgur.com/IjC0vEu.png)

### Slutgiltiga wireframe med de flesta funktioner.
![alt text](https://i.imgur.com/sQ2DflK.png)

