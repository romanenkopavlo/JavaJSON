import org.example.utils.LectureFichierTexte;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("mes tests")
class LectureFichierTexteTest {
    LectureFichierTexte fichierUtil;

    @TempDir
    Path tempDir; // Répertoire temporaire pour les tests

    @BeforeEach
    void init() {
        // Initialise une nouvelle instance de LectureFichierTexte avant chaque test
        fichierUtil = new LectureFichierTexte();
    }

    @AfterEach
    void ras() {
        // Code de nettoyage après chaque test
        fichierUtil = null;
        tempDir = null;
    }

    @Test
    @DisplayName("test contenu non vide")
    void testLireContenu() throws Exception {
        Path fichierTemporaire = tempDir.resolve("test.txt");
        String contenuAttendu = "Test de lecture de fichiers texte.";
        Files.writeString(fichierTemporaire, contenuAttendu);
        fichierUtil = new LectureFichierTexte();
        String contenuLu = fichierUtil.lire(fichierTemporaire.toString());
        assertEquals(contenuAttendu, contenuLu, "Le contenu du fichier lu est incorrect.");

    }

    @Test
    @DisplayName("test contenu vide")
    void testLireContenuNull()  {
        Path fichierTemporaire = tempDir.resolve("test.txt");
        fichierUtil = new LectureFichierTexte();
        String contenuLu = fichierUtil.lire(fichierTemporaire.toString());
        assertNull(contenuLu, "La méthode devrait retourner null pour un fichier inexistant.");
    }


}