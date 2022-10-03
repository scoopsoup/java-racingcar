package racingcarwinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RacingCarWinner {

    private String participantName;
    private int carMovingCount;
    private String[] participantNames;
    private int participantNumber;
    private Map<String, Integer> carNowInformation;
    private String printingStatus = "";
    private Car car = new Car();

    public RacingCarWinner(String participantName, int carMovingCount) {
        this.participantName = participantName;
        this.carMovingCount = carMovingCount;
    }

    public void splitParticipantName() {
        participantNames = participantName.split(",");
        participantNumber = participantNames.length;
    }

    public void enterParticipantInformation() {
        Map<String, Integer> participantInformation = new HashMap<>();
        splitParticipantName();
        for (String name : participantNames) {
            participantInformation.put(name, 0);
        }
        car.setParticipant(participantInformation);
    }

    public int judgmentGoStop() {
        int randomNum = (int) (Math.random() * 10);
        if (randomNum >= 4) {
            return 1;
        }
        return 0;
    }

    public List<Integer> racingCarMove() {
        List<Integer> carMovement = new ArrayList<>();
        for (int i = 0; i < participantNumber; i++) {
            carMovement.add(i, judgmentGoStop());
        }
        return carMovement;
    }

    public void updateParticipantInformation() {
        List<Integer> carMovement = racingCarMove();
        carNowInformation = car.getParticipant();
        String participantNameKey = "";
        int eachNumberOfMove = 0;
        for (int i = 0; i < participantNumber; i++) {
            participantNameKey = participantNames[i];
            eachNumberOfMove = carNowInformation.get(participantNameKey) + carMovement.get(i);
            carNowInformation.replace(participantNameKey, eachNumberOfMove);
        }
        car.setParticipant(carNowInformation);
        printGameStatus();
    }

    public String printGameStatus() {
        for (String participant : participantNames) {
            printingStatus += participant + " : " + printCarBar(participant) + "\n";
        }
        printingStatus += "\n";
        return printingStatus;
    }

    public String printCarBar(String participant) {
        int moveDistance = carNowInformation.get(participant);
        String distanceBar = "";
        for (int i = 0; i < moveDistance; i++) {
            distanceBar += "-";
        }
        return distanceBar;
    }

    public String winner() {
        String max = participantNames[0];
        String winnerParticipant = participantNames[0];
        for (int i = 1; i < participantNumber; i++) {
            if (carNowInformation.get(max) == carNowInformation.get(participantNames[i])) {
                winnerParticipant += "," + participantNames[i];
            }
            if (carNowInformation.get(max) < carNowInformation.get(participantNames[i])) {
                max = participantNames[i];
                winnerParticipant = participantNames[i];
            }
        }
        return winnerParticipant + "가 최종우승했습니다.";
    }

    public String racingCarGame() {
        for (int i = 0; i < carMovingCount; i++) {
            updateParticipantInformation();
        }
        printingStatus += winner();
        return printingStatus;
    }

    public String startGame() {
        enterParticipantInformation();
        racingCarMove();
        return racingCarGame();
    }

}
