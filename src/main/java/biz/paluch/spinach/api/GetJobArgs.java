package biz.paluch.spinach.api;

import com.lambdaworks.redis.protocol.CommandArgs;

import java.util.concurrent.TimeUnit;

/**
 * Arguments for adding a job.
 *
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class GetJobArgs {

    private Boolean noHang;
    private Long timeout;
    private Boolean withCounters;


    public Boolean getNoHang() {
        return noHang;
    }

    public void setNoHang(Boolean noHang) { this.noHang = noHang; }

    public Long getTimeout() { return timeout; }

    public void setTimeout(Long timeout) { this.timeout = timeout; }

    public Boolean getWithCounters() { return withCounters; }

    public void setWithCounters(Boolean withCounters) { this.withCounters = withCounters; }

    public Builder copyBuilder() {
        return GetJobArgs.builder().noHang(noHang).timeout(timeout).withCounters(withCounters);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Static builder methods.
     */
    public static class Builder {

        private Boolean noHang;
        private Long timeout;
        private Long count;
        private Boolean withCounters;

        /**
         * Utility constructor.
         */
        private Builder() {

        }

        public Builder noHang(Boolean noHang) {
            this.noHang = noHang;
            return this;
        }

        public Builder timeout(Long timeoutMs) {
            this.timeout = timeoutMs;
            return this;
        }

        public Builder timeout(long timeout, TimeUnit timeUnit) {
            this.timeout = timeUnit.toMillis(timeout);
            return this;
        }

        public Builder count(Long count) {
            this.count = count;
            return this;
        }

        public Builder withCounters(Boolean withCounters) {
            this.withCounters = withCounters;
            return this;
        }

        public GetJobArgs build() {
            GetJobArgs getJobArgs = new GetJobArgs();
            getJobArgs.setNoHang(noHang);
            getJobArgs.setTimeout(timeout);
            getJobArgs.setWithCounters(withCounters);
            return getJobArgs;

        }
    }

    public <K, V> void build(CommandArgs<K, V> args, Long count, K... queues) {

        // GETJOB [NOHANG] [TIMEOUT <ms-timeout>] [COUNT <count>] [WITHCOUNTERS] FROM queue1 queue2 ... queueN

        if (noHang != null && noHang.booleanValue()) {
            args.add(CommandKeyword.NOHANG.bytes);
        }

        if (timeout != null) {
            args.add(CommandKeyword.TIMEOUT.bytes).add(timeout);
        }

        if (count != null && count.longValue() != 1L) {
            args.add(CommandKeyword.COUNT.bytes).add(count);
        }

        if (withCounters != null && withCounters.booleanValue()) {
            args.add(CommandKeyword.WITHCOUNTERS.bytes);
        }
        args.add(CommandKeyword.FROM.bytes).addKeys(queues);
    }

}
