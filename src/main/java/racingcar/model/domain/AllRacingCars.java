package racingcar.model.domain;

import static racingcar.common.ErrorMessage.ERROR_SINGLE_RACING_CAR_NAME;

import java.util.List;


public final class AllRacingCars {

    public static final int MIN_CAR_COUNT = 2;

    private final List<RacingCar> cars;

    public AllRacingCars(List<String> carNames) {
        List<RacingCar> cars = convertToRacingCar(carNames);
        validate(cars);
        this.cars = cars;
    }

    public void playOneRound(GameRecords gameRecords, int round) {
        Game game = new Game();
        cars.stream().forEach(game::play);
        gameRecords.record(round, game);
    }

    public FinalWinners getFinalWinners() {
        return FinalWinners.from(cars, calculateMaxPosition());
    }

    private void validate(List<RacingCar> cars) {
        if (cars.size() < MIN_CAR_COUNT) {
            throw new IllegalArgumentException(ERROR_SINGLE_RACING_CAR_NAME);
        }
    }

    private static List<RacingCar> convertToRacingCar(List<String> carNames) {
        return carNames.stream().map(RacingCar::new).toList();
    }

    private int calculateMaxPosition() {
        return cars.stream()
                .mapToInt(RacingCar::getPosition)
                .max()
                .orElse(0);
    }
}
