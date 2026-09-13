package presentation.views;

import java.util.Scanner;
import domain.time.Time;
import lib.UI.Terminal;

public class ConsoleSimulationView implements SimulationView {

    private static final String TEMPLATE = """
            %s%s===================================================================================%s
            %s       LUDOTECA: JUEGO DEL TELÉFONO DESCACHARRADO       [ %s%s%s%s ]%s
            %s===================================================================================%s

              %s[ RECEPCIÓN ] Lydia%s
              Sala de espera: %s%d niño(s) esperando%s
              %s

              %s[ SALÓN DE JUEGO ] Aisha  |  Estado: %s  |  Partidas: %d%s
              Fila de juego: %s%d jugador(es)%s
              %s

              ┌── Pizarrín actual ────────────────┐      ┌── Pizarra del salón (Final) ──────┐
              │  %s│      │  %s│
              └───────────────────────────────────┘      └───────────────────────────────────┘
              Palabra inicial de Aisha: %s%s%s

            %s-----------------------------------------------------------------------------------%s
              Último suceso: %s%s%s
            %s===================================================================================%s
            """;

    private Time scheduleStartTime = new Time(0, 0);
    private String currentTimeStr = "--:--";
    private int waitingVisitorsCount = 0;
    private int playersInLineCount = 0;
    private String gameStatus = "ESPERANDO JUGADORES";
    private String starterWord = "----------";
    private String currentMessage = "----------";
    private String bigChalkboardContent = "[ Vacía ]";
    private String lastEventLog = "Simulación iniciada.";
    private int completedGames = 0;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void startSchedule(Time startTime) {
        this.scheduleStartTime = startTime != null ? startTime : new Time(0, 0);
        this.currentTimeStr = this.scheduleStartTime.toString();
        this.lastEventLog = "Apertura de la ludoteca. Inicio de jornada.";
        render();
    }

    @Override
    public void updateTime(Time elapsedTime) {
        if (elapsedTime != null) {
            Time absoluteTime = scheduleStartTime.add(elapsedTime);
            this.currentTimeStr = absoluteTime.toString();
        }
        render();
        waitForEnter();
    }

    @Override
    public void visitorsArrive(Integer numberOfArrivals) {
        int arrived = (numberOfArrivals != null) ? numberOfArrivals : 0;
        this.waitingVisitorsCount += arrived;
        this.lastEventLog = (arrived > 0)
                ? (arrived == 1 ? "Ha entrado 1 niñ@ por la puerta." : "Han entrado " + arrived + " niñ@s por la puerta.")
                : "No ha llegado ningún niño este minuto.";
        render();
    }

    @Override
    public void visitorInscribeAsPlayer(String visitorName) {
        if (waitingVisitorsCount > 0) {
            waitingVisitorsCount--;
        }
        playersInLineCount++;
        this.lastEventLog = "Lydia transfiere a " + visitorName + " a la fila de Aisha.";
        render();
    }

    @Override
    public void playersReturned(Integer count) {
        int returned = (count != null) ? count : 0;
        this.waitingVisitorsCount += returned;
        this.lastEventLog = returned + " niño(s) terminan la partida y regresan a la sala de espera con Lydia.";
        render();
    }

    @Override
    public void startGame(String starterWord, Integer numberOfPlayers, String currentText, String bigChalkboardText) {
        this.gameStatus = "EN CURSO";
        this.starterWord = starterWord;
        this.currentMessage = currentText != null ? currentText : starterWord;
        this.bigChalkboardContent = (bigChalkboardText != null && !bigChalkboardText.isEmpty()) ? bigChalkboardText : "[ Limpia ]";
        this.playersInLineCount = (numberOfPlayers != null) ? numberOfPlayers : playersInLineCount;
        this.lastEventLog = "Aisha inicia el juego con la palabra secreta: \"" + starterWord + "\"";
        render();
    }

    @Override
    public void playerTurn(String playerName, String currentText) {
        this.currentMessage = currentText;
        this.lastEventLog = playerName + " lee, deforma y escribe: \"" + currentText + "\"";
        render();
    }

    @Override
    public void finishGame(String playerName, String bigChalkboardText) {
        this.completedGames++;
        this.gameStatus = "TERMINADA";
        this.bigChalkboardContent = bigChalkboardText;
        this.playersInLineCount = 0;
        this.lastEventLog = playerName + " corre a la pizarra grande y escribe: \"" + bigChalkboardText + "\"";
        render();
    }

    private void render() {
        String centeredCurrent = Terminal.center(Terminal.GREEN + currentMessage + Terminal.RESET, 33);
        String centeredBig = Terminal.center(Terminal.YELLOW + bigChalkboardContent + Terminal.RESET, 33);

        String output = String.format(TEMPLATE,
                Terminal.CLEAR,
                Terminal.CYAN, Terminal.RESET,
                Terminal.BOLD, Terminal.YELLOW, currentTimeStr, Terminal.RESET, Terminal.BOLD, Terminal.RESET,
                Terminal.CYAN, Terminal.RESET,
                Terminal.BOLD, Terminal.RESET,
                Terminal.BLUE, waitingVisitorsCount, Terminal.RESET,
                renderQueueAscii(waitingVisitorsCount),
                Terminal.BOLD, renderStatus(gameStatus), completedGames, Terminal.RESET,
                Terminal.MAGENTA, playersInLineCount, Terminal.RESET,
                renderLineAscii(playersInLineCount),
                centeredCurrent, centeredBig,
                Terminal.BOLD, starterWord, Terminal.RESET,
                Terminal.CYAN, Terminal.RESET,
                Terminal.BOLD, lastEventLog, Terminal.RESET,
                Terminal.CYAN, Terminal.RESET
        );

        System.out.print(output);
        System.out.flush();

        Terminal.pause(500);
    }

    private void waitForEnter() {
        System.out.print(Terminal.YELLOW + "  [Pulsa ENTER para avanzar al siguiente minuto...] " + Terminal.RESET);
        System.out.flush();
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    private String renderQueueAscii(int count) {
        if (count == 0) return "[ Sala vacía ]";
        StringBuilder sb = new StringBuilder();
        int max = Math.min(count, 12);
        for (int i = 0; i < max; i++) {
            sb.append("(o_o) ");
        }
        if (count > max) {
            sb.append("...+").append(count - max);
        }
        return sb.toString();
    }

    private String renderLineAscii(int count) {
        if (count == 0) return "[ Fila vacía ]";
        StringBuilder sb = new StringBuilder();
        int max = Math.min(count, 10);
        for (int i = 0; i < max; i++) {
            sb.append("[o/]-");
        }
        if (count > max) {
            sb.append("...+").append(count - max);
        } else if (count > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private String renderStatus(String status) {
        return switch (status) {
            case "EN CURSO" -> Terminal.GREEN + "● EN JUEGO" + Terminal.RESET;
            case "TERMINADA" -> Terminal.MAGENTA + "★ TERMINADA" + Terminal.RESET;
            default -> Terminal.YELLOW + "○ ESPERANDO" + Terminal.RESET;
        };
    }
}
