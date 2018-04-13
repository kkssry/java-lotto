package calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void 쉼표_또는_콜론_구분자_기준으로_숫자_합() {
        /*
         * 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환
         * (예: “” => 0, "1,2" => 3, "1,2,3" => 6, “1,2:3” => 6)
         */
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.sum("")).isEqualTo(0);
        assertThat(stringCalculator.sum("1")).isEqualTo(1);
        assertThat(stringCalculator.sum("1,2")).isEqualTo(3);
        assertThat(stringCalculator.sum("1,2,3")).isEqualTo(6);
        assertThat(stringCalculator.sum("1,2:3")).isEqualTo(6);
    }

    @Test
    public void 커스텀_구분자() {
        /*
         * 앞의 기본 구분자(쉼표, 콜론)외에 커스텀 구분자를 지정할 수 있다.
         * 커스텀 구분자는 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
         * 예를 들어 “//;\n1;2;3”과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
         */
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.sum("//;\n1;2;3"))
                .isEqualTo(6);
    }

    @Test
    public void 숫자_이외의_값() {
        /*
         * 문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw한다.
         */
        StringCalculator stringCalculator = new StringCalculator();

        thrown.expect(RuntimeException.class);

        stringCalculator.sum("숫자 이외의 값");
    }

    @Test
    public void 음수() {
        /*
         * 문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw한다.
         */
        StringCalculator stringCalculator = new StringCalculator();

        thrown.expect(RuntimeException.class);

        stringCalculator.sum("-1,2,3");
    }
}