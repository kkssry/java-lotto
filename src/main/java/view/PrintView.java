package view;

import dto.LottoDto;
import dto.UserLottoDto;
import lotto.Rank;
import lotto.Reward;
import util.Utility;

public class PrintView {
    public static void printLottoCount(int lottoCount, int manualLottoCount) {
        System.out.println("수동으로 " + manualLottoCount + "장," + "자동으로 " + (lottoCount-manualLottoCount) + "개를 구매했습니다.");
    }

    public static void printResult(Reward reward, int sumPurchasingPrice) {
        for (Rank rank : Rank.values()) {
            System.out.println(rank.getCountOfMatch() + "개 일치 " + rank.getWinningMoney() + "- " + reward.outEachRewardCount(rank) + "개");
        }
        System.out.println("총 수익률은 " + Utility.multiplyHundred(reward.earningRate() / sumPurchasingPrice) + "%");
    }

    public static void printAutoLotto(UserLottoDto userLottoDto) {
        for (LottoDto lottoDto : userLottoDto.getUserLottoDto()) {
            System.out.println(lottoDto);
        }
    }
}
