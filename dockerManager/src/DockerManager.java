import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class DockerManager {

    // TNPW BTW (2)

    public static void main(String[] args) {
        // BootUpDatabase();
        // TurnOffDatabase();
        // ResetDatabaseData();
        ResetAndBoot();
    }

    private static void BootUpDatabase() {
        executeDockerCommand("docker", "compose", "up", "-d");
    }

    private static void TurnOffDatabase() {
        executeDockerCommand("docker", "compose", "down");
    }

    private static void ResetDatabaseData() {
        TurnOffDatabase();
        executeDockerCommand("docker", "volume", "rm", "docktnpw_pgdata");
    }

    private static void ResetAndBoot() {
        TurnOffDatabase();
        ResetDatabaseData();
        BootUpDatabase();
    }

    private static void executeDockerCommand(String... command) {
        try {
            ProcessBuilder builder = new ProcessBuilder(command);

            builder.directory(new File(".\\"));
            builder.redirectErrorStream(true);
            Process process = builder.start();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println(command[1] + " executed successfully.");
            } else {
                System.err.println("Error executing " + command[1]);
            }
        } catch (Exception e) {
            System.err.println("Docker command error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
