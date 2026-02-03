package QazCodeMentoring_Week3_Task7;

import java.time.Instant;
import java.util.*;

public class EventAggregator {
    public static void main(String[] args) {
        Instant now = Instant.now();

        List<Event> events = Arrays.asList(
                new Event(1L, EventType.PURCHASE, now.minusSeconds(60)),
                new Event(1L, EventType.LOGIN, now.minusSeconds(300)),
                new Event(1L, EventType.PURCHASE, now.minusSeconds(30)),
                new Event(2L, EventType.LOGIN, now.minusSeconds(500)),
                new Event(2L, EventType.CLICK, now.minusSeconds(400)),
                new Event(1L, EventType.CLICK, now)
        );

        Map<Long, Map<EventType, List<Event>>> result = groupEvents(events);

        result.forEach((userId, typeMap) -> {
            System.out.println("User ID: " + userId);
            typeMap.forEach((type, eventList) -> {
                System.out.println("  Type: " + type);
                eventList.forEach(e ->
                        System.out.println("    - Time: " + e.timestamp())
                );
            });
        });
    }
    public static Map<Long, Map<EventType, List<Event>>> groupEvents(List<Event> events) {
        return events
                .stream()
                .sorted(Comparator.comparing(Event::timestamp))
                .collect(
                        HashMap::new
                        , (map, event) ->
                        {
                            var userMap = map.computeIfAbsent(event.userId(), k -> new HashMap<>());
                            List<Event> typeList = userMap.computeIfAbsent(event.type(), k -> new ArrayList<Event>());
                            typeList.add(event);
                        }
                        , Map::putAll
                );
    }
}

enum EventType {
    LOGIN, PURCHASE, CLICK
}

// Prikolnaya shtuka
record Event(long userId, EventType type, Instant timestamp) {
}


