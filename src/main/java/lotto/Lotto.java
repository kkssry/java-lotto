package lotto;

import dto.LottoDto;
import vo.Num;

import java.util.*;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 45;
    private static List<Num> initLottoNumber = new ArrayList<>();
    private List<Num> lotto;

    static {
        for (int i = 1; i <= LOTTO_NUMBER_COUNT; i++) {
            initLottoNumber.add(new Num(i));
        }
    }

    Lotto() {                   //자동 로또
        Collections.shuffle(initLottoNumber);
        lotto = new ArrayList<>(initLottoNumber.subList(0, 6));
        Collections.sort(lotto);
    }

    Lotto(Set<Num> lottoByInputed) {
        lotto = new ArrayList<>(lottoByInputed);
    }

//    Lotto(Set<Num> manualLotto){       //수동 로또
//        lotto = new ArrayList<>(manualLotto);
//    }


    public int gameStart(Lotto winningLotto) {
        int count = 0;
        for (Num num : winningLotto.lotto) {
            if (lotto.contains(num))
                count++;
        }
        return count;
    }

    public Boolean isMatchBonusNum(Num bonusNum){
        return lotto.contains(bonusNum);
    }

    public LottoDto toDto() {
        return new LottoDto(lotto);
    }
}
