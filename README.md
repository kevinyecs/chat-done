
# Chat app doc
Alkalmazás fejlesztés I. Kötelező program

* Az alkalmazás Maven Build rendszer és Javafx valamint JSP-vel lett megvalósítva.
* Az alkalmazás minden követelményt megvalósított.

# Leírás/Features

* Egy chat alkalmazás létre hozása volt a feladat, a program asztali felülete kilistázza az adatbázisban lévő usereket valamint chat szobákat
és ezeket lehet törölni onnan.

* Webes környezetben meglett valósítva a regisztráció és belépés, ha ezek megvannak a belépés után egy olyan oldalra navigál minket ahol,
kilistázza az összes user és chat szobát, ezeknek van egy keresőjük.

* Ezen belül beléphetünk egy chat szobába ahol meg van jelenítve az adott chat szoba tartalma, ahol képeket valamint szöveget küldhetünk.
Amit elment az adatbázisba és frissítéskor automatikusan látthatjuk.

# Megvalósítás / Extra beállítások
  * application.properties fájla szükséges megadni az adatbázis elérésí utját
  * Input a chatnél egy 3rd party javascript libraryval lett megvalósítva Quill-el, 
  valamint emojit úgy küldhetünk hogy jobb klikkelünk az inputra és hangulatjel kiválaszatása ez nem saját feature de müködik így is meg elmenti rendesen
  * Tartalom formázásához Boostrap3-at használtam
  (Fentiek mind CDN-el vannak benne a kódban elméletben nem kéne semmit beállítani a müködésükhöz kivéve a databaset)
