import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

char leseZeichen() {
    System.out.print("Bitte geben Sie ein Zeichen an: ");

    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        return (char) reader.read();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

String leseWort() {
    System.out.print("Bitte geben Sie mehrere Zeichen an: ");

    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        return reader.readLine();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

void ausgabe() {
    System.out.println(leseZeichen());
    System.out.println(leseWort());
}

void main() {
   ausgabe();
}
