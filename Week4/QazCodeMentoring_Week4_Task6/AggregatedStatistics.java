package org.example;

import java.util.List;

public class AggregatedStatistics {
    int successCount;
    int errorCount;
    long averageTime;

    public int getSuccessCount() {
        return successCount;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public long getAverageTime() {
        return averageTime;
    }

    public AggregatedStatistics(List<RequestResult> list) {
        int success = 0;
        int error = 0;
        long time = 0;
        int count = 0;
        for(RequestResult result: list){
            if(result.error){
                error++;
            }
            else{
                success++;
            }
            time+=result.elapsed;
            count++;
        }
        this.successCount=success;
        this.errorCount=error;
        this.averageTime = (count!=0) ? time/count : 0;
    }

    @Override
    public String toString() {
        return "AggregatedStatistics{" +
                "successCount=" + successCount +
                ", errorCount=" + errorCount +
                ", averageTime=" + averageTime +
                "ms}";
    }
}
