package lotto;

import dto.*;
import util.Utility;
import vo.Num;

import java.util.*;

public class UserLotto {
    private List<Lotto> lottos = new ArrayList<>();

    public UserLotto(int autoLottoCount,List<String> manualLottoManager) {
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(new Lotto());
        }
        for (String manualLotto : manualLottoManager) {
            lottos.add(new Lotto(makeManualLotto(manualLotto)));
        }
    }

    private Set<Num> makeManualLotto(String manualLotto) {
        Set<Num> manualLottoOne = new TreeSet<>();
        for (int i = 0; i < Utility.splitComma(manualLotto).length; i++) {
            manualLottoOne.add(new Num(Utility.fromStringToInteger(Utility.splitComma(manualLotto)[i])));
        }
        checkManualLottoNumber(manualLottoOne);
        return manualLottoOne;
    }

    private void checkManualLottoNumber(Set<Num> manualLottoOne) {
        if (manualLottoOne.size() != 6)
            throw new IllegalArgumentException("중복되는 숫자가 있거나 6개의 숫자가 아닙니다.");
    }

    public Reward matchNum(WinningLotto winningLotto) {
        Reward rewards = new Reward();
        for (Lotto userLotto : lottos) {
            int reward = userLotto.gameStart(winningLotto.makeWinningLotto());
            boolean matchBonus = winningLotto.checkMatchBonusNum(userLotto);
            if (reward > 2) {
                rewards.changeReward(Rank.valueOf(reward, matchBonus));
            }
        }
        return rewards;
    }

    public UserLottoDto toDto() {
        List<LottoDto> userLottoDto = new ArrayList<>();
        for (Lotto lotto : lottos) {
            userLottoDto.add(lotto.toDto());
        }
        return new UserLottoDto(userLottoDto);
    }
}