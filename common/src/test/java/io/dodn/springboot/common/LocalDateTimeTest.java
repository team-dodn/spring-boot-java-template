package io.dodn.springboot.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class LocalDateTimeTest implements TestDataSupplier {

    @Test
    void testLocalDateTimeNow() {
        final Instant fixedInstant = Instant.parse(TEST_VAL_INSTANT);
        final ZoneId zoneId = ZoneId.of(TEST_VAL_ZONE_ID);
        final Clock fixedClock = Clock.fixed(fixedInstant, zoneId);

        try (
                final MockedStatic<Clock> mockedClock = mockStatic(Clock.class);
                final MockedStatic<LocalDateTime> mockedLocalDateTime = mockStatic(LocalDateTime.class);
                final MockedStatic<ZonedDateTime> mockedZonedDateTime = mockStatic(ZonedDateTime.class)
        ) {
            mockedClock.when(() -> Clock.systemDefaultZone()).thenReturn(fixedClock);
            final LocalDateTime fixedLocalDateTime = LocalDateTime.ofInstant(fixedInstant, zoneId);
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(fixedLocalDateTime);
            final ZonedDateTime fixedZonedDateTime = ZonedDateTime.ofInstant(fixedInstant, zoneId);
            mockedZonedDateTime.when(ZonedDateTime::now).thenReturn(fixedZonedDateTime);

            // 테스트 확인
            assertEquals(fixedLocalDateTime, LocalDateTime.now());
            assertEquals(fixedZonedDateTime, ZonedDateTime.now());
        }
    }
}
