package edu.t1.limitservice.scheduled;

import edu.t1.limitservice.dao.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "app.scheduling.update-limits", name = "enabled", havingValue = "true")
@RequiredArgsConstructor
public class UpdateLimitsScheduler {

    private final LimitRepository limitRepository;

    @Scheduled(cron = "${app.scheduling.update-limits.cron}")
    public void scheduledTask() {
        log.debug("UpdateLimits - job started");
        limitRepository.resetAllAmountUsed();
    }
}
