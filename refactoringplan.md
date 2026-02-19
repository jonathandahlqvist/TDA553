**Refaktoriseringsplan**

1. Klassen TimerListener flyttas till inuti CarView
2. I klassen Controller läggs det till en helper funktion: actionPerformed() som innehåller all logik
3. DrawPanel görs oberoende av Controller genom att ta bort nästlade loopen i paintComponent
4. I klassen CarTransport läggs det till en helper funktion checkNear() som hjälper addCar() kolla om bilen är tillräckligt nära
5. Göra koden i repairshop mer konsekvent med hänsyn till helpers, exceptions, ansvar, etc.
6. Ändra synlighet på fields, främst från protected till package private.

(Steg 3, 4, 5, & 6 kan göras separat, oberoende av andra steg)
(Steg 1-2 är inte oberoende av varandra och bör göras av samma programmerare.)
