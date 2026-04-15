Ecco una versione aggiornata e ottimizzata del tuo **README.md**. Ho integrato la tua sequenza pratica con i suggerimenti tecnici per evitare di perdere il file `.jar` e per spiegare i "perché" dietro il funzionamento dell'app.

---

# 📚 Progetto Jakarta EE 10 - Libreria API

Questo progetto è un'applicazione didattica basata su **Jakarta EE 10** per la gestione di una libreria. Dimostra l'integrazione tra **JAX-RS** (API REST), **EJB** (Logica di business) e **JPA** (Persistenza dei dati).

## 🛠️ Requisiti e Ambiente
* **Java JDK:** Versione **21** (LTS) - *Essenziale per la compatibilità con Payara Micro 7+*.
* **Build Tool:** Maven (integrato in IntelliJ IDEA).
* **Application Server:** **Payara Micro** (il file `payara-micro.jar` è già incluso nella root del progetto).

---

## 🚀 Sequenza di Compilazione e Avvio
Segui questi passaggi nell'ordine esatto per compilare e far girare l'applicazione senza perdere i file necessari.

### 1. Build del progetto (Maven)
* Apri la scheda **Maven** a destra in IntelliJ.
* Espandi la voce `Lifecycle`.
* Fai doppio click su **`clean`** (cancella la vecchia cartella `target`).
* Fai doppio click su **`package`** (genera il nuovo file `target/mia-app.war`).

### 2. Lancio del Server
Non spostare il file `.jar` dentro `target` (verrebbe cancellato al prossimo `clean`). Apri il terminale nella **cartella principale** del progetto e lancia:

```bash
java -jar payara-micro.jar --deploy target/mia-app.war --nocluster
```
Attendi finché non vedi il messaggio: `Payara Micro Ready`.

---

## 📡 Test delle API (Postman / Curl)

| Metodo | URL | Descrizione |
| :--- | :--- | :--- |
| **POST** | `http://localhost:8080/mia-app/api/libri/{nome}` | Aggiunge un libro (es. `/api/libri/Il-Signore-degli-Anelli`). |
| **GET** | `http://localhost:8080/mia-app/api/libri` | Ritorna la lista di tutti i libri in formato JSON. |

---

## 💡 Note Tecniche (Lessons Learned)

### Il mistero dei Getter e il Decoupling
Abbiamo scoperto che il formato del JSON in uscita non dipende dal nome della variabile privata nella classe Java, ma dal nome del metodo **Getter**.
* Se la variabile è `private String name` ma il metodo è `getTitoloski()`, il JSON mostrerà `"titoloski": "valore"`.
* Questo permette di cambiare il codice interno senza rompere l'interfaccia usata dai client esterni (**Decoupling**).

### Compatibilità Database H2 (v2.x)
Le versioni recenti di H2 sono molto rigide sulla sintassi SQL. Per far funzionare correttamente la creazione automatica delle tabelle:
1.  **Nel `persistence.xml`:** Abbiamo forzato la piattaforma con `<property name="eclipselink.target-database" value="org.eclipse.persistence.platform.database.H2Platform"/>`.
2.  **Nell'Entity:** Usiamo `@GeneratedValue(strategy = GenerationType.AUTO)` per evitare errori sulla parola chiave `IDENTITY`.

### Persistence Unit
Il nome specificato nel `persistence.xml` (es. `LibreriaUnit`) deve coincidere esattamente con quello richiamato nel Service tramite l'annotazione `@PersistenceContext(unitName = "LibreriaUnit")`.

---

## 📁 Struttura Principale
* `src/main/java`: Classi Java (Entity, Service, Resource).
* `src/main/resources/META-INF/persistence.xml`: Configurazione JPA.
* `src/main/webapp/WEB-INF/beans.xml`: Attivazione CDI (Dependency Injection).
* `payara-micro.jar`: Server portatile (non spostare).

---
*Note: I dati salvati sono memorizzati in un database H2 in memoria (o file locale). Se il server viene spento, i dati andranno persi se non diversamente configurato.*
