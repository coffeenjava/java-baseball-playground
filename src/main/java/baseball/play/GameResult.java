package baseball.play;

/**
 * 게임 결과
 */
public class GameResult {

    /**
     * 스트라이크 개수
     */
    private int strike;

    /**
     * 볼 개수
     */
    private int ball;

    /**
     * 스트라이크 추가
     */
    public void strike() {
        ++strike;
    }

    /**
     * 볼 추가
     */
    public void ball() {
        ++ball;
    }

    /**
     * 게임 종료 여부
     */
    public boolean isFinished() {
        return strike == 3;
    }

    /**
     * 결과 출력
     */
    public void print() {
        if (strike == 0 && ball == 0) {
            System.out.println("낫싱");
        }

        StringBuilder sb = new StringBuilder();

        if (ball > 0) {
            sb.append(ball).append("볼 ");
        }

        if (strike > 0) {
            sb.append(strike).append("스트라이크");
        }

        System.out.println(sb);
    }
}
