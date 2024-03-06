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

void leseWort() {
    String input = "";
    while (!input.equalsIgnoreCase("over")) {
        System.out.print("Bitte geben Sie mehrere Zeichen an: ");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            input = reader.readLine();
            System.out.println(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

void main() {
    System.out.println(leseZeichen());
    leseWort();
}
