package racingcar.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.dto.FinalWinnersDto;

public class FinalWinners {

    private final List<RacingCar> finalWinners;

    private FinalWinners(final List<RacingCar> finalWinners) {
        this.finalWinners = finalWinners;
    }

    public static FinalWinners from(List<RacingCar> cars, int maxPosition) {
        List<RacingCar> result = cars.stream()
                .filter(car -> car.isSamePosition(maxPosition))
                .collect(Collectors.toList());
        return new FinalWinners(result);
    }

    public FinalWinnersDto toDto() {
        return new FinalWinnersDto(finalWinners.stream().map(RacingCar::getName).toList());
    }

}
