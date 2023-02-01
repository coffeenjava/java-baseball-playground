package baseball.play;

import baseball.util.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    /**
     * 게임에서 생성한 숫자
     */
    private BaseBallNumber gameNumber;

    /**
     * 사용자가 입력한 숫자
     */
    private BaseBallNumber userNumber;

    /**
     * 결과
     */
    private GameResult result;

    private Game() {
        // 게임 숫자 생성
        gameNumber = BaseBallNumber.generateRandom();
    }

    /**
     * 게임 시작
     */
    public static void start() {
        Game game = new Game();
        game.play();
    }

    /**
     * Game 클래스는 게임의 라이프 사이클을 관리한다.
     * 시작 / 종료 / 재시작
     */
    private void play() {
        // 사용자 입력 숫자 검증 및 추출
        List<Integer> inputNumberList = extractInputNumber();

        // 사용자 입력 숫자 저장
        userNumber = new BaseBallNumber(inputNumberList);

        // 숫자 비교
        result = gameNumber.compare(userNumber);

        // 결과 출력
        result.print();

        if (result.isFinished()) {
            // 게임 종료 후 처리
            restartOrStop();
        } else {
            // 게임 계속 진행
            play();
        }
    }

    /**
     * 게임 종료 후 처리
     */
    private void restartOrStop() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

        String startOrStop = Console.readLine();

        if ("1".equals(startOrStop)) {
            Thread t = new Thread(Game::start);
            t.start();
        }
    }

    /**
     * 사용자 입력 숫자 검증 및 추출
     * 중복되지 않은 1~9 사이의 숫자 3개여야 한다.
     */
    private List<Integer> extractInputNumber() {
        System.out.print("숫자를 입력해 주세요 : ");

        String input = Console.readLine();
        List<Integer> list;

        try {
            list = Arrays.stream(input.split(""))
                    .map(Integer::parseInt)
                    .filter(i -> i.equals(0) == false)
                    .distinct()
                    .collect(Collectors.toList());

            if (list.size() != 3) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.out.println("중복되지 않은 1~9 사이의 숫자 3개를 연속적으로 입력해주세요. usage: 371");
            list = extractInputNumber();
        }

        return list;
    }
}
