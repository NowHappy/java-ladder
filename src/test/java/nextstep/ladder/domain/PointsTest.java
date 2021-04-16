package nextstep.ladder.domain;

import nextstep.ladder.strategy.RandomDirectionStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

public class PointsTest {

    @Test
    @DisplayName("플레이어 인원 수와 연결 전략을 인수로 받아 플레이어 인원 수 만큼의 좌표를 생성한다.")
    public void create() throws Exception {
        //given
        Points points = new Points(4, new RandomDirectionStrategy());

        //when
        List<Point> result = points.points();

        then(result).hasSize(4);
    }

    @Test
    @DisplayName("좌표 인덱스를 인자로 받아 해당 좌표를 찾는다.")
    public void findByIndex() throws Exception {
        //given
        Point firstPoint = Point.first(directionStrategy());
        Point secondPoint = firstPoint.next(directionStrategy());
        Points points = new Points(Arrays.asList(firstPoint, secondPoint));

        //when
        Point point = points.findByIndex(0);

        then(point).isEqualTo(firstPoint);
    }

    private RandomDirectionStrategy directionStrategy() {
        return new RandomDirectionStrategy() {
            @Override
            public boolean isConnectable() {
                return true;
            }
        };
    }
}