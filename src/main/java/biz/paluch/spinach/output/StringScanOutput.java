package biz.paluch.spinach.output;

import java.nio.ByteBuffer;

import com.lambdaworks.redis.KeyScanCursor;
import com.lambdaworks.redis.codec.RedisCodec;
import com.lambdaworks.redis.output.ScanOutput;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 13.07.15 12:47
 */
public class StringScanOutput<K, V> extends ScanOutput<K, V, KeyScanCursor<String>> {

    public StringScanOutput(RedisCodec<K, V> codec) {
        super(codec, new KeyScanCursor<String>());
    }

    @Override
    protected void setOutput(ByteBuffer bytes) {
        output.getKeys().add(bytes == null ? null : decodeAscii(bytes));
    }

}
