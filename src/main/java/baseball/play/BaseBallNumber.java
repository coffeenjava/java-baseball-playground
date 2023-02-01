package baseball.play;

import baseball.util.Randoms;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 숫자
 */
public class BaseBallNumber {

    private final List<Integer> numbers;

    public BaseBallNumber(List<Integer> numbers) {
        if (numbers == null || numbers.size() != 3) {
            throw new IllegalArgumentException();
        }
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    /**
     * 랜덤 숫자 생성 (게임 숫자)
     */
    public static BaseBallNumber generateRandom() {
        List<Integer> numbers = new ArrayList<>();

        do {
            int num = Randoms.pickNumberInRange(1, 9);
            if (numbers.contains(num) == false) {
                numbers.add(num);
            }
        } while (numbers.size() < 3);

        return new BaseBallNumber(numbers);
    }

    /**
     * 숫자 비교 및 결과 생성
     * @param targetNumber 사용자 입력 숫자
     */
    public GameResult compare(BaseBallNumber targetNumber) {
        List<Integer> targetNumberList = targetNumber.getNumbers();
        GameResult result = new GameResult();

        for (int i = 0; i < numbers.size(); i++) {
            Integer targetNum = targetNumberList.get(i);

            if (numbers.contains(targetNum)) {
                if (numbers.get(i).equals(targetNum)) {
                    result.strike();
                } else {
                    result.ball();
                }
            }
        }

        return result;
    }
}
