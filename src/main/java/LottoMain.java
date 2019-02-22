import lotto.UserLotto;
import lotto.WinningLotto;
import util.Utility;
import view.InputView;
import view.PrintView;
import vo.Num;

import java.util.List;

public class LottoMain {
    public static void main(String[] args) {
        int lottoCount = InputView.inputPrice();
        int manualLottoCount = InputView.inputManualLottoCount();
        List<String> list = InputView.inputManualLottoNumber(manualLottoCount);

        PrintView.printLottoCount(lottoCount,manualLottoCount);
        UserLotto userLottos = new UserLotto(lottoCount-manualLottoCount,list);
        PrintView.printAutoLotto(userLottos.toDto());

        WinningLotto winningLotto = new WinningLotto(InputView.intputWinningNumber(),new Num(InputView.inputBonusBall()));

        PrintView.printResult(userLottos.matchNum(winningLotto),Utility.multiplyThousand(lottoCount));
    }
}
