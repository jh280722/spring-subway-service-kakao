package subway.path.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.line.domain.Line;
import subway.line.domain.Lines;
import subway.line.domain.Section;
import subway.line.domain.Sections;
import subway.path.domain.Vertices;
import subway.path.domain.fare.strategy.FareStrategy;
import subway.path.domain.fare.strategy.LineFare;
import subway.station.domain.Station;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LineFareTest {

    @DisplayName("라인 추가 요금 테스트")
    @Test
    void lineExtraFare() {
        List<Section> sections = Arrays.asList(
                new Section(new Station(1L, "잠실새내"), new Station(2L, "잠실"), 1)
        );

        List<Section> sections2 = Arrays.asList(
                new Section(new Station(2L, "잠실"), new Station(3L, "복정"), 1)
        );

        Lines lines = Lines.of(Arrays.asList(
                Line.of(1L, "2호선", "bg-red-600", 400, new Sections(sections)),
                Line.of(1L, "8호선", "bg-red-600", 1000, new Sections(sections2))
        ));

        Vertices vertices = Vertices.of(lines.getVertices(1L, 2L));
        FareStrategy fareStrategy = new LineFare(vertices);
        assertThat(fareStrategy.getExtraFare()).isEqualTo(400);

        vertices = Vertices.of(lines.getVertices(1L, 3L));
        fareStrategy = new LineFare(vertices);
        assertThat(fareStrategy.getExtraFare()).isEqualTo(1000);
    }
}
